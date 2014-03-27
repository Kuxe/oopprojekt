package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 *
 */

public class Projectile implements IMovable{
	
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	private Point2f position;
	//private Damage damage= new Damage();
	private MoveComponent mC;
	
	public Projectile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction){
		mC= new MoveComponent(position, velocity);
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		
	}
	//methods
	public void move(){
		mC.move();
	}
	//getters
	public Vector2f getVelocity() {
		return velocity;
	}
	public Vector2f getAcceleration() {
		return acceleration;
	}
	public Vector2f getDirection() {
		return direction;
	}
	public Point2f getPosition() {
		return position;
	}

	//setters
	public void setVelocity(Vector2f speed) {
		mC.setVelocity(velocity);
	}
	public void setAcceleration(Vector2f acceleration) {
		mC.setAcceleration(acceleration);
		
	}
	public void setDirection(Vector2f direction) {
		mC.setDirection(direction);
		
	}
	public void setPosition(Point2f position) {
		mC.setPosition(position);
		
	}
}