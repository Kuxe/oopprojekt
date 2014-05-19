package com.whathappensingandalf.howdoiflythisthing;


import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import utils.VecmathUtils.*;

/**
 *
 * @author Francine Mäkelä
 */
public class CollidableComponent{
	
	private final Point2f position;
	private final double radius;
	private Vector2f distance = new Vector2f(0 ,0);
	
	public CollidableComponent(Point2f position, double radius){
		this.position= position;
		this.radius= radius;
		//this.distance
	}
	
	public CollidableComponent(Point2f position, int width, int height){
		this.position= position;
		radius= ((height+ width)/4.0f);
	}
	
	public boolean collideDetection(ICollidable rhs){
		distance.sub(position, rhs.getPosition());
		return (radius+ rhs.getWidth()/2.0) > distance.length();
	}
	
}//end CollidableComponent