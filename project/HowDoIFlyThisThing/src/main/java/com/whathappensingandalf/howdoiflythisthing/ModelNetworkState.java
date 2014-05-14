package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Connection;

public interface ModelNetworkState{
	
	public static enum state {
		HOST,
		CLIENT,
		NONE,
		ERROR
	}
	
	public state getState();
	public void addUser(int id);	
	public void update(Set<Integer> listOfHoldKeys);
	public Set<DrawableData> getDrawableData();
	public Point2f getSpaceshipPoint();
	public void cleanup();
	public Set<String> getListOfSounds();
	public int getHull();
	public int getShield();
	public long getCountdown();
	public String getModelStatus();
	public WorldBorder getWorldBorder();
	public void addPropertyChangeListener(PropertyChangeListener listener);
}
