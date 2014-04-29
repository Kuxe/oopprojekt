package controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import View.ViewThread;

import com.whathappensingandalf.howdoiflythisthing.HowDoIFlyThisThing;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;

public class Controller implements KeyListener{
	
	private HowDoIFlyThisThing model;
	private ViewThread viewThread;
	
	
	
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	private Set<Integer> listOfHoldKeys;
	
	public Controller(){
		model = new HowDoIFlyThisThing();
		
		model.host();
		model.TEST_addUser2();
		
		listOfPressedKeys = new HashSet();
		listOfReleasedKeys = new HashSet();
		listOfHoldKeys = new HashSet();
		
		viewThread=new ViewThread();
		viewThread.start();
		try {
			//HOWTO: make this thread sleep until a certain point in viewThread is reached?
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewThread.getView().getContainer().getInput().addKeyListener(this);
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
		boolean running = true;
		while(running) {
			update();
		}
	}
	
	private void update() {
		manageInput();		
		model.update(listOfHoldKeys);
		viewThread.getView().setCamera(model.getSpaceshipPoint(127001));
		setRenderObjects(model.getIDrawables());	
	}
	
	public void setRenderObjects(Map<Object,IDrawable> list){
		viewThread.getView().setRenderObjects(list);
	}
	
	private void shutdown() {
		System.exit(0);
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
}

