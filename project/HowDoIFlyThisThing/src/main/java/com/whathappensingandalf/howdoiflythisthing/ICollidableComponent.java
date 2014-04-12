package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public interface ICollidableComponent {
	
	public void updateBoundingBox();
	public Area getBoundingBox();
	/**
	 * @param rhs- the component to compare with
	 * @return true if a collision between this component and rhs is found, false otherwise
	 */
	public boolean collideDetection(ICollidable rhs);
}
