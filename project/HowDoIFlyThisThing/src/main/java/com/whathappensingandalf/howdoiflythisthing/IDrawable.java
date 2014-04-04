package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;


public interface IDrawable {
	
	public Point2f getPossition();
	
	public Vector2f getDirection();
	
	public String getIdentifier();
}
