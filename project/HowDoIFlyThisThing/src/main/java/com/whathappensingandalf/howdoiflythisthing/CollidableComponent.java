package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class CollidableComponent implements ICollidableComponent{

	private Point2f position;
	private Vector2f topLeftCorner;
	private Vector2f distanceToSide;	//vector pointing to the right (from start)
	private Vector2f distanceToTop;		//vector pointing to upwards (from start)
	private Vector2f direction;
	private Vector2f ex;
	private Vector2f ey;
	private Point2f calc;
	
	public CollidableComponent(Point2f position, Vector2f direction, int width, int height){
		
		this.position = new Point2f(position.x, position.y);	//Center point
		this.topLeftCorner = new Vector2f(-width/2, -height/2);	//To find upper left corner
		this.direction = direction;
		this.distanceToSide = new Vector2f(width/2,0);
		this.distanceToTop = new Vector2f(0,height/2);
		this.ex = new Vector2f(1,0);
		this.ey = new Vector2f(0,1);
		
		
	}

	public boolean collideDetection(ICollidable rhs) {
		
		
		return false;
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	public Point2f getLeftmostCoordinate(){
		calc = new Point2f(position.x, position.y);
		if(distanceToTop.x<0){
			calc.add(distanceToTop);
		}else{
			calc.sub(distanceToTop);
		}if(distanceToSide.x<0){
			calc.add(distanceToSide);
		}else{
			calc.sub(distanceToSide);
		}return calc;
	}
	/**
	 * @return the point most to the right (highest x- value)
	 */
	public Point2f getRightmostCoordinate(){
		calc = new Point2f(position.x, position.y);
		if(distanceToTop.x>0){
			calc.add(distanceToTop);
		}else{
			calc.sub(distanceToTop);
		}if(distanceToSide.x>0){
			calc.add(distanceToSide);
		}else{
			calc.sub(distanceToSide);
		}
		return calc;
	}
	/**
	 * @return the point in the upper corner (with the lowest y- value)
	 */
	public Point2f getTopmostCoordinate(){
		calc = new Point2f(position.x, position.y);
		if(distanceToTop.y<0){
			calc.add(distanceToTop);
		}else{
			calc.sub(distanceToTop);
		}if(distanceToSide.y<0){
			calc.add(distanceToSide);
		}else{
			calc.sub(distanceToSide);
		}
		return calc;
	}
	/**
	 * @return the point in the down corner (with the highest y- value)
	 */
	public Point2f getBottommostCoordinate(){
		calc = new Point2f(position.x,position.y);
		if(distanceToTop.y>0){
			calc.add(distanceToTop);
		}else{
			calc.sub(distanceToTop);
		}if(distanceToSide.y>0){
			calc.add(distanceToSide);
		}else{
			calc.sub(distanceToSide);
		}
		return calc;
	}
	
	public void updateBoundingBox() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Area getBoundingBox() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}//end CollidableComponent