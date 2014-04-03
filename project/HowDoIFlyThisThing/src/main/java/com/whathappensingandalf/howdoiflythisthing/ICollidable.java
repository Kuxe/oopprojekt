package com.whathappensingandalf.howdoiflythisthing;

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
	public double getHeight();
	/**
	 * @return the width of this component
	 */
	public double  getWidth();
	/**
	 * @return the position of this component
	 */
	public Point2f getPosition();
	/**
	 * @return
	 */
	public Rectangle2D getBoundingBox();
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
	public void visit(Projectile projectile);
	
}
