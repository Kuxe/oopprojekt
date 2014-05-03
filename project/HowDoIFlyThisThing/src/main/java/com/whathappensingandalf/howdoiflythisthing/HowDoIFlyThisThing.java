package com.whathappensingandalf.howdoiflythisthing;

import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Connection;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Main model class. This is the class that is exposed to the controller
 * and all functionality regarding the model is called from here.
 */
public class HowDoIFlyThisThing {

	ModelNetworkState state;
	
	public HowDoIFlyThisThing() {
		state = new NoneState();
	}
	
	/**
	 *  
	 * @param spaceship
	 */
	public void addUser(Connection connection) {
		state.addUser(connection.getID());
	}
	
	public void host() {
		state = new HostState();
	}
	
	public void join(String ip) {
		state = new ClientState(ip);
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		state.update(listOfHoldKeys);
	}
	
	public Set<DrawableData> getDrawableData() {
		return state.getDrawableData();
	}
	
	public Point2f getSpaceshipPoint() {
		return state.getSpaceshipPoint();
	}
	
	/**
	 * Should always be called when program is exiting
	 * it stops clients and servers, does all the 
	 * cleaning work
	 */
	public void cleanup() {
		state.cleanup();
	}
}
