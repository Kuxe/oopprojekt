package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public class CollidableComponent implements ICollidableComponent{
	
	private Point2f position;
	private double width;
	private double height;
	private Area area;
	private Rectangle2D rect2D;
	private Rectangle2D boundingBox;
	/**
	 * Constructs a CollidableComponent and creates a Rectangle2D representing this component
	 * @param position
	 * @param width
	 * @param height
	 */
	public CollidableComponent(Point2f position, double width, double height){
		this.position= position;
		this.width= width;
		this.height= height;
		rect2D= new Rectangle2D.Double(position.x, position.y, width, height);
		area= new Area(rect2D);
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
	public boolean collideDetection(ICollidable rhs, double x, double y, double width, double height){
		
		rect2D.setRect(x, y, width, height);

		if(rect2D.intersects(rhs.getBoundingBox(rhs.getPosition().x, rhs.getPosition().y, rhs.getWidth(), rhs.getHeight()))){
			return true;
		}else{
			return false;
		}
	}
	public Rectangle2D getBoundingBox(double x, double y, double width, double height) {
		updateBoundingBox(x, y, width, height);
		return boundingBox;
	}
	public void updateBoundingBox(double x, double y, double width, double height){
		boundingBox.setRect(x, y, width, height);
	}
}
