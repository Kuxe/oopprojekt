package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

public interface ModelNetworkState {
	
	public static enum state {
		HOST,
		CLIENT,
		NONE,
		ERROR
	}
	
	public state getState();
	public void addUser(int ip);	
	public void update(Set<Integer> listOfHoldKeys);
	public Map<Object, IDrawable> getIDrawables();
	public Point2f getSpaceshipPoint(int ip);
}
