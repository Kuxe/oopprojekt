package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class Thruster {
	private Vector2f direction;
	private Boolean active;
	private int magnitude;
	private Vector2f temp;
	
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
	public Vector2f getAcceleration(){
		temp.scale(0);
		if(active){
			// "Sets the value of this tuple to the scalar multiplication of tuple t1."
			temp.scale(magnitude, direction);
			return temp;
		}else {
			return temp;
		}
	} 
}
