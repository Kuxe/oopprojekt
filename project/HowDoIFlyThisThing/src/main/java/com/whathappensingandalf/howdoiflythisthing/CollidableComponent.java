package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;
import utils.VecmathUtils.*;

/**
 *
 * @author Francine Mäkelä
 */
public class CollidableComponent implements ICollidableComponent{

	private Point2f position;
	private Vector2f distanceToSide;	//vector pointing to the right (from start)
	private Vector2f distanceToTop;		//vector pointing to upwards (from start)
	private Vector2f direction;
	private Vector2f alfaVector;
	private Vector2f betaVector;
	private Vector2f ex;
	private Vector2f ey;
	private Point2f calc;
	private float alfa;
	private float beta;
	
	/**
	 * @param position - a point describing the center of the component
	 * @param direction - a vector describing the direction of the component
	 * @param width - the width of the component
	 * @param height - the height of the component
	 */
	public CollidableComponent(Point2f position, Vector2f direction, int width, int height){
		this.direction = direction;
		this.position = position;
		distanceToSide = new Vector2f(width/2,0);
		distanceToTop = new Vector2f(0,height/2);
		ex = new Vector2f(1,0);
		ey = new Vector2f(0,1);
		alfaVector= new Vector2f(0, 0);
		betaVector= new Vector2f(0, 0);
		alfa= 0.0f;
		beta= 0.0f;
		calc= new Point2f(0, 0);
	}

	public boolean collideDetection(ICollidable rhs) {
		utils.VecmathUtils.setAngleFromVector(distanceToTop, direction);
		utils.VecmathUtils.setAngleFromVector(distanceToSide, direction);
		utils.VecmathUtils.rotateVector(distanceToSide, Math.PI/2);

		/*
		if(getLeftmostCoordinate().x > rhs.getRightmostCoordinate().x || getRightmostCoordinate().x < rhs.getLeftmostCoordinate().x ||
				getTopmostCoordinate().y > rhs.getBottommostCoordinate().y || getBottommostCoordinate().y < rhs.getTopmostCoordinate().y){
 */
		if(getLeftmostCoordinate().x > rhs.getRightmostCoordinate().x ){
			return false;
		}else if(getRightmostCoordinate().x < rhs.getLeftmostCoordinate().x){
			return false;
		}else if(getTopmostCoordinate().y > rhs.getBottommostCoordinate().y){
			return false;
		}else if(getBottommostCoordinate().y < rhs.getTopmostCoordinate().y){
			return false;
		}
		else{
			if(getLeftmostCoordinate().x < rhs.getRightmostCoordinate().x){
				if(getLeftmostCoordinate().y > rhs.getRightmostCoordinate().y){
					alfaVector.sub(rhs.getRightmostCoordinate(), getLeftmostCoordinate());
					alfa= ex.angle(alfaVector);
					
					betaVector.sub(getTopmostCoordinate(), getLeftmostCoordinate());
					beta= ex.angle(betaVector);
					
					return beta- alfa > 0;
				}else{
					alfaVector.sub(rhs.getRightmostCoordinate(), getLeftmostCoordinate());
					alfa= ex.angle(alfaVector);
					
					betaVector.sub(getBottommostCoordinate(), getLeftmostCoordinate());
					beta= ex.angle(betaVector);
					
					return beta- alfa > 0;
				}
			}else if(getRightmostCoordinate().x < rhs.getLeftmostCoordinate().x){
				if(getRightmostCoordinate().y > rhs.getLeftmostCoordinate().y){
					alfaVector.sub(rhs.getLeftmostCoordinate(), getRightmostCoordinate());
					alfa= ex.angle(alfaVector);
					
					betaVector.sub(getTopmostCoordinate(), getRightmostCoordinate());
					beta= ex.angle(betaVector);
					
					return alfa- beta > 0;
				}else{
					alfaVector.sub(rhs.getLeftmostCoordinate(), getRightmostCoordinate());
					alfa= ex.angle(alfaVector);
					
					betaVector.sub(getBottommostCoordinate(), getRightmostCoordinate());
					beta= ex.angle(betaVector);
					
					return alfa- beta > 0;
				}
			}else if(getTopmostCoordinate().y < rhs.getBottommostCoordinate().y){
				if(getTopmostCoordinate().x > rhs.getBottommostCoordinate().x){
					alfaVector.sub(rhs.getBottommostCoordinate(), getTopmostCoordinate());
					alfa= ey.angle(alfaVector);
					
					betaVector.sub(getLeftmostCoordinate(), getTopmostCoordinate());
					beta= ey.angle(betaVector);
					
					return beta- alfa > 0;
				}else{
					alfaVector.sub(rhs.getBottommostCoordinate(), getTopmostCoordinate());
					alfa= ey.angle(alfaVector);
					
					betaVector.sub(getRightmostCoordinate(), getTopmostCoordinate());
					beta= ey.angle(betaVector);
					
					return beta- alfa > 0;
				}
			}else if(getBottommostCoordinate().y > rhs.getTopmostCoordinate().y){
				if(getBottommostCoordinate().x > rhs.getTopmostCoordinate().x){
					alfaVector.sub(rhs.getTopmostCoordinate(), getBottommostCoordinate());
					alfa= ey.angle(alfaVector);
					
					betaVector.sub(getLeftmostCoordinate(), getBottommostCoordinate());
					beta= ey.angle(betaVector);
					
					return alfa- beta > 0;
				}else{
					alfaVector.sub(rhs.getTopmostCoordinate(), getBottommostCoordinate());
					alfa= ey.angle(alfaVector);
					
					betaVector.sub(getRightmostCoordinate(), getBottommostCoordinate());
					beta= ey.angle(betaVector);
					
					return alfa- beta > 0;
				}
			}
		}
//		throw new UnsupportedOperationException("Not supported yet.");
		return false;
	}
	public Point2f getLeftmostCoordinate(){
		calc.set(position.x, position.y);
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
		calc.set(position.x, position.y);
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
		calc.set(position.x, position.y);
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
		calc.set(position.x,position.y);
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