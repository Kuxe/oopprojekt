package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import com.whathappensingandalf.howdoiflythisthing.ModelNetworkState.state;

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
	public void addUser(int ip) {
		state.addUser(ip);
	}
	
	public void host() {
		state = new HostState();
		state.addUser(127001);
	}
	
	public void join(int ip) {
		state = new ClientState();
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		state.update(listOfHoldKeys);
	}
	
	public Map<Object, IDrawable> getIDrawables() {
		return state.getIDrawables();
	}
	
	public Point2f getSpaceshipPoint(int ip) {
		return state.getSpaceshipPoint(ip);
	}
}
