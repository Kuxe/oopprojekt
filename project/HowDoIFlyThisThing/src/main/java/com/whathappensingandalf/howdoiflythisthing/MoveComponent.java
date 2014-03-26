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
    
	private Point2f point;
	private Vector2f vector;
	
	public MoveComponent(Point2f point, Vector2f vector){
		this.point = point;
		this.vector = vector;
	}
	
	/**
	 * Moves the component
	 */
	public void move(){
		point.add(vector);
	}
}
