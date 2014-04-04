package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public interface ICollidableComponent {
	
	public void updateBoundingBox();
	public Rectangle2D getBoundingBox();
	public boolean collideDetection(ICollidable rhs);
}
