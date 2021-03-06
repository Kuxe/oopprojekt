package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import utils.TypeWrapper;

/**
 * 
 * @author Francine
 *
 */

public class Bullet implements IProjectile {
	
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
	 * A instance of PropertyChangeSupport so that this class can be listened to.
	 */
	private PropertyChangeSupport pcs;
	
//	public static enum Message{
//		PROJECTILE_DIE
//	}
	
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
	public Bullet(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		this.width = width;
		this.height = height;
		mC= new MoveComponent(position, velocity, acceleration, direction, new TypeWrapper(0.0f),new TypeWrapper(0.0f));
		colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
	}
	
	/**
	 * Deep copy-ctor
	 * @param projectile
	 */
	public Bullet(Bullet projectile) {
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
	public void move(Timestep timestep){
		mC.move(timestep);
	}
	/**
	 * Removes all references to this component.
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
		return Type.BULLET.toString();
	}
	public boolean collideDetection(ICollidable rhs) {
		return colliComp.collideDetection(rhs);
	}

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(Spaceship spaceship) {
		this.remove();
	}

	public void visit(IProjectile projectile) {
		//Do nothing
	}
	
	public void visit(CookieCracker cookieCracker) {
		//Do nothing
	}
	
	public void visit(Asteroid asteroid) {
		this.remove();
	}
	
	public void visit(IPickup iPickup) {
		//Do nothing
	}
	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
    public void addPropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.addPropertyChangeListener(pcl);
	}
	/**
	 * Remove a listener to this object.
	 * @param pcl
	 */
    public void removePropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.removePropertyChangeListener(pcl);
	}  
	@Override
	public Bullet clone() {
		return new Bullet(this);
	}
	@Override
	public boolean equals(Object obj){
		if(this== obj){
			return true;
		}
		if(obj== null){
			return false;
		}
		if(this.getClass()!= obj.getClass()){
			return false;
		}
		Bullet p= (Bullet)obj;
		return width== p.width && height== p.height && velocity.equals(p.velocity) && acceleration.equals(p.acceleration) &&
				direction.equals(p.direction) && position.equals(p.position) && damage== p.damage;
	}

	@Override
	public Collection<? extends DrawableData> getCollectionDrawables() {
		Set<DrawableData> returnSet = new HashSet<DrawableData>();
		returnSet.add(new DrawableData(
				getPosition(),
				getHeight(),
				getWidth(),
				getDirection(),
				getType()));
		return returnSet;
	}
	
}//end Projectile