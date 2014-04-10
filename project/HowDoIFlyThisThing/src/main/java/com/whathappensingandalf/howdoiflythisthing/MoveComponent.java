package com.whathappensingandalf.howdoiflythisthing;

import utils.VecmathUtils;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 * 
 *	A class for handeling movement calculation common for several (if not all)
 *	classes that implement IMovable. The reason is to avoid duplicate code. 
 *	This solution is inspired by the Entity-component-system design. 
 */
class MoveComponent {
    /**
	 * Referenses to variables needed for calculating the movement.
	 */
	private Point2f position;
	private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f direction;
	private Float rotationVelocity;
	
	/**
	 * A constructor for creating a MoveComponent. The parameters are the instanses 
	 * to be shared between the movecomponent and the clas that owns it. 
	 * @param position
	 * @param velocity
	 * @param acceleration
	 * @param direction 
	 */
	public MoveComponent(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, Float rotationVelocity){
		this.position = position;
		this.velocity = velocity;
        this.acceleration = acceleration;
		this.direction = direction;
		this.rotationVelocity = rotationVelocity;
	}
	
	/**
	* Moves the component
	*/
	public void move(Timestep timestep){
		VecmathUtils.rotateVectorV2(direction, rotationVelocity);
		VecmathUtils.setAngleFromVector(acceleration, direction);
		velocity.add(acceleration);
        position.add(new Vector2f((float) (velocity.x * timestep.getDelta()), (float) (velocity.y * timestep.getDelta())));
	}
        
    /**
	* Sets the speed of the component.
	*/
    public void setVelocity(Vector2f velocity) {
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
