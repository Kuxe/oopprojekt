/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin
 */
class MoveComponent {
    
	private Point2f position;
	private Vector2f velocity;
        private Vector2f acceleration;
        private Vector2f direction;
	
	public MoveComponent(Point2f point, Vector2f vector){
            this.position = point;
            this.velocity = vector;
	}
	
	/**
	 * Moves the component
	 */
	public void move(){
            velocity.add(acceleration);
            position.add(velocity);
	}
        
        /**
	 * Sets the acceleration of the component
	 */
        public void setAcceleration(Vector2f acceleration){
            this.acceleration.set(acceleration);
        }
}
