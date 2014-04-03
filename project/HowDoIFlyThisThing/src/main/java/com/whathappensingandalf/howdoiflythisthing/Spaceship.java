package com.whathappensingandalf.howdoiflythisthing;

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
public class Spaceship implements IMovable, IThrustable, ICollidable, IGameObject{
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
    private double width;
	private double height;
    private Point2f position;
	private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f direction;
	private Float rotationVelocity;
	private Float rotationAcceleration;
	/**
	 * A instance of PropertyChangeSupport so that this class can be listend to.
	 */
	private PropertyChangeSupport pcs;
	
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
    public Spaceship(Point2f position, Vector2f direction, double width, double height){
		this.pcs = new PropertyChangeSupport(this);
        this.position = position;
        this.acceleration = new Vector2f();
        this.direction = direction;
        this.velocity = new Vector2f(1.0f, 2.0f);
        this.moveComponent = new MoveComponent(this.position, this.velocity, this.acceleration, this.direction);
		this.thrusterComponent = new ThrusterComponent(this.acceleration, this.direction, rotationAcceleration, rotationVelocity);
		this.armsComponent = new ArmsComponent(this.position, velocity, acceleration, this.direction);
		this.colliComp = new CollidableComponent(position, width, height);
    }
	
	/**
	 * {@inheritDoc}
	 */
    public void move() {
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
		//Decrease Shield or Hull.
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
	
	
	public void collide(ICollidable rhs) {
		
		String s = rhs.getType();
		
		if(s.equals(type.SPASESHIP.toString())){
			this.remove();
		}else if(s.equals(type.PROJECTILE.toString())){
			this.hurt(1);//Should depond on the manner of projectile that you colide with.
		}
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
	public Rectangle2D getBoundingBox() {
		return colliComp.getBoundingBox();
	}
}
