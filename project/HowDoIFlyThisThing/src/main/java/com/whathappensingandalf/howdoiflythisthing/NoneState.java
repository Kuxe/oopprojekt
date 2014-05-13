package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
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

	public void addUser(int id) {
		// TODO Auto-generated method stub

	}

	public void update(Set<Integer> listOfHoldKeys) {
		// TODO Auto-generated method stub

	}

	public Set<DrawableData> getDrawableData() {
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

	public Set<String> getListOfSounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHull() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getShield() {
		// TODO Auto-generated method stub
		return 0;
	}
	public long getCountdown() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public String getModelStatus() {
		return "Something went horribly wrong (Model has NoneState)";
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}
}
