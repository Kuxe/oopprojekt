package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 *
 */

public class Projectile implements IMovable{
	
	private Vector2f speed;
	private Vector2f acceleration;
	private Vector2f direction;
	private Point2f position;
	//private Damage damage= new Damage();
	private MoveComponent mC;
	
	public Projectile(Point2f position, Vector2f speed, Vector2f acceleration, Vector2f direction){
		mC= new MoveComponent(position, speed);
		this.speed= speed;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		
	}
	//methods
	public void move(){
		mC.move();
	}
	//getters
	public Vector2f getSpeed() {
		return speed;
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
	public void setSpeed(Vector2f speed) {
		this.speed= speed;
	}
	public void setAcceleration(Vector2f acceleration) {
		this.acceleration= acceleration;
		
	}
	public void setDirection(Vector2f direction) {
		this.direction= direction;
		
	}
	public void setPosition(Point2f position) {
		this.position= position;
		
	}
}