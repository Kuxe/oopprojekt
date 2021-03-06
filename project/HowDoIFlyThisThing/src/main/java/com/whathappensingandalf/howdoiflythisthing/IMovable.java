package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 * @revised Mathias
 *
 */
public interface IMovable extends IListable{
	
	
	/**
	 * Moves this component to the specified location
	 */
	public void move(Timestep timestep);
	/**
	 * Removes this component to the specified location
	 */
	public void remove();
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
	
	/**
	 * @return the rotationvelocity of this component
	 */
	public float getRotVelocity();
	
	/**
	 * @return the rotationaceleration of this component
	 */
	public float getRotAcceleration();
	
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
	
	/**
	 * Sets the rotationvelocity of this component to the specified rotationvelocity
	 * @param rotVelocity
	 */
	public void setRotVelocity(float rotVelocity);
	
	/**
	 * Sets the rotationacceleration of this component to the specified rotationacceleration
	 * @param rotAcceleration
	 */
	public void setRotAcceleration(float rotAcceleration);
}