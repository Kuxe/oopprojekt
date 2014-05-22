package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.util.Set;

import javax.vecmath.Point2f;

public interface IModelNetworkState{
	
	public static enum State {
		HOST,
		CLIENT,
		NONE,
		ERROR
	}
	
	public static enum Message {
		SHUTDOWN
	}
	
	public State getState();
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
