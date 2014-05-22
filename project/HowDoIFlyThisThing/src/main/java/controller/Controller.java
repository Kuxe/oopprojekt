package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import network.ExplosionNetworkPacket;
import network.SparkleNetworkPacket;
import network.AsteroidDieNetworkPacket;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import services.SoundEffects;
import view.View;
import view.ViewThread;

import com.whathappensingandalf.howdoiflythisthing.DrawableData;
import com.whathappensingandalf.howdoiflythisthing.Gameworld;
import com.whathappensingandalf.howdoiflythisthing.HowDoIFlyThisThing;
import com.whathappensingandalf.howdoiflythisthing.Keybindings;
import com.whathappensingandalf.howdoiflythisthing.IModelNetworkState;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

public class Controller implements KeyListener, PropertyChangeListener{

	private HowDoIFlyThisThing model;
	private ViewThread viewThread;
	private SoundEffects soundEffects;
	private final Keybindings keybindings;

	boolean running = false;

	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	private Set<Integer> listOfHoldKeys;

	public Controller(int leftKey, int mainKey, int rightKey, int fireKey) throws IOException{
		keybindings = new Keybindings(leftKey, mainKey, rightKey, fireKey);
		sharedCTOR();
		model.host();
		model.addPropertyChangeListener(this);
	}

	public Controller(String ip, int leftKey, int mainKey, int rightKey, int fireKey)throws java.net.UnknownHostException{
		keybindings = new Keybindings(leftKey, mainKey, rightKey, fireKey);
		sharedCTOR();
		try {
			model.join(ip);
			model.addPropertyChangeListener(this);
		} catch (IOException ex) {
			throw new java.net.UnknownHostException();
		}
	}
	public void createTheView(boolean fullscreen){
		this.createView(fullscreen);
		
	}
	private void sharedCTOR() {
		model = new HowDoIFlyThisThing(keybindings);	
		listOfPressedKeys = Collections.synchronizedSet(new HashSet<Integer>());
		listOfReleasedKeys = Collections.synchronizedSet(new HashSet<Integer>());
		listOfHoldKeys = new HashSet<Integer>();
		soundEffects= new SoundEffects();
	}
	private void createView(boolean fullscreen){
		final Object lock = new Object();
		viewThread=new ViewThread(lock, fullscreen);
		new Thread(viewThread).start();
		synchronized(lock) {
			while(!viewThread.isReady()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		viewThread.getView().getContainer().getInput().addKeyListener(this); //This row may crash if View-thread haven't created view yet
		viewThread.getView().setBorder(model.getWorldBorder().getWorldWidth(), model.getWorldBorder().getWorldHeight()); //Set border size in view
		viewThread.getView().addPropertyChangeListener(this);
	}

	/**
	 * Set booleans representing if a key is held down or not
	 * If a key is inside pressedKeys (someone pressed a key), set the boolean to true
	 * If a key is inside releasedkeys (someone released a key), set the boolean to false
	 */
	public synchronized void manageInput() {
		for(int key : listOfPressedKeys) {
			listOfHoldKeys.add(key);
		}

		for(int key : listOfReleasedKeys) {
			listOfHoldKeys.remove(key);
		}

		listOfPressedKeys.clear();
		listOfReleasedKeys.clear();
	}

	public void start() {
		running = true;
		while(running) {
			update();
		}
	}

	private void update() {
		manageInput();		
		model.update(listOfHoldKeys);
		viewThread.getView().setCamera(model.getSpaceshipPoint());
		setRenderObjects(model.getDrawableData());
		viewThread.getView().setHull(model.getHull());
		viewThread.getView().setShield(model.getShield());
		setCountdown(model.getCountdown());
		setModelStatus(model.getModelStatus());
		
		soundEffects.playSound(getListOfSounds());
	}
	
	private void setModelStatus(String status) {
		viewThread.getView().setModelStatus(status);
	}

	private void setCountdown(long countdown){
		viewThread.getView().setCountdown(countdown);
	}
	
	public void setRenderObjects(Set<DrawableData> set){
		viewThread.getView().setRenderObjects(set); 
	}

	public void cleanup() {
		model.cleanup();
	}

	@Override
	public synchronized void keyPressed(int key, char c) {
		listOfPressedKeys.add(key);
	}

	@Override
	public synchronized void keyReleased(int key, char c) {
		if(key == Keyboard.KEY_ESCAPE){
			running = false;
			viewThread.stopView();
		}
		listOfReleasedKeys.add(key);
	}

	@Override
	public void setInput(Input input) {
		// not used
	}
	@Override
	public boolean isAcceptingInput() {
		return true;
	}
	@Override
	public void inputEnded() {
		// not used
	}
	@Override
	public void inputStarted() {
		// not used
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Controller recieved event: " + event.getPropertyName()); //Remove?
		
		//If either view was shutdown or model requested a a close, stop the game loop and let cleanup begin
		if(	event.getPropertyName().equals(View.Message.VIEW_CLOSE.toString()) ||
			event.getPropertyName().equals(IModelNetworkState.Message.SHUTDOWN.toString())) {
			viewThread.stopView();
			running = false;
		}else if(event.getPropertyName().equals(Gameworld.Message.EXPLOSION.toString())){
			this.viewThread.createExplosion(((ExplosionNetworkPacket)event.getOldValue()).position);
		}else if(event.getPropertyName().equals(Gameworld.Message.SPARKLE.toString())){
			this.viewThread.createSparkle(((SparkleNetworkPacket)event.getOldValue()).position);
		}else if(event.getPropertyName().equals(Gameworld.Message.ASTEROID_EXP.toString())){
			this.viewThread.createAsteroidExplosion(((AsteroidDieNetworkPacket)event.getOldValue()).position);
		}
	}
	public Set<String> getListOfSounds(){
		return model.getListOfSounds();
	}
	
	public void createExplosion(javax.vecmath.Point2f position){
		viewThread.createExplosion(position);
	}
}