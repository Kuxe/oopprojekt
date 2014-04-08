package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class Asteroid implements ICollidable, IGameObject, IDrawable{

	
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
		return this.position;
	}

	public Area getBoundingBox() {
		return this.colliComp.getBoundingBox();
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

	public Point2f getPossition() {
		return position;
	}
	public Vector2f getDirection() {
		return new Vector2f(0,1);
	}
}
