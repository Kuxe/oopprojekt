package com.whathappensingandalf.howdoiflythisthing;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * @author Francine
 *
 */
public class CollidableComponent implements ICollidableComponent{
	
	private Point2f position;
	private Dimension2D size;
	private Vector2f direction;
	private Rectangle2D rect2D;	//Only used to create boundingBox
	private Area boundingBox;
	private AffineTransform affineTransform;
	/**
	 * Constructs a CollidableComponent and creates a Rectangle2D representing this component
	 * @param position
	 * @param width
	 * @param height
	 */
	public CollidableComponent(Point2f position, Vector2f direction, int width, int height){
		this.position= position;
		this.direction= direction;
		affineTransform= new AffineTransform();
		size= new Dimension(width, height);
		rect2D= new Rectangle2D.Double(position.x, position.y, width, height);
		boundingBox= new Area(rect2D);
	}
	
	/**
	 * Tests if this Area have collide with a Rectangle2D
	 * @param rhs- the ICollidable whose Rectangle2D to compare with
	 * @return true if this Area intersects rhs
	 */
	public boolean collideDetection(ICollidable rhs){
		
//		TODO- new parameter; rotationvector
		affineTransform.rotate(direction.x, direction.y, rect2D.getCenterX(), rect2D.getCenterY());	//this or the angle of rotation in radius
		
//		affineTransform.createTransformedShape(shape)- shape rotates and then creates a NEW Shape
//			Creates a new Area object that contains the same geometry as this Area transformed by the specified AffineTransform.
//		creates a new Shape
//		Rectangle2D r= (Rectangle2D)affineTransform.createTransformedShape(rect2D);
		
		updateBoundingBox();
		
		return boundingBox.intersects(rhs.getBoundingBox().getBounds2D());
	}
	public Area getBoundingBox() {
		updateBoundingBox();
		return boundingBox;
	}

	@Override
	public void updateBoundingBox() {
		boundingBox.transform(affineTransform);
	}

}
