package controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.lwjgl.input.Keyboard;

import View.ViewThread;

import com.whathappensingandalf.howdoiflythisthing.Asteroid;
import com.whathappensingandalf.howdoiflythisthing.Gameworld;
import com.whathappensingandalf.howdoiflythisthing.HowDoIFlyThisThing;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;
import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import com.whathappensingandalf.howdoiflythisthing.factorys.AsteroidFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

public class Controller{
	
	private HowDoIFlyThisThing model;
	private ViewThread viewThread;
	
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	boolean aHold, wHold, dHold, spaceHold;
	
	public Controller(){
		model = new HowDoIFlyThisThing();
		
		model.host();
		
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
	}
	
	public void start() {
		boolean running = true;
		while(running) {
			update();
		}
	}
	
	private void update() {
		model.update();
		viewThread.getView().setCamera(new Point2f(0, 0));
		setRenderObjects(model.getIDrawables());	
	}
	
	public void setRenderObjects(Map<Object,IDrawable> list){
		viewThread.getView().setRenderObjects(list);
	}
	
	private void shutdown() {
		System.exit(0);
	}
}

