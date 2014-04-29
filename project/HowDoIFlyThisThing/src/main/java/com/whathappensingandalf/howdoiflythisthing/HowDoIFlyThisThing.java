package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

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
	
	public void TEST_addUser2() {
		session.TEST_addUser2();
	}
	
	public void host() {
		session = new Session();
		addUser(127001);
	}
	
	public void join(int ip) {
		//TODO: Connect to server
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		session.update(listOfHoldKeys);
	}
	
	public Map<Object, IDrawable> getIDrawables() {
		return session.getIDrawables();
	}
	
	/**
	 *  This method returns point of spaceship beloning to ip.
	 *  If ip is currently a spectator, returns position where
	 *  spaceship exploded. If ip never had a spaceship returns
	 *  null.
	 * @return Point of spaceship beloning to ip
	 */
	public Point2f getSpaceshipPoint(int ip) {
		return session.getSpaceshipPoint(ip); 
	}
}
