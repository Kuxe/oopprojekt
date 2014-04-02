package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public class CollidableComponent{
	
	Area area;
	
	/**
	 * @param collidObj
	 */
	public CollidableComponent(ICollidable collidObj){
		area= new Area(collidObj.getBoundingBox());
	}
	
	/**
	 * Tests if this Area have collide with a Rectangle2D
	 * @param rhs- the ICollidable whose Rectangle2D to compare with
	 * @return true if this Area intersects rhs
	 */
	public boolean collideDetection(ICollidable rhs){
		if(area.intersects(rhs.getBoundingBox())){
			return true;
		}else{
			return false;
		}
	}
}
