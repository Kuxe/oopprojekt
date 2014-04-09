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
	private Vector2f width;
	private Vector2f height;
	private Vector2f direction;
	private Vector2f ex;
	private Vector2f ey;
	private Point2f calc;
	
	public CollidableComponent(Point2f position, Vector2f direction, int width, int height){
		
		this.position = new Point2f(position.x, position.y);
		this.topLeftCorner = new Vector2f(-width/2, -height/2);
		this.direction = direction;
		this.width = new Vector2f(width,0);
		this.height = new Vector2f(0,height);
		this.ex = new Vector2f(1,0);
		this.ey = new Vector2f(0,1);
		
		
	}
	
	public void updateBoundingBox() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Area getBoundingBox() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public boolean collideDetection(ICollidable rhs) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	public Point2f getLeftmostCoordinate(){
		calc = new Point2f(0,0);
		if(height.x<0){
			calc.add(height);
		}else{
			calc.add(height);
		}if(width.x<0){
			calc.add(width);
		}else{
			calc.add(width);
		}return calc;
	}
	public Point2f getRightmostCoordinate(){
		calc = new Point2f(0,0);
		if(height.x>0){
			calc.add(height);
		}else{
			calc.add(width);
		}if(width.x>0){
			calc.add(width);
		}else{
			calc.add(width);
		}return calc;
	}
	public Point2f getTopmostCoordinate(){
		calc = new Point2f(0,0);
		if(height.y<0){
			calc.add(height);
		}else{
			calc.add(height);
		}if(width.y<0){
			calc.add(width);
		}else{
			calc.add(width);
		}return calc;
	}
	public Point2f getBottommostCoordinate(){
		calc = new Point2f(position.x,position.y);
		if(height.y>0){
			calc.add(height);
		}else{
			calc.add(height);
		}if(width.y>0){
			calc.add(width);
		}else{
			calc.add(width);
		}return calc;
	}
}
