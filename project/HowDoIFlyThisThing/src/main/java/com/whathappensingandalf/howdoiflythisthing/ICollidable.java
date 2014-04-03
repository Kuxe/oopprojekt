package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;


/**
 * @author Francine
 *
 */
public interface ICollidable extends IListable{
	
	/**
	 * @param collidableComponent
	 */
	public boolean collideDetection(ICollidable rhs);
	/**
	 * @return the height of this component
	 */
	public int getHeight();
	/**
	 * @return the width of this component
	 */
	public int getWidth();
	/**
	 * @return the position of this component
	 */
	public Point2f getPosition();
	/**
	 * @return
	 */
	public Rectangle2D getBoundingBox();
	/**
	 * @return the type of this component
	 */
	public String getType();
}
