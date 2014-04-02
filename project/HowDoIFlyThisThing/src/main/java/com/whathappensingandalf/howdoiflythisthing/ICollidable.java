package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;


/**
 * @author Francine
 *
 */
public interface ICollidable{
	
	/**
	 * @param collidableComponent
	 */
	public boolean collideDetection(ICollidable collidObj);
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

}
