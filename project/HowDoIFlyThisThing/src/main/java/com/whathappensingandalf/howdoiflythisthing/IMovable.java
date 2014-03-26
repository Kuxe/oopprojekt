package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Francine
 *
 */
public interface IMovable {
	
	public void move();
	//getters
	public Vector2f getSpeed();
	public Vector2f getAcceleration();
	public Vector2f getDirection();
	public Point2f getPosition();
	
	//setters
	public void setSpeed(Vector2f speed);
	public void setAcceleration(Vector2f accelertion);
	public void setDirection(Vector2f direction);
	public void setPosition(Point2f position);
}