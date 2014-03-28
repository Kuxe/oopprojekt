package com.whathappensingandalf.howdoiflythisthing;

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
public class Spaceship implements IMovable, IThrustable{
	/**
	 * Different components for avoiding duplicate code.
	 */
    private ArmsComponent armsComponent;
    private MoveComponent moveComponent;
    private ThrusterComponent thrusterComponent;
	/**
	 * The nessesary information for moving the Spaceship.
	 */
    private Point2f position;
	private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f direction;
	/**
	 * A instance of PropertyChangeSupport so that this class can be listend to.
	 */
	private PropertyChangeSupport pcs;
    
	/**
	 * A constructor for creating a Spaceship in the given possition and faceing
	 * in the given direction.
	 * @param position
	 * @param direction 
	 */
    public Spaceship(Point2f position, Vector2f direction){
		this.pcs = new PropertyChangeSupport(this);
        this.position = position;
        this.acceleration = new Vector2f();
        this.direction = direction;
        this.velocity = new Vector2f(1.0f, 2.0f);
        this.moveComponent = new MoveComponent(this.position, this.velocity, this.acceleration, this.direction);
		this.thrusterComponent = new ThrusterComponent(this.acceleration, this.direction);
		
    }
	
	/**
	 * {@inheritDoc}
	 */
    public void move() {
        this.moveComponent.move();
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
        return this.direction;
    }

	/**
	 * {@inheritDoc}
	 */
    public Point2f getPosition() {
        return this.position;
    }

	/**
	 * {@inheritDoc}
	 */
    public Vector2f getVelocity() {
        return this.velocity;
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
    
    public ArmsComponent getWeapon() {
        return this.armsComponent;
    }

    public void setWeapon(ArmsComponent weapon) {
        this.armsComponent=weapon;
    }
    public Projectile fireWeapon() {
		return armsComponent.fire();	
    }
	
	/**
	 * {@inheritDoc}
	 */
	public void calculateThrust() {
		this.thrusterComponent.calculateAceleration();
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
}
