package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * State used by model when acting as a host
 */
public class HostState implements ModelNetworkState {
	
	private Session session;
	
	public HostState() {
		session = new Session();
	}
	
	public void update() {
		// TODO Auto-generated method stub
	}

	public ModelNetworkState.state getState() {
		return ModelNetworkState.state.HOST;
	}
	
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
