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
	private int radius;
	private Vector2f distance= new Vector2f(0 ,0);
	
	public CollidableComponent(Point2f position, int radius){
		this.position= position;
		this.radius= radius;
	}
	
	public CollidableComponent(Point2f position, int width, int height){
		this.position= position;
		this.width= width;
		this.height= height;
		radius= width;
	}
	
	public boolean collideDetection(ICollidable rhs){
		distance.sub(position, rhs.getPosition());
		return (radius+ rhs.getWidth()) < distance.length();
	}
}//end CollidableComponent