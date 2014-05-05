package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Point2f;


/**
 * @author Francine
 *
 */
public interface ICollidable extends IListable{
	
	/**
	 * @param collidableComponent
	 */
	public boolean collideDetection(ICollidable rhs);
	/**
	 * @return the height of this component
	 */
	public int getHeight();
	/**
	 * @return the width of this component
	 */
	public int  getWidth();
	/**
	 * @return the position of this component
	 */
	public Point2f getPosition();
	/**
	 * @return the type of this component
	 */
	public String getType();
	/**
	 * @param visitor
	 */
	public void accept(ICollidable visitor);
	/**
	 * @param spaceship
	 */
	public void visit(Spaceship spaceship);
	/**
	 * @param projectile
	 */
	public void visit(IProjectile projectile);
	/**
	 * @param asteroid
	 */
	public void visit(Asteroid asteroid);
	
	public void visit(HealthPickup healthPickup);
	/**
	 * @return the point most to the left
	 */
//	public Point2f getLeftmostCoordinate();
	/**
	 * @return the point most to the right
	 */
//	public Point2f getRightmostCoordinate();
	/**
	 * @return the upper point
	 */
//	public Point2f getTopmostCoordinate();
	/**
	 * @return the point at the bottom
	 */
//	public Point2f getBottommostCoordinate();
}
