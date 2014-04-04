package com.whathappensingandalf.howdoiflythisthing;

import java.awt.Dimension;
import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public class CollidableComponent implements ICollidableComponent{
	
	private Point2f position;
	private Dimension2D size;
	private Area area;
	private Rectangle2D rect2D;
	private Rectangle2D boundingBox;
	/**
	 * Constructs a CollidableComponent and creates a Rectangle2D representing this component
	 * @param position
	 * @param width
	 * @param height
	 */
	public CollidableComponent(Point2f position, int width, int height){
		this.position= position;
		size= new Dimension(width, height);
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
	public boolean collideDetection(ICollidable rhs){
		
		rect2D.setRect(position.x, position.y, size.getWidth(), size.getHeight());

		if(rect2D.intersects(rhs.getBoundingBox(rhs.getPosition().x, rhs.getPosition().y, rhs.getWidth(), rhs.getHeight()))){
			return true;
		}else{
			return false;
		}
	}
	public Rectangle2D getBoundingBox() {
		updateBoundingBox(position, size.getWidth(), size.getHeight());
		return boundingBox;
	}
	public void updateBoundingBox(){
		boundingBox.setRect(position.x, position.y, size.getWidth(), size.getHeight());
	}
}
