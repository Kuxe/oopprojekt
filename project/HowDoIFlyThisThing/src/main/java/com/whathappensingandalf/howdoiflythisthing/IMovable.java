package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Francine
 *
 */
public interface IMovable {
	
	public void move();
	//getters
	public Vector getSpeed();
	public Vector getAcceleration();
	public Vector getDirection();
	public Point getPossition();
	
	//setters
	public void setSpeed(Vector speed);
	public void setAcceleration(Vector accelertion);
	public void setDirection(Vector direction);
	public void setPossition(Point possition);
}