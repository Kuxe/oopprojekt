package controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.Asteroid;
import com.whathappensingandalf.howdoiflythisthing.Gameworld;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;
import com.whathappensingandalf.howdoiflythisthing.Spaceship;

import View.View;
import View.ViewThread;
import org.newdawn.slick.Input;

public class Controller {
	
	private Gameworld model;
	private ViewThread viewThread;
	private View view;
	
	//Create two spaceships and make the first one shoot bullets on the other one
	Spaceship spaceship1 = new Spaceship(new Point2f(50, 50), new Vector2f(3.0f, 1.0f), 50, 50);
	Spaceship spaceship2 = new Spaceship(new Point2f(500, 0), new Vector2f(-1, 0), 50, 50);
	Asteroid asteroid1 = new Asteroid(new Point2f(300, 20), 100, 100);
	
	public Controller(){
		model = new Gameworld();
		
		model.addSpaceship(spaceship1);
		model.addSpaceship(spaceship2);
		model.addAsteroid(asteroid1);
		viewThread=new ViewThread();
		viewThread.start();
	}
	
	public void start() {
		boolean running = true;
		while(running) {
			update();
			spaceship1.fireWeapon();
		}
	}
	
	private void update() {
		model.update();
		setRenderObjects(model.getIDrawables());
	}
	
	public void setRenderObjects(Map<Object,IDrawable> list){
		viewThread.getView().setRenderObjects(list);
	}
	private void inputHandeler(Input i, int delta){
		if(i.isKeyDown(Input.KEY_LEFT)){
			System.out.println("Left Thruster");
		}else if(i.isKeyDown(Input.KEY_RIGHT)){
			System.out.println("Main Thruster");
		}else if(i.isKeyDown(Input.KEY_DOWN)){
			System.out.println("Right Thruster");
		}else if(i.isKeyDown(Input.KEY_UP)){
			System.out.println("FIRE!!!");
		}
	}
	
}

