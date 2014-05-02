package com.whathappensingandalf.howdoiflythisthing;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Connection;

public interface ModelNetworkState {
	
	public static enum state {
		HOST,
		CLIENT,
		NONE,
		ERROR
	}
	
	public state getState();
	public void addUser(InetSocketAddress connection);	
	public void update(Set<Integer> listOfHoldKeys);
	public Set<DrawableData> getDrawableData();
	public Point2f getSpaceshipPoint();
	public void cleanup();
}
