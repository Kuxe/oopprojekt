package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 *
 */
public interface IMovable extends IListable{
	
	public void move();
	//getters
	public Vector2f getVelocity();
	public Vector2f getAcceleration();
	public Vector2f getDirection();
	public Point2f getPosition();
	
	//setters
	public void setVelocity(Vector2f velocity);
	public void setAcceleration(Vector2f acceleration);
	public void setDirection(Vector2f direction);
	public void setPosition(Point2f position);
}