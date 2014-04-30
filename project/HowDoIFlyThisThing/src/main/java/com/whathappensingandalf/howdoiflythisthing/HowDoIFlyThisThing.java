package com.whathappensingandalf.howdoiflythisthing;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
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
	public void addUser(InetSocketAddress ip) {
		state.addUser(ip);
	}
	
	public void host() {
		state = new HostState();
		((HostState)state).host();
	}
	
	public void join(String ip) {
		state = new ClientState(ip);
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		state.update(listOfHoldKeys);
	}
	
	public Map<Object, IDrawable> getIDrawables() {
		return state.getIDrawables();
	}
	
	public Point2f getSpaceshipPoint() {
		return state.getSpaceshipPoint();
	}
}
