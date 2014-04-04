package com.whathappensingandalf.howdoiflythisthing;

import java.awt.Dimension;
import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

public class EllipticalCollidableComponent implements ICollidableComponent{

	private Point2f position;
	private Dimension2D size;
	private Area area;
	private Ellipse2D ellipse2D;
	private Rectangle2D boundingBox;
	/**
	 * Constructs a CollidableComponent and creates a Ellipse2D representing this component
	 * @param position
	 * @param width
	 * @param height
	 */
	public EllipticalCollidableComponent(Point2f position, int width, int height){
		size= new Dimension(width, height);
		ellipse2D= new Ellipse2D.Double(position.x, position.y, width, height);
		area= new Area(ellipse2D);
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
		
		ellipse2D.setFrame(position.x, position.y, size.getWidth(), size.getHeight());
		
		return ellipse2D.intersects(rhs.getBoundingBox());
	}
	public Rectangle2D getBoundingBox(){
		updateBoundingBox();
		return boundingBox;
	}
	public void updateBoundingBox(){
		boundingBox.setRect(position.x, position.y, size.getWidth(), size.getHeight());
	}
}
