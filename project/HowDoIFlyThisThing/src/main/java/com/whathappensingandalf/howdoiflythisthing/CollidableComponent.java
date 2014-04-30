package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.jbox2d.dynamics.World;

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
	private Point2f calcLeft;
	private Point2f calcRight;
	private Point2f calcTop;
	private Point2f calcBottom;
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
		calcLeft= new Point2f(0, 0);
		calcRight= new Point2f(0, 0);
		calcTop= new Point2f(0, 0);
		calcBottom= new Point2f(0, 0);
	}
	/**
	 * {@inheritDoc}
	 */
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
				alfaVector.sub(rhs.getRightmostCoordinate(), getLeftmostCoordinate());
				alfa= ex.angle(alfaVector);
				if(getLeftmostCoordinate().y > rhs.getRightmostCoordinate().y){
					betaVector.sub(getTopmostCoordinate(), getLeftmostCoordinate());
					beta= ex.angle(betaVector);
					return beta- alfa > 0;
				}else{
					betaVector.sub(getBottommostCoordinate(), getLeftmostCoordinate());
					beta= ex.angle(betaVector);
					return beta- alfa > 0;
				}
			}else if(getRightmostCoordinate().x > rhs.getLeftmostCoordinate().x){
				alfaVector.sub(rhs.getLeftmostCoordinate(), getRightmostCoordinate());
				alfa= ex.angle(alfaVector);
				if(getRightmostCoordinate().y > rhs.getLeftmostCoordinate().y){
					betaVector.sub(getTopmostCoordinate(), getRightmostCoordinate());
					beta= ex.angle(betaVector);

					return alfa- beta > 0;
				}else{
					betaVector.sub(getBottommostCoordinate(), getRightmostCoordinate());
					beta= ex.angle(betaVector);

					return alfa- beta > 0;
				}
			}else if(getTopmostCoordinate().y < rhs.getBottommostCoordinate().y){
				alfaVector.sub(rhs.getBottommostCoordinate(), getTopmostCoordinate());
				alfa= ey.angle(alfaVector);
				if(getTopmostCoordinate().x > rhs.getBottommostCoordinate().x){
					betaVector.sub(getLeftmostCoordinate(), getTopmostCoordinate());
					beta= ey.angle(betaVector);

					return beta- alfa > 0;
				}else{
					betaVector.sub(getRightmostCoordinate(), getTopmostCoordinate());
					beta= ey.angle(betaVector);

					return beta- alfa > 0;
				}
			}else if(getBottommostCoordinate().y > rhs.getTopmostCoordinate().y){
				alfaVector.sub(rhs.getTopmostCoordinate(), getBottommostCoordinate());
				alfa= ey.angle(alfaVector);
				if(getBottommostCoordinate().x > rhs.getTopmostCoordinate().x){
					betaVector.sub(getLeftmostCoordinate(), getBottommostCoordinate());
					beta= ey.angle(betaVector);

					return alfa- beta > 0;
				}else{
					betaVector.sub(getRightmostCoordinate(), getBottommostCoordinate());
					beta= ey.angle(betaVector);

					return alfa- beta > 0;
				}
			}
		}
		//		throw new UnsupportedOperationException("Not supported yet.");
		return false;
	}
	/**
	 * @return the point most to the left (lowest x- value)
	 */
	public Point2f getLeftmostCoordinate(){
		calcLeft.set(position.x, position.y);
		if(distanceToTop.x<0){
			calcLeft.add(distanceToTop);
		}else{
			calcLeft.sub(distanceToTop);
		}if(distanceToSide.x<0){
			calcLeft.add(distanceToSide);
		}else{
			calcLeft.sub(distanceToSide);
		}return calcLeft;
	}
	/**
	 * @return the point most to the right (highest x- value)
	 */
	public Point2f getRightmostCoordinate(){
		calcRight.set(position.x, position.y);
		if(distanceToTop.x>0){
			calcRight.add(distanceToTop);
		}else{
			calcRight.sub(distanceToTop);
		}if(distanceToSide.x>0){
			calcRight.add(distanceToSide);
		}else{
			calcRight.sub(distanceToSide);
		}
		return calcRight;
	}
	/**
	 * @return the point in the upper corner (with the lowest y- value)
	 */
	public Point2f getTopmostCoordinate(){
		calcTop.set(position.x, position.y);
		if(distanceToTop.y<0){
			calcTop.add(distanceToTop);
		}else{
			calcTop.sub(distanceToTop);
		}if(distanceToSide.y<0){
			calcTop.add(distanceToSide);
		}else{
			calcTop.sub(distanceToSide);
		}
		return calcTop;
	}
	/**
	 * @return the point in the down corner (with the highest y- value)
	 */
	public Point2f getBottommostCoordinate(){
		calcBottom.set(position.x,position.y);
		if(distanceToTop.y>0){
			calcBottom.add(distanceToTop);
		}else{
			calcBottom.sub(distanceToTop);
		}if(distanceToSide.y>0){
			calcBottom.add(distanceToSide);
		}else{
			calcBottom.sub(distanceToSide);
		}
		return calcBottom;
	}
}//end CollidableComponent