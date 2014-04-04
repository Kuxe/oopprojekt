package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public interface ICollidableComponent {
	
	public void updateBoundingBox(Point2f position, int width, int height);
	public Rectangle2D getBoundingBox(Point2f position, int width, int height);
	public boolean collideDetection(ICollidable rhs);
}
