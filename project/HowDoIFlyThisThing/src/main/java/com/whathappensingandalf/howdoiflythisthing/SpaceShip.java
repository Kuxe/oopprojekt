package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;


/**
 *
 * @author Martin Nilsson
 */
public class SpaceShip implements IArmable, IMovable{

    private ArmsComponent armsComponent;
    private MoveComponent moveComponent;
    private ThrusterComponent thrusters;
    private Point2f position;
	private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f direction;
	private PropertyChangeSupport pcs;
    
    public SpaceShip(Point2f position, Vector2f direction){
		this.pcs = new PropertyChangeSupport(this);
        this.position = position;
        this.acceleration = new Vector2f();
        this.direction = direction;
        this.velocity = new Vector2f(1.0f, 2.0f);
        this.moveComponent = new MoveComponent(this.position, this.velocity, this.acceleration, this.direction);
		this.thrusters = new ThrusterComponent(this.direction);
		
    }

    public void move() {
        this.moveComponent.move();
    }
    
    public Vector2f getAcceleration() {
        return acceleration;
    }

    public Vector2f getDirection() {
        return this.direction;
    }

    public Point2f getPosition() {
        return this.position;
    }

    public Vector2f getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.moveComponent.setVelocity(velocity);
    }

    public void setAcceleration(Vector2f acceleration) {
        this.moveComponent.setAcceleration(acceleration);
    }

    public void setDirection(Vector2f direction) {
        this.moveComponent.setDirection(direction);
    }

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
    public void addPropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.addPropertyChangeListener(pcl);
	}
    public void removePropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.removePropertyChangeListener(pcl);
	}
}
