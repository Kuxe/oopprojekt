package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;


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
	private Float rotationVelocity;
	private Float rotationAcceleration;
	private final Vector2f weaponPipePosition;
	/**
	 * A instance of PropertyChangeSupport so that this class can be listend to.
	 */
	private PropertyChangeSupport pcs;
	
	private int hull;
	
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
		this.pcs = new PropertyChangeSupport(this);
        this.position = position;
        this.acceleration = new Vector2f(0.0f, 0.0f);
        this.direction = direction;
        this.velocity = new Vector2f(0.0f, 0.0f);
        rotationAcceleration = new Float(0.0f);
        rotationVelocity = new Float(0.0f);
        this.weaponPipePosition = new Vector2f(0.0f, height/2); //Should fire from middle of spaceships just infront of it
		this.hull=100;
        this.moveComponent = new MoveComponent(this.position, this.velocity, this.acceleration, this.direction, rotationAcceleration);
		this.thrusterComponent = new ThrusterComponent(this.acceleration, this.direction, rotationAcceleration, rotationVelocity);
		this.armsComponent = new ArmsComponent(this.position, velocity, acceleration, this.direction, weaponPipePosition);
		this.colliComp = new CollidableComponent(position, direction, width, height);
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
    public void move() {
		this.calculateThrust();
        this.moveComponent.move();
    }
	
	/**
	 * Removes all referenses to this component.
	 */
	public void remove(){
		this.pcs.firePropertyChange(Message.SPACESHIP_DIE.toString(), this, true);
	}
	//Only this object should be able to destroy it?
	private void hurt(int damage){
		hull-=damage;
		if(hull<=0)
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
		return this.rotationVelocity.floatValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public float getRotAcceleration() {
		return this.rotationAcceleration.floatValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setRotVelocity(float rotationVelocity) {
		this.rotationVelocity=rotationVelocity;
	}

	/**
	 * {@inheritDoc}
	 * @param rotationAcceleration
	 */
	public void setRotAcceleration(float rotationAcceleration) {
		this.rotationAcceleration=rotationAcceleration;
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
	
    public ArmsComponent getWeapon() {
        return this.armsComponent;
    }

    public void setWeapon(ArmsComponent weapon) {
        this.armsComponent=weapon;
    }
    
    /**
     * Sends message to GameWorld which creates the bullet via projectilefactory
     */
    public void fireWeapon() {
		pcs.firePropertyChange(Message.SPACESHIP_FIRE.toString(), armsComponent.fire(), true);
    }
	
	/**
	 * {@inheritDoc}
	 */
	public void calculateThrust() {
		this.thrusterComponent.calculateAceleration();
	}
	
	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}
	public void visit(Spaceship spaceship) {
		this.remove();
	}

	public void visit(Projectile projectile) {
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
	@Override
	public boolean collideDetection(ICollidable rhs) {
		return colliComp.collideDetection(rhs);
	}
	@Override
	public Area getBoundingBox() {
		return colliComp.getBoundingBox();
	}
	
	public Point2f getPossition() {
		return this.position;
	}
	@Override
	public Spaceship clone() {
		return new Spaceship(this);
	}
	
	public void activateMainThruste(){
		this.thrusterComponent.activateMainThruster();
	}
	public void activateLeftThruste(){
		this.thrusterComponent.activateLeftThruster();
	}
	public void activateRightThruste(){
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
}
