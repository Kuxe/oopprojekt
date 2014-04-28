package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashSet;
import java.util.Map;

import controller.User;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Main model class. This is the class that is exposed to the controller
 * and all functionality regarding the model is called from here.
 */
public class HowDoIFlyThisThing {
	
	Session session;
	
	public HowDoIFlyThisThing() {
		
	}
	
	/**
	 *  
	 * @param spaceship
	 */
	public void addUser(int ip) {
		session.addUser(ip);
	}
	
	public void host() {
		session = new Session();
		addUser(127001);
	}
	
	public void join(int ip) {
		//TODO: Connect to server
	}
	
	public void update() {
		session.update();
	}
	
	public Map<Object, IDrawable> getIDrawables() {
		return session.getIDrawables();
	}
}
