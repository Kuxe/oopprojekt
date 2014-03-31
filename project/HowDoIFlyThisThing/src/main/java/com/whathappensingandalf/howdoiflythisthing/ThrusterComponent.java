package com.whathappensingandalf.howdoiflythisthing;

import static java.lang.Math.sqrt;
import javax.vecmath.Vector2f;

/**
 *
 *	@author Martin Nilsson
 *	@revised Mathias
 * 
 *	A class for handeling thruster calculation common for several classes that 
 *	implement IThrustable. The reason is to avoid duplicate code. 
 *	This solution is inspired by the Entity-component-system design. 
 */
class ThrusterComponent {
	/**
	 * Vectors for calculating and representing the resulting acceleration.
	 */
	private Vector2f acceleration;
	private Vector2f direction;
	/**
	 * Thrusters used for calculating the resulting acceleration.
	 */
	private Thruster mainThruster;
	private Thruster rightThruster;
	private Thruster leftThruster;
	
	/**
	 * A constructor for creating a ThrusterComponent. The parameters are the instanses 
	 * to be shared between the movecomponent and the clas that owns it. 
	 * @param acceleration
	 * @param direction 
	 */
    public ThrusterComponent(Vector2f acceleration, Vector2f direction){
		this.acceleration=acceleration;
		this.direction=direction;
		this.mainThruster=new Thruster(0,1);
		this.leftThruster=new Thruster(1,1);
		this.rightThruster=new Thruster(-1,1);
	}
	
	/**
	 * The method that calculates the resulting of all the thrusters.
	 * The acceleration vector is changed accordingly by this method.
	 */
	public void calculateAceleration(){
		acceleration.scale(0);
		acceleration.add(mainThruster.getAcceleration(), rightThruster.getAcceleration());
		acceleration.add(leftThruster.getAcceleration());
	}
}
