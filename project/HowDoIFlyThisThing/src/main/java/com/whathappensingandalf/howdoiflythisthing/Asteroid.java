package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class Asteroid implements ICollidable, IGameObject, IDrawable, Cloneable{

	
	private CollidableComponent colliComp;
	Point2f position;
	int width;
	int height;
	
	public Asteroid (Point2f position, int width, int height){
		this.position = position;
		this.width = width;
		this.height = height;
		this.colliComp = new CollidableComponent(position, new Vector2f(1,0), width, height);
	}
	
	/**
	 * Deep-copy CTOR
	 * @param asteroid
	 */
	public Asteroid(Asteroid asteroid) {
		this(asteroid.getPosition(), asteroid.getWidth(), asteroid.getHeight());
	}
	
	public boolean collideDetection(ICollidable rhs) {
		return this.colliComp.collideDetection(rhs);
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public Point2f getPosition() {
		return new Point2f(position.x, position.y);
	}

	public String getType() {
		return type.ASTEROID.toString();
	}

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(Spaceship spaceship) {
		//Nothing should happen.
	}

	public void visit(Projectile projectile) {
		//Nothing should happen.
	}
	
	public void visit(Asteroid asteroid) {
		//Nothing should happen.
	}
	public Vector2f getDirection() {
		return new Vector2f(0,1);
	}
	@Override
	public Asteroid clone() {
		return new Asteroid(this);
	}
	/**
	 * {@inheritDoc}
	 */
	public Point2f getLeftmostCoordinate() {
		return colliComp.getLeftmostCoordinate();
	}
	/**
	 * {@inheritDoc}
	 */
	public Point2f getRightmostCoordinate() {
		return colliComp.getRightmostCoordinate();
	}
	/**
	 * {@inheritDoc}
	 */
	public Point2f getTopmostCoordinate() {
		return colliComp.getTopmostCoordinate();
	}
	/**
	 * {@inheritDoc}
	 */
	public Point2f getBottommostCoordinate() {
		return colliComp.getBottommostCoordinate();
	}
}
