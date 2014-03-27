package com.whathappensingandalf.howdoiflythisthing;

import static java.lang.Math.sqrt;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
class ThrusterComponent {
	
	private Vector2f direction;
	private Vector2f acceleration;
	private Thruster mainThruster;
	private Thruster rightThruster;
	private Thruster leftThruster;

    public ThrusterComponent(Vector2f acceleration, Vector2f direction){
		this.acceleration=acceleration;
		this.direction=direction;
		this.mainThruster=new Thruster(0,(float)sqrt(2));
		this.leftThruster=new Thruster(1,1);
		this.leftThruster=new Thruster(1,1);
	}
	
	public void calculateAceleration(){
		acceleration.scale(0);
		acceleration.add(mainThruster.getAcceleration(), rightThruster.getAcceleration());
		acceleration.add(leftThruster.getAcceleration());
	}
}
