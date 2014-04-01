package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;


/**
 * @author Francine
 *
 */
public interface ICollidable{
	
//	TODO- should take a parameter?
	public void collide(ICollidable rhs);
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
	 * @return the type of this component
	 */
	public String getType();
}
