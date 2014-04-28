package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashSet;

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
	
	HashSet<User> users;
	
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
}
