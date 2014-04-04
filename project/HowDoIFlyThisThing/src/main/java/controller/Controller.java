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
import View.ViewThred;

public class Controller {
	
	private Gameworld model;
	private ViewThred viewThred;
	private View view;
	
	public Controller(){
		model = new Gameworld();
		
		//Create two spaceships and make the first one shoot bullets on the other one
		Spaceship spaceship1 = new Spaceship(new Point2f(0, 0), new Vector2f(-1, 0), 5, 5);
		model.addSpaceship(spaceship1);
		spaceship1.fireWeapon();
		
		viewThred=new ViewThred();
		viewThred.start();
		
		setRenderObjects(model.getIDrawables());
		
		//Update gameworld 10 times.
		for(int i = 0; i < 5; i++){
			model.update();
			setRenderObjects(model.getIDrawables());
		}
//		view=new View();
	}
	
	public synchronized void setRenderObjects(Map<Object,IDrawable> list){
		viewThred.getView().setRenderObjects(list);
	}
	
}

//public class Controller {
//	
//	private Gameworld model;
//	private ViewThred viewThred;
//	private View view;
//	
//	public Controller(){
//		model = new Gameworld();
//		
//		//Create two spaceships and make the first one shoot bullets on the other one
//		Spaceship spaceship1 = new Spaceship(new Point2f(0, 0), new Vector2f(0, 0), 5, 5);
//		Spaceship spaceship2 = new Spaceship(new Point2f(10, 10), new Vector2f(0, 0), 5, 5);
//		model.addSpaceship(spaceship1);
//		model.addSpaceship(spaceship2);
//		spaceship1.fireWeapon();
//		
////		view=new View("How do i fly this thing?");
//		viewThred=new ViewThred();
//		viewThred.start();
//		System.out.println("tråd startad");
//		this.setRenderObjects(model.getIDrawables());
//		System.out.println("tråd anropad");
//		//Update gameworld 10 times.
//		for(int i = 0; i < 5; i++){
//			model.update();
//			this.setRenderObjects(model.getIDrawables());
//		}
//	}
//	
//	public synchronized void setRenderObjects(Map<Object,IDrawable> list){
//		viewThred.getView().setRenderObjects(list);
//	}
//	
//}

