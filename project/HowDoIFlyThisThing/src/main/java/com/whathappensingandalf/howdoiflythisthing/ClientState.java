package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 * 
 * State used by model when it's acting as a client
 */
public class ClientState implements ModelNetworkState {

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public state getState() {
		return ModelNetworkState.state.CLIENT;
	}

	public void addUser(int ip) {
		// TODO Auto-generated method stub
		
	}

	public void update(Set<Integer> listOfHoldKeys) {
		// TODO Auto-generated method stub
	}

	public Map<Object, IDrawable> getIDrawables() {
		return null;
	}

	public Point2f getSpaceshipPoint(int ip) {
		return null;
	}
}
