package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 * 
 * State used by model when it's neither hosting nor acting as client
 */
public class NoneState implements ModelNetworkState {

	public void update() {
		// TODO Auto-generated method stub

	}

	public state getState() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUser(int ip) {
		// TODO Auto-generated method stub

	}

	public void update(Set<Integer> listOfHoldKeys) {
		// TODO Auto-generated method stub

	}

	public Map<Object, IDrawable> getIDrawables() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point2f getSpaceshipPoint(int ip) {
		// TODO Auto-generated method stub
		return null;
	}

}
