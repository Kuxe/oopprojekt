package controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import View.View;
import View.ViewThread;

import com.whathappensingandalf.howdoiflythisthing.Asteroid;
import com.whathappensingandalf.howdoiflythisthing.Gameworld;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;
import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import com.whathappensingandalf.howdoiflythisthing.factorys.AsteroidFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

public class Controller implements KeyListener {
	
	private Gameworld model;
	private ViewThread viewThread;
	private View view;
	
	//Create two spaceships and make the first one shoot bullets on the other one
	private Spaceship spaceship1 = SpaceshipFactory.create(new Point2f(50, 50), new Vector2f(3.0f, -1.0f));
	private Spaceship spaceship2 = SpaceshipFactory.create(new Point2f(500, 0), new Vector2f(-1, 0));
	private Asteroid asteroid1 = AsteroidFactory.create(new Point2f(300, 20));
	
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	boolean aHold, wHold, dHold, spaceHold;
	
	public Controller(){
		model = new Gameworld();
		
		model.addSpaceship(spaceship1);
		model.addSpaceship(spaceship2);
		model.addAsteroid(asteroid1);
		
		listOfPressedKeys = new HashSet();
		listOfReleasedKeys = new HashSet();
		
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
	
	public void start() {
		boolean running = true;
		while(running) {
			update();
		}
	}
	
	private void update() {
		manageInput();
		executeInput();
		model.update();
		setRenderObjects(model.getIDrawables());	
	}
	
	public void setRenderObjects(Map<Object,IDrawable> list){
		viewThread.getView().setRenderObjects(list);
	}
	
	private void executeInput() {
		if(spaceHold) {
			spaceship1.fireWeapon();
		}
		if (wHold) {
			spaceship1.activateMainThruste();
		} else {
			spaceship1.deactivateMainThruster();
		}
		if (aHold) {
			spaceship1.activateLeftThruste();
		} else {
			spaceship1.deactivateLeftThruster();
		}
		if (dHold) {
			spaceship1.activateRightThruste();
		} else {
			spaceship1.deactivateRightThruster();
		}
	}
	
	private void manageInput() {
		for(int key : listOfPressedKeys) {
			switch(key) {
			case Keyboard.KEY_A: {
				aHold = true;
				break;
			}
			case Keyboard.KEY_W: {
				wHold = true;
				break;
			}
			case Keyboard.KEY_D: {
				dHold = true;
				break;
			}
			case Keyboard.KEY_SPACE: {
				spaceHold = true;
				break;
			}
			}
		}
		
		for(int key : listOfReleasedKeys) {
			switch(key) {
			case Keyboard.KEY_A: {
				aHold = false;
				break;
			}
			case Keyboard.KEY_W: {
				wHold = false;
				break;
			}
			case Keyboard.KEY_D: {
				dHold = false;
				break;
			}
			case Keyboard.KEY_SPACE: {
				spaceHold = false;
				break;
			}
			}
		}
		
		listOfPressedKeys.clear();
		listOfReleasedKeys.clear();
	}
	
	private void shutdown() {
		System.exit(0);
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

	public void keyPressed(int key, char c) {
		listOfPressedKeys.add(key);
	}

	public void keyReleased(int key, char c) {
		listOfReleasedKeys.add(key);
	}	
}

