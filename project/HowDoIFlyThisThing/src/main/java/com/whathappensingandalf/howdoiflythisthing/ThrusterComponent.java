package com.whathappensingandalf.howdoiflythisthing;

import static java.lang.Math.sqrt;
import javax.vecmath.Vector2f;
import utils.TypeWrapper;

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
	private TypeWrapper rotationVelocity;
	private TypeWrapper rotationAcceleration;
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
    public ThrusterComponent(Vector2f acceleration, Vector2f direction,TypeWrapper rotationAcceleration,TypeWrapper rotationVelocity){
		this.acceleration=acceleration;
		this.direction=direction;
		this.rotationVelocity=rotationVelocity;
		System.out.println(rotationVelocity+"con");
		System.out.println(this.rotationVelocity+"con");
		this.rotationAcceleration=rotationAcceleration;
		this.mainThruster=new Thruster(0,1,200,0);
		this.leftThruster=new Thruster(0,1,100, (float)Math.PI);
		this.rightThruster=new Thruster(0,1,100, -(float)Math.PI);
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
	public void calculateRotation(Timestep timestep){
		System.out.println(this.rotationVelocity);
		float f=0;
//		f=f+mainThruster.getRotationAcceleration();
		f=(float) (f+rightThruster.getRotationAcceleration() * timestep.getDelta());
		f=(float) (f+leftThruster.getRotationAcceleration() * timestep.getDelta());
		this.rotationAcceleration.setValue(f);
		this.rotationVelocity.setValue(rotationVelocity.getValue()+rotationAcceleration.getValue());
	}
	public void activateMainThruster(){
		this.mainThruster.activate();
	}
	public void activateLeftThruster(){
		this.leftThruster.activate();
	}
	public void activateRightThruster(){
		this.rightThruster.activate();
	}
	public void deactivateMainThruster(){
		this.mainThruster.deactivate();
	}
	public void deactivateLeftThruster(){
		this.leftThruster.deactivate();
	}
	public void deactivateRightThruster(){
		this.rightThruster.deactivate();
	}
}
