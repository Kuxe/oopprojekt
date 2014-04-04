package controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.Gameworld;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;
import com.whathappensingandalf.howdoiflythisthing.Spaceship;

import View.View;
import View.ViewThread;

public class Controller {
	
	private Gameworld model;
	private ViewThread viewThread;
	private View view;
	
	public Controller(){
		model = new Gameworld();
		
		//Create two spaceships and make the first one shoot bullets on the other one
		Spaceship spaceship1 = new Spaceship(new Point2f(0, 0), new Vector2f(1, 0), 5, 5);
		Spaceship spaceship2 = new Spaceship(new Point2f(100, 0), new Vector2f(-1, 0), 5, 5);
		model.addSpaceship(spaceship1);
		
		viewThread=new ViewThread();
		viewThread.start();
	}
	
	public void start() {
		boolean running = true;
		while(running) {
			update();
		}
	}
	
	private void update() {
		model.update();
		setRenderObjects(model.getIDrawables());
	}
	
	public synchronized void setRenderObjects(Map<Object,IDrawable> list){
		viewThread.getView().setRenderObjects(list);
	}
	
}

