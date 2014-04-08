package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
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

public class Projectile implements IMovable, ICollidable, IGameObject, IDrawable, Cloneable {
	
	private int width;
	private int height;
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
	 * @param width
	 * @param height
	 */
	public Projectile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		this.width = width;
		this.height = height;
		mC= new MoveComponent(position, velocity, acceleration, direction, 0f);
		colliComp = new CollidableComponent(position, direction, width, height);
		pcs = new PropertyChangeSupport(this);
	}
	
	/**
	 * Deep copy-ctor
	 * @param spaceship
	 */
	public Projectile(Projectile projectile) {
		this(
				projectile.getPosition(), 
				projectile.getVelocity(), 
				projectile.getAcceleration(), 
				projectile.getDirection(), 
				projectile.getWidth(), 
				projectile.getHeight());
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
		return new Vector2f(velocity.x, velocity.y);
	}
	/**
	 * {@inheritDoc}
	 */
	public Vector2f getAcceleration() {
		return new Vector2f(acceleration.x, acceleration.y);
	}
	/**
	 * {@inheritDoc}
	 */
	public Vector2f getDirection() {
		return new Vector2f(direction.x, direction.y);
	}
	/**
	 * {@inheritDoc}
	 */
	public Point2f getPosition() {
		return new Point2f(position.x, position.y);
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
	public float getRotVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}
	public float getRotAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setRotVelocity(float rotVelocity) {
		// TODO Auto-generated method stub
		
	}
	public void setRotAcceleration(float rotAcceleration) {
		// TODO Auto-generated method stub
		
	}
	public int getDamage(){
		return this.damage;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public String getType() {
		return type.PROJECTILE.toString();
	}
	public boolean collideDetection(ICollidable rhs) {
		return colliComp.collideDetection(rhs);
	}
	public Area getBoundingBox() {
		return colliComp.getBoundingBox();
	}

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(Spaceship spaceship) {
		//this.remove();
	}

	public void visit(Projectile projectile) {
		//Do nothing
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
	public Projectile clone() {
		return new Projectile(this);
	}
}//end Projectile