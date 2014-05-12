package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import View.View;
import services.SoundEffects;
import View.ViewThread;

import com.whathappensingandalf.howdoiflythisthing.DrawableData;
import com.whathappensingandalf.howdoiflythisthing.HowDoIFlyThisThing;
import com.whathappensingandalf.howdoiflythisthing.Keybindings;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements KeyListener, PropertyChangeListener{

	private HowDoIFlyThisThing model;
	private ViewThread viewThread;
	private SoundEffects soundEffects;
	private Keybindings keybindings;

	boolean running = false;

	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	private Set<Integer> listOfHoldKeys;

	public Controller(int leftKey, int mainKey, int rightKey, int fireKey, boolean fullscreen){
		keybindings = new Keybindings(leftKey, mainKey, rightKey, fireKey);
		sharedCTOR();
		model.host();
		this.createView();
	}

	public Controller(String ip, int leftKey, int mainKey, int rightKey, int fireKey, boolean fullscreen)throws java.net.UnknownHostException{
		keybindings = new Keybindings(leftKey, mainKey, rightKey, fireKey);
		sharedCTOR();
		try {
			model.join(ip);
		} catch (IOException ex) {
			viewThread.exit();
			throw new java.net.UnknownHostException();
		}
		this.createView();
	}

	private void sharedCTOR() {
		model = new HowDoIFlyThisThing(keybindings);	
		listOfPressedKeys = new HashSet();
		listOfReleasedKeys = new HashSet();
		listOfHoldKeys = new HashSet();
		soundEffects= new SoundEffects();
	}
	private void createView(){
		final Object lock = new Object();
		viewThread=new ViewThread(lock);
		viewThread.start();
		synchronized(lock) {
			while(!viewThread.isReady()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		viewThread.getView().getContainer().getInput().addKeyListener(this); //This row may crash if View-thread havent created view yet
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
		setCountdown(model.getCountdown());
		soundEffects.playSound(getListOfSounds());
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

	public synchronized void keyPressed(int key, char c) {
		listOfPressedKeys.add(key);
	}

	public synchronized void keyReleased(int key, char c) {
		listOfReleasedKeys.add(key);
	}

	public void setInput(Input input) {
		// TODO Auto-generated method stub
	}
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}
	public void inputEnded() {
		// TODO Auto-generated method stub
	}
	public void inputStarted() {
		// TODO Auto-generated method stub
	}

	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Controller recieved event: " + event.getPropertyName());
		if(event.getPropertyName().equals(View.message.VIEW_CLOSE.toString())) {
			running = false;
		}		
	}
	public Set<String> getListOfSounds(){
		return model.getListOfSounds();
	}
}