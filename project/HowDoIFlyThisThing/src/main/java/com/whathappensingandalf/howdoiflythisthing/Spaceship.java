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
import utils.VecmathUtils;

/**
 *
 * @author Martin Nilsson
 * 
 * A class for reperesenting a Spaceship. It uses different components in a way
 * similiar to the Entety-component-system to avoid duplicate code. This class 
 * implements IMovable and IThrustable as it will be able to move and doing it 
 * by using a number of Thrusters.
 */
public class Spaceship implements IMovable, IThrustable, ICollidable, IGameObject, IDrawable, Cloneable {
	/**
	 * Different components for avoiding duplicate code.
	 */
    private ArmsComponent armsComponent;
    private MoveComponent moveComponent;
    private ThrusterComponent thrusterComponent;
    private CollidableComponent colliComp;
	/**
	 * The nessesary information for moving the Spaceship.
	 */
    private int width;
	private int height;
    private Point2f position;
	private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f direction;
	private TypeWrapper rotationVelocity;
	private TypeWrapper rotationAcceleration;
	private final Vector2f WEAPON_PIPE_POSITION;
	/**
	 * A instance of PropertyChangeSupport so that this class can be listend to.
	 */
	private PropertyChangeSupport pcs;
	
	private Hull hull;
	private Shield shield;
	
	public static enum Message{
		SPACESHIP_DIE,
		SPACESHIP_FIRE
	}
	
	/**
	 * A constructor for creating a Spaceship in the given possition and faceing
	 * in the given direction.
	 * @param position
	 * @param direction
	 * @param width
	 * @param height 
	 */
    public Spaceship(Point2f position, Vector2f direction, int width, int height){
		this.width = width;
		this.height = height;
    	this.pcs = new PropertyChangeSupport(this);
        this.position = position;
        this.acceleration = new Vector2f(0.0f, 0.0f);
        this.direction = direction;
        this.velocity = new Vector2f(0.0f, 0.0f);
        rotationAcceleration = new TypeWrapper(0.0f);
        rotationVelocity = new TypeWrapper(0.0f);        
        this.WEAPON_PIPE_POSITION = new Vector2f(0.0f, height/2 + 30.0f); //Should fire from middle of spaceships just infront of it
        this.hull=new Hull(10);
        this.shield=new Shield(3);
        this.moveComponent = new MoveComponent(this.position, this.velocity, this.acceleration, this.direction, this.rotationVelocity, this.rotationAcceleration);
		this.thrusterComponent = new ThrusterComponent(this.acceleration, this.direction, rotationAcceleration, rotationVelocity);
		this.armsComponent = new ArmsComponent(this.position, velocity, new Vector2f(0,0), this.direction, WEAPON_PIPE_POSITION);
		this.colliComp = new CollidableComponent(this.position, /*this.direction,*/ this.width, this.height);
		direction.normalize();
    }
    
    /**
     * Deep Copy-constructor
     * @param spaceship
     */
	public Spaceship(Spaceship spaceship) {
		this(spaceship.getPosition(),spaceship.getDirection(), spaceship.getWidth(), spaceship.getHeight());
	}
	/**
	 * {@inheritDoc}
	 */
    public synchronized void move(Timestep timestep) {
		this.calculateThrust(timestep);
		calculateWeaponPipePosition();
        this.moveComponent.move(timestep);
    }
    
    private void calculateWeaponPipePosition() {
    	VecmathUtils.setAngleFromVector(WEAPON_PIPE_POSITION, direction);
    }
	
	/**
	 * Removes all referenses to this component.
	 */
	public void remove(){
		this.pcs.firePropertyChange(Message.SPACESHIP_DIE.toString(), this, true);
	}
	//Only this object should be able to destroy it?
	private void hurt(int damage){
		if(hull.hurt(shield.hurt(damage)))
			this.remove();
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
    public Vector2f getVelocity() {
        return new Vector2f(velocity.x, velocity.y);
    }

	/**
	 * {@inheritDoc}
	 */
    public void setVelocity(Vector2f velocity) {
        this.moveComponent.setVelocity(velocity);
    }

	/**
	 * {@inheritDoc}
	 */
    public void setAcceleration(Vector2f acceleration) {
        this.moveComponent.setAcceleration(acceleration);
    }

	/**
	 * {@inheritDoc}
	 */
    public void setDirection(Vector2f direction) {
        this.moveComponent.setDirection(direction);
    }

	/**
	 * {@inheritDoc}
	 */
    public void setPosition(Point2f position) {
        this.moveComponent.setPosition(position);
    }
	
	/**
	 * {inheritDoc}
	 * @return 
	 */
	public float getRotVelocity() {
		return this.rotationVelocity.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public float getRotAcceleration() {
		return this.rotationAcceleration.getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setRotVelocity(float rotationVelocity) {
		this.rotationVelocity.setValue(rotationVelocity);
	}

	/**
	 * {@inheritDoc}
	 * @param rotationAcceleration
	 */
	public void setRotAcceleration(float rotationAcceleration) {
		this.rotationAcceleration.setValue(rotationAcceleration);
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public String getType() {
		return type.SPACESHIP.toString();
	}
	
    public String getWeapon() {
        return this.armsComponent.getWeapon();
    }
	
	public int getHull(){
		return this.hull.getHull();
	}

    public void setWeapon(IProjectile weapon) {
        this.armsComponent.setWeapon(weapon);
    }
    
    /**
     * Sends message to GameWorld which creates the bullet via projectilefactory
     */
    public void fireWeapon() {
    	if(armsComponent.canFire()){
    		pcs.firePropertyChange(Message.SPACESHIP_FIRE.toString(), armsComponent.fire(), true);
    	}
    }
	
	/**
	 * {@inheritDoc}
	 */
	public void calculateThrust(Timestep timestep) {
		this.thrusterComponent.calculateAceleration();
		this.thrusterComponent.calculateRotation(timestep);
	}
	
	public void repair(int repair){
		this.hull.repair(repair);;
	}
	
	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}
	public void visit(Spaceship spaceship) {
		this.remove();
	}

	public void visit(IPickup iPickup) {
		iPickup.affectMe(this);
	}
	public void visit(IProjectile projectile) {
		this.hurt(projectile.getDamage());
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
	public boolean collideDetection(ICollidable rhs) {
		return colliComp.collideDetection(rhs);
	}
	
	@Override
	public Spaceship clone() {
		return new Spaceship(this);
	}
	
	public void activateMainThruster(){
		this.thrusterComponent.activateMainThruster();
	}
	public void activateLeftThruster(){
		this.thrusterComponent.activateLeftThruster();
	}
	public void activateRightThruster(){
		this.thrusterComponent.activateRightThruster();
	}
	public void deactivateMainThruster(){
		this.thrusterComponent.deactivateMainThruster();
	}
	public void deactivateLeftThruster(){
		this.thrusterComponent.deactivateLeftThruster();
	}
	public void deactivateRightThruster(){
		this.thrusterComponent.deactivateRightThruster();
	}

	public boolean equals(Object rhs){
		if(rhs==this){
			return true;
		}else if(!(rhs instanceof Spaceship)){
			return false;
		}else{
			Spaceship other = (Spaceship)rhs;
			return 	this.direction.equals(other.direction)&&
					this.height==other.height&&
					this.position.equals(other.position)&&
					this.width==other.width;
		}
	}
	
	public boolean isMainThusterActive(){
		return thrusterComponent.isMainThusterActive();
	}
	public boolean isLeftThusterActive(){
		return thrusterComponent.isLeftThusterActive();
	}
	public boolean isRightThusterActive(){
		return thrusterComponent.isRightThusterActive();
	}

	@Override
	public Collection<? extends DrawableData> getCollectionDrawables() {
		Set<DrawableData> returnSet=new HashSet<DrawableData>();
		returnSet.add(new DrawableData(
				getPosition(),
				getHeight(),
				getWidth(),
				getDirection(),
				getType().toString()));
		
		if(thrusterComponent.isMainThusterActive()){
			returnSet.add(new DrawableData(
					new Point2f(getPosition().x-getDirection().x*25,
					getPosition().y-getDirection().y*25),
					3,
					3,
					getDirection(),
					IGameObject.type.BULLET.toString()));
		}
		if(thrusterComponent.isRightThusterActive()){
			returnSet.add(new DrawableData(
					new Point2f(getPosition().x-getDirection().x*25-getDirection().y*10,
					getPosition().y-getDirection().y*25+getDirection().x*10),
					3,
					3,
					getDirection(),
					IGameObject.type.BULLET.toString()));
		}
		if(thrusterComponent.isLeftThusterActive()){
			returnSet.add(new DrawableData(
					new Point2f(getPosition().x-getDirection().x*25+getDirection().y*10,
					getPosition().y-getDirection().y*25-getDirection().x*10),
					3,
					3,
					getDirection(),
					IGameObject.type.BULLET.toString()));
		}
		
		return returnSet;
	}
}
