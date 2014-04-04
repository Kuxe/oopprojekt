package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Francine
 *
 */

public class Projectile implements IMovable, ICollidable, IGameObject, IDrawable{
	
	private double width;
	private double height;
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	private Point2f position;
	private int damage = 1;  //@TODO
	//private Damage damage= new Damage();
	private MoveComponent mC;
	private CollidableComponent colliComp;
	/**
	 * A instance of PropertyChangeSupport so that this class can be listend to.
	 */
	private PropertyChangeSupport pcs;
	
	public static enum Message{
		PROJECTILE_DIE
	}
	
	/**
	 * Contructs a projectile with a specified position, velocity, acceleration and direction
	 * 
	 * @param position
	 * @param velocity
	 * @param acceleration
	 * @param direction
	 */
	public Projectile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, double width, double height){
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		mC= new MoveComponent(position, velocity, acceleration, direction, 0f);
		colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
	}

	//methods
	/**
	 * {@inheritDoc}
	 */
	public void move(){
		mC.move();
	}
	/**
	 * Removes all referenses to this component.
	 */
	public void remove(){
		this.pcs.firePropertyChange(Message.PROJECTILE_DIE.toString(), this, true);
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
	public int getDamage(){
		return this.damage;
	}

	public double getHeight() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public double getWidth() {
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

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(Spaceship spaceship) {
		this.remove();
	}

	public void visit(Projectile projectile) {
		this.remove();
	}
	
	public void visit(Asteroid asteroid) {
		this.remove();
	}
	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
    public void addPropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.addPropertyChangeListener(pcl);
	}
	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
    public void removePropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.removePropertyChangeListener(pcl);
	}

	@Override
	public Point2f getPossition() {
		return position;
	}

}//end Projectile