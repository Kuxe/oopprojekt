package com.whathappensingandalf.howdoiflythisthing;

import java.util.Set;

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
	
	public Point2f getPosition();
	
	public Vector2f getDirection();
	
	public int getHeight();
	
	public int getWidth();
	
	public String getType();
	
	public IDrawable clone();
	
	public Set<IDrawable> getSetOfDrawables();
}
