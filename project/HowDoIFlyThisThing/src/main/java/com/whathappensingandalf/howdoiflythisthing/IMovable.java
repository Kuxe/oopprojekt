package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 *
 */
public interface IMovable extends IListable{
	
	
	/**
	 * Moves this component to the specified location
	 */
	public void move();
	
	//getters
	/**
	 * @return the velocity of this component
	 */
	public Vector2f getVelocity();
	
	/**
	 * @return the acceleration of this component
	 */
	public Vector2f getAcceleration();
	/**
	 * @return the direction of this component
	 */
	public Vector2f getDirection();
	/**
	 * @return the position of this component
	 */
	public Point2f getPosition();
	
	//setters
	/**
	 * Sets the velocity of this component to the specified velocity
	 * @param velocity
	 */
	public void setVelocity(Vector2f velocity);
	/**
	 * Sets the acceleration of this component to the specified acceleration
	 * @param acceleration
	 */
	public void setAcceleration(Vector2f acceleration);
	/**
	 * Sets the direction of this component to the specified direction
	 * @param direction
	 */
	public void setDirection(Vector2f direction);
	/**
	 * Sets the position of this component to the specified position
	 * @param position
	 */
	public void setPosition(Point2f position);
}