package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Francine
 *
 */
public interface IMovable {
	
	//getters
	public Vector getSpeed();
	public Vector getAcceleration();
	public Vector getDirection();
	public Point getPossition();
	
	//setters
	public void setSpeed();
	public void setAcceleration();
	public void setDirection();
	public void setPossition();
}