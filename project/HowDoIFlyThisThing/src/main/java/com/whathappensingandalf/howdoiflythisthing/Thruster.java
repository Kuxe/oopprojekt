package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 * 
 * A class for representing a single thruster. It is used to calculate acceleration
 * of the entety that it is a component of.
 */
public class Thruster {
	/**
	 * A vector for storing the direction of the thruster.
	 * A boolean that indicates if the thruster is active or not.
	 * A int saving the magnitude of the thruster output.
	 * A temporary vector used for calculation.
	 */
	private Vector2f direction;
	private Vector2f zero = new Vector2f(0,0);
	private Boolean active;
	private float rotationAcceleration;
	
	/**
	 * A constructor creating a thruster with a direction given by the parameters.
	 * @param x
	 * @param y 
	 * @param magnitude 
	 * @param rotation 
	 */
	public Thruster(float x, float y, float magnitude, float rotation){
		this.direction = new Vector2f(x, y);
		this.direction.normalize();
		this.direction.scale(magnitude);
		this.rotationAcceleration = rotation;
		this.active = false;
	}
	
	/**
	* Activates this thruster.
	*/
	public void activate(){
		this.active=true;
	}
	
	/**
	* Deactivates this thruster.
	*/
	public void deactivate(){
		this.active=false;
	}
	
	/**
	* Changes the magnitude of this thruster.
	 * @param magnitude
	*/
	public void setMagnitude(int magnitude){
		this.direction.normalize();
		this.direction.scale(magnitude);
	}
	
	/**
	* Returns the acceleration created by this thruster.
	 * @return A vector with the acurate direction and magnitude.
	*/
//	public Vector2f getAcceleration(){
//		temp.scale(0);
//		if(active){
//			// "Sets the value of this tuple to the scalar multiplication of tuple t1."
//			temp.scale(magnitude, direction);
//			return temp;
//		}else {
//			return temp;
//		}
//	} 
	public Vector2f getAcceleration(){
		if(active){
			return this.direction;
		}
		else{
			return this.zero;
		}
		
	}
	
	public Float getRotationAcceleration(){
		if(active){
			return this.rotationAcceleration;
		}else{
			return 0.0f;
		}
	}
	
	public boolean isThrusterActive(){
		return active;
	}
	
	public Vector2f getDirection(){
		return this.direction;
	}
}
