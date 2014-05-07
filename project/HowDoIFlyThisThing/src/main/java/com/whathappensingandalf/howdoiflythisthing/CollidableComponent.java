package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.jbox2d.dynamics.World;

import utils.VecmathUtils.*;

/**
 *
 * @author Francine Mäkelä
 */
public class CollidableComponent implements ICollidableComponent{

	private Point2f position;
	private int width;
	private int height;
	private double radius;
	private Vector2f distance= new Vector2f(0 ,0);
	
	public CollidableComponent(Point2f position, double radius){
		this.position= position;
		this.radius= radius;
	}
	
	public CollidableComponent(Point2f position, int width, int height){
		this.position= position;
		this.width= width/2;
		this.height= height/2;
		radius= ((height+ width)/4);
	}
	
	public boolean collideDetection(ICollidable rhs){
		distance.sub(position, rhs.getPosition());
		return (radius+ rhs.getWidth()/2) > distance.length();
	}
}//end CollidableComponent