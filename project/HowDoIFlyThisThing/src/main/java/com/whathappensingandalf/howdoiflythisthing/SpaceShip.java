package com.whathappensingandalf.howdoiflythisthing;

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
    
    public SpaceShip(Point2f position, Vector2f direction){
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
    
    public Weapon getWeapon() {
        return this.armsComponent;
    }

    public void setWeapon(Weapon weapon) {
        this.armsComponent=weapon;
    }

    
}
