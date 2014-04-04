package com.whathappensingandalf.howdoiflythisthing;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
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
//	private Rectangle2D boundingBox;
	private Area boundingBox;
	private AffineTransform affineTransform;
	/**
	 * Constructs a CollidableComponent and creates a Rectangle2D representing this component
	 * @param position
	 * @param width
	 * @param height
	 */
	public CollidableComponent(Point2f position, int width, int height){
		this.position= position;
		affineTransform= new AffineTransform();
		size= new Dimension(width, height);
		rect2D= new Rectangle2D.Double(position.x, position.y, width, height);
		area= new Area(rect2D);
	}
	
//	TODO- position should be area:s position?
//	TODO- rect2D has to be the boundingbox, but we can not transform it? BUt we CAN transform it and THEN update the area, or well,
//			we could do that if I can find a way to set the values of the area
	
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
		
//		TODO- new parameter; rotationvector
		affineTransform.rotate(vecx, vecy, x, y);	//this or the angle of rotation in radians
		
//		TODO- set size at area, not rect2D
		rect2D.setRect(position.x, position.y, size.getWidth(), size.getHeight());
		
//		Update area, how?
		area.transform(affineTransform);

		return area.intersects(rhs.getBoundingBox());
	}
	
//	the boundingbox should be an Area, which means this method should return Area
	public Area getBoundingBox() {
		updateBoundingBox();
		return boundingBox;
	}
	public void updateBoundingBox(){
		boundingBox.setRect(position.x, position.y, size.getWidth(), size.getHeight());
	}

}
