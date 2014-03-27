package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
class MoveComponent {
    
	private Point2f position;
	private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f direction;
	
	public MoveComponent(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction){
		this.position = position;
		this.velocity = velocity;
        this.acceleration = acceleration;
		this.direction = direction;
	}
	
	/**
	* Moves the component
	*/
	public void move(){
		velocity.add(acceleration);
        position.add(velocity);
	}
        
    /**
	* Sets the speed of the component.
	*/
    public void setSpeed(Vector2f velocity) {
		this.velocity.set(velocity);
    }
        
    /**
	* Sets the acceleration of the component.
	*/
    public void setAcceleration(Vector2f acceleration){
		this.acceleration.set(acceleration);
    }
        
    /**
	* Sets the direction of the component.
	*/
    public void setDirection(Vector2f direction) {
		this.direction.set(direction);
    }

    /**
	* Sets the position of the component.
	*/
    public void setPosition(Point2f position) {
		this.position.set(position);
    }
	
}
