package com.whathappensingandalf.howdoiflythisthing;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

public class EllipticalCollidableComponent implements ICollidableComponent{

	private Point2f position;
	private Dimension2D size;
	private Vector2f direction;
	private Area boundingBox;
	private Ellipse2D ellipse2D;
	private AffineTransform affineTransform;
	/**
	 * Constructs a CollidableComponent and creates a Ellipse2D representing this component
	 * @param position
	 * @param width
	 * @param height
	 */
	public EllipticalCollidableComponent(Point2f position, Vector2f direction, int width, int height){
		this.position= position;
		this.direction= direction;
		affineTransform= new AffineTransform();
		size= new Dimension(width, height);
		ellipse2D= new Ellipse2D.Double(position.x, position.y, width, height);
		boundingBox= new Area(ellipse2D);
	}
		
	/**
	 * Tests if this Area have collide with a Rectangle2D
	 * @param rhs- the ICollidable whose Rectangle2D to compare with
	 * @param x- the x coordinate of the upper- left corner
	 * @param y- the y coordinate of the upper- left corner
	 * @param width- the width of this component
	 * @param height- the height of this component
	 * @return true if this Area intersects rhs
	 */
	public boolean collideDetection(ICollidable rhs){
		
//		TODO- now: rotate around upper- left corner, how get to rotation around the center- point?
		affineTransform.rotate(direction.x, direction.y, ellipse2D.getCenterX(), ellipse2D.getCenterY());	//this or the angle of rotation in radius
		
		updateBoundingBox();
//		return ellipse2D.intersects(rhs.getBoundingBox().getBounds2D());
		return false;
	}
	public Area getBoundingBox(){
		updateBoundingBox();
		return boundingBox;
	}
	public void updateBoundingBox(){
		boundingBox.transform(affineTransform);
	}
}
