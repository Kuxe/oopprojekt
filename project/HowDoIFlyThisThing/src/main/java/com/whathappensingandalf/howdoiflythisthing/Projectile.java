package com.whathappensingandalf.howdoiflythisthing;

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
	
	public Projectile(Point2f position, Vector2f vector){
		mC= new MoveComponent();
		this position= position;
		
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
		//TODO
	}
	public void setAcceleration(Vector2f accelation) {
		// TODO Auto-generated method stub
		
	}
	public void setDirection(Vector2f direction) {
		// TODO Auto-generated method stub
		
	}
	public void setPosition(Point2f position) {
		// TODO Auto-generated method stub
		
	}
}