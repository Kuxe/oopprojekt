package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Francine
 *
 */

public class Projectile implements IMovable{
	
	private Vector speed;
	private Vector acceleration;
	private Vector direction;
	private Point possition;
	private Damage damage= new Damage();
	private MoveComponent mC= new MoveComponent();
	
	//getters
	public Vector getSpeed() {
		return speed;
	}
	public Vector getAcceleration() {
		return acceleration;
	}
	public Vector getDirection() {
		return direction;
	}
	public Point getPossition() {
		return possition;
	}
	
	//setters
	public void setSpeed(Vector speed) {
		//TODO
	}
	public void setAcceleration(Vector accelation) {
		// TODO Auto-generated method stub
		
	}
	public void setDirection(Vector direction) {
		// TODO Auto-generated method stub
		
	}
	public void setPossition(Point possition) {
		// TODO Auto-generated method stub
		
	}
}