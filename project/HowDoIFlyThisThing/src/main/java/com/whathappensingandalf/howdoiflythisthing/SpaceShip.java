package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class SpaceShip implements IArmable, IMovable{

    private ArmsComponent armsComponent;
    private MoveComponent moveComponent;
    private Point2f position;
    private Vector2f acceleration;
    private Vector2f direction;
    private Vector2f speed;
    private Thruster[] thruster = new Thruster[3];
    
    public SpaceShip(Point2f position, Vector2f direction){
        this.position = position;
        this.acceleration = new Vector2f();
        this.direction = direction;
        this.speed = new Vector2f(1.0f, 2.0f);
        this.moveComponent = new MoveComponent(position, speed);
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

    public Vector2f getSpeed() {
        return this.speed;
    }

    public void setSpeed(Vector2f velocity) {
        this.moveComponent.setSpeed(velocity);
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
}
