package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;

/**
 * @author Francine
 *
 */
public class CollidableComponent{
	
	Area area;
	Rectangle2D rect2D;
	
	/**
	 * @param collidObj
	 */
	public CollidableComponent(Point2f position, double width, double height){
		rect2D= new Rectangle2D.Double(position.x, position.y, width, height);
		area= new Area(rect2D);
	}
	
	/**
	 * Tests if this Area have collide with a Rectangle2D
	 * @param rhs- the ICollidable whose Rectangle2D to compare with
	 * @return true if this Area intersects rhs
	 */
	public boolean collideDetection(ICollidable rhs){
		
		//rect2D.setRect(x, y, w, h);
//		rect2D.intersects(rect2D)
		
		if(area.intersects(rhs.getBoundingBox())){
			return true;
		}else{
			return false;
		}
	}
	public Rectangle2D getBoundingBox() {
		return rect2D;
	}
	public void updateRectangle(double x, double y, double width, double height){
		rect2D.setRect(x, y, width, height);
//		rect2D
	}
}
