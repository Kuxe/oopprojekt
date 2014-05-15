package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import utils.VecmathUtils.*;

/**
 *
 * @author Francine Mäkelä
 */
public class CollidableComponent implements ICollidableComponent{
	
	private Point2f position;
	private double radius;
	private Vector2f distance= new Vector2f(0 ,0);
	
	public CollidableComponent(Point2f position, double radius){
		this.position= position;
		this.radius= radius;
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