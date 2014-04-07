package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 * 
 * Note that all gameobjects implementing this interface MUST
 * make position and direction cloneable (deep-clone) since list
 * of drawables are copied to the view to avoid threading issues.
 */

public interface IDrawable extends IListable {
	
	public Point2f getPossition();
	
	public Vector2f getDirection();
	
	public String getType();
	
	public IDrawable clone();
}
