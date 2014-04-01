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
	
	
	/**
	 * Contructs a projectile with a specified position, velocity, acceleration and direction
	 * 
	 * @param position
	 * @param velocity
	 * @param acceleration
	 * @param direction
	 */
	public Projectile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction){
		mC= new MoveComponent(position, velocity, acceleration, direction);
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		
	}
	//methods
	/**
	 * {@inheritDoc}
	 */
	public void move(){
		mC.move();
	}
	//getters
	/**
	 * {@inheritDoc}
	 */
	public Vector2f getVelocity() {
		return velocity;
	}
	/**
	 * {@inheritDoc}
	 */
	public Vector2f getAcceleration() {
		return acceleration;
	}
	/**
	 * {@inheritDoc}
	 */
	public Vector2f getDirection() {
		return direction;
	}
	/**
	 * {@inheritDoc}
	 */
	public Point2f getPosition() {
		return position;
	}

	//setters
	/**
	 * {@inheritDoc}
	 */	
	public void setVelocity(Vector2f velocity) {
		mC.setVelocity(velocity);
	}
	/**
	 * {@inheritDoc}
	 */
	public void setAcceleration(Vector2f acceleration) {
		mC.setAcceleration(acceleration);
	}
	/**
	 * {@inheritDoc}
	 */
	public void setDirection(Vector2f direction) {
		mC.setDirection(direction);
	}
	/**
	 * {@inheritDoc}
	 */
	public void setPosition(Point2f position) {
		mC.setPosition(position);
	}
	@Override
	public double getRotVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getRotAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setRotVelocity(double rotVelocity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRotAcceleration(double rotAcceleration) {
		// TODO Auto-generated method stub
		
	}
}//end Projectile