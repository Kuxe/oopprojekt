package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;

/**
 * @author Francine
 *
 */
public interface ICollidableComponent {
	
	public void updateBoundingBox(double x, double y, double width, double height);
	public Rectangle2D getBoundingBox(float x, float y, double width, double height);
	public boolean collideDetection(ICollidable rhs, double x, double y, double width, double height);
}
