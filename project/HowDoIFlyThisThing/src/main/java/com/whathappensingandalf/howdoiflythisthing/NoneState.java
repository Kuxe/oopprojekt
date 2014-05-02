package com.whathappensingandalf.howdoiflythisthing;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Connection;

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

	public void addUser(InetSocketAddress ip) {
		// TODO Auto-generated method stub

	}

	public void update(Set<Integer> listOfHoldKeys) {
		// TODO Auto-generated method stub

	}

	public Set<IDrawable> getIDrawables() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point2f getSpaceshipPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
}
