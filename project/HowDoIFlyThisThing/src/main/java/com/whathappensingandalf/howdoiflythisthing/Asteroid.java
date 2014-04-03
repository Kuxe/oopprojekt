package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Rectangle2D;
import javax.vecmath.Point2f;

/**
 *
 * @author Martin Nilsson
 */
public class Asteroid implements ICollidable, IGameObject{

	
	private CollidableComponent colliComp;
	Point2f position;
	double width;
	double height;
	
	public Asteroid (Point2f position, double width, double height){
		this.position = position;
		this.width = width;
		this.height = height;
		this.colliComp = new CollidableComponent(position, width, height);
	}
	
	public boolean collideDetection(ICollidable rhs) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public double getHeight() {
		return this.height;
	}

	public double getWidth() {
		return this.width;
	}

	public Point2f getPosition() {
		return this.position;
	}

	public Rectangle2D getBoundingBox() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String getType() {
		return type.ASTEROID.toString();
	}

	public void accept(ICollidable visitor) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void visit(Spaceship spaceship) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void visit(Projectile projectile) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
