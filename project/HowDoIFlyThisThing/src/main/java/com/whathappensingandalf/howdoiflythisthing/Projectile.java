package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 *
 */

public class Projectile implements IMovable, ICollidable, IGameObject{
	
	private double width;
	private double height;
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	private Point2f position;
	//private Damage damage= new Damage();
	private MoveComponent mC;
	private CollidableComponent colliComp;
	
	/**
	 * Contructs a projectile with a specified position, velocity, acceleration and direction
	 * 
	 * @param position
	 * @param velocity
	 * @param acceleration
	 * @param direction
	 */
	public Projectile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction){
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		mC= new MoveComponent(position, velocity, acceleration, direction);
	}
	/**
	 * @param position
	 * @param velocity
	 * @param acceleration
	 * @param direction
	 * @param width
	 * @param height
	 */
	public Projectile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, double width, double height){
		this(position, velocity, acceleration, direction);
		this.width= width;
		this.height= height;
		colliComp= new CollidableComponent(position, width, height);
	}
	//methods
	/**
	 * {@inheritDoc}
	 */
	public void move(){
		mC.move();
	}
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
	public float getRotVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getRotAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setRotVelocity(float rotVelocity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setRotAcceleration(float rotAcceleration) {
		// TODO Auto-generated method stub
		
	}

	public int getHeight() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public int getWidth() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String getType() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	@Override
	public boolean collideDetection(ICollidable rhs) {
		return colliComp.collideDetection(rhs);
	}
	@Override
	public Rectangle2D getBoundingBox() {
		return colliComp.getBoundingBox();
	}

}//end Projectile