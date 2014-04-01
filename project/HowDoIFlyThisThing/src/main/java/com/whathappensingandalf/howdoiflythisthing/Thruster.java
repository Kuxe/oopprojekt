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
	private Boolean active;
	private int magnitude;
	private Vector2f temp;
	
	/**
	 * A constructor creating a thruster with a direction given by the parameters.
	 * @param x
	 * @param y 
	 */
	public Thruster(float x, float y){
		this.direction = new Vector2f(x, y);
		this.direction.normalize();
		this.active = false;
		this.magnitude  =1;
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
		this.magnitude=magnitude;
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
		temp.scale(0);
		if(active){
			temp.scale(magnitude, new Vector2f(0,direction.y));
		}
		return temp;
		
	}
	
	public Float getRotationAcceleration(){
		if(active){
			return direction.x;
		}else{
			return 0.0f;
		}
	}
}
