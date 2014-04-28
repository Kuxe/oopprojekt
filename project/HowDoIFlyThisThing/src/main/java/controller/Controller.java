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

public class Controller{
	
	private Gameworld model;
	private ViewThread viewThread;
	
	//Create two spaceships and make the first one shoot bullets on the other one
	private Spaceship spaceship1 = SpaceshipFactory.create(new Point2f(50, 100), new Vector2f(3.0f, 1.0f));
	private Spaceship spaceship2 = SpaceshipFactory.create(new Point2f(200, 100), new Vector2f(-1, 0));
	private Asteroid asteroid1 = AsteroidFactory.create(new Point2f(500, 300));
	
	boolean aHold, wHold, dHold, spaceHold;
	
	private User user1;
	private User user2;
	
	public Controller(){
		model = new Gameworld();
		
		model.addSpaceship(spaceship1);
		model.addSpaceship(spaceship2);
		model.addAsteroid(asteroid1);
		
		viewThread=new ViewThread();
		viewThread.start();
		try {
			//HOWTO: make this thread sleep until a certain point in viewThread is reached?
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user1 = new User();
		user1.setSpaceship(spaceship1);
		
		user2 = new User();
		user2.setSpaceship(spaceship2);
		user2.setLeftButton(Keyboard.KEY_LEFT);
		user2.setRightButton(Keyboard.KEY_RIGHT);
		user2.setMainButton(Keyboard.KEY_UP);
		
		viewThread.getView().getContainer().getInput().addKeyListener(user1);
		viewThread.getView().getContainer().getInput().addKeyListener(user2);
	}
	
	public void start() {
		boolean running = true;
		while(running) {
			update();
		}
	}
	
	private void update() {
		user1.manageInput();
		user1.executeInput();
		user2.manageInput();
		user2.executeInput();
		model.update();
		viewThread.getView().setCamera(new Point2f((spaceship1.getPosition().x + spaceship2.getPosition().x)/2,
			(spaceship1.getPosition().y + spaceship2.getPosition().y)/2)); //Makes camerae follow spaceship1 position
		setRenderObjects(model.getIDrawables());	
	}
	
	public void setRenderObjects(Map<Object,IDrawable> list){
		viewThread.getView().setRenderObjects(list);
	}
	
	private void shutdown() {
		System.exit(0);
	}
}

