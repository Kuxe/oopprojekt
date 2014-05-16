package com.whathappensingandalf.howdoiflythisthing;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.IGameObject.Type;

/**
 *
 * @author Martin Nilsson
 */
public class Asteroid implements ICollidable, IGameObject, IDrawable, Cloneable{

	public enum Message {
		ASTEROID_DIE
	}
	
	private CollidableComponent colliComp;
	private Point2f position;
	private int width;
	private int height;
	private int health = 12;
	private PropertyChangeSupport pcs;
	
	public Asteroid (Point2f position, int width, int height){
		this.position = position;
		this.width = width;
		this.height = height;
		this.colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
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
		switch(health) {
		case 1:
		case 2:
		case 3:
			return Type.ASTEROID_DMG3.toString();
		case 4:
		case 5:
		case 6:
			return Type.ASTEROID_DMG2.toString();
		case 7:
		case 8:
		case 9:
			return Type.ASTEROID_DMG1.toString();
			default:
				return Type.ASTEROID.toString();
		}
	}

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(IPickup iPickup) {
		//Nothing should happen.	
	}

	public void visit(Spaceship spaceship) {
		damage(3);
	}

	public void visit(IProjectile projectile) {
		//Nothing should happen.
	}
	
	public void visit(Asteroid asteroid) {
		//Nothing should happen.
	}

	public void visit(CookieCracker cookieCracker) {
		damage(1);
	}
	
	public Vector2f getDirection() {
		return new Vector2f(0,1);
	}
	@Override
	public Asteroid clone() {
		return new Asteroid(this);
	}

	@Override
	public Collection<? extends DrawableData> getCollectionDrawables() {
		Set<DrawableData> returnSet = new HashSet<DrawableData>();
		returnSet.add(new DrawableData(
				getPosition(),
				getHeight(),
				getWidth(),
				getDirection(),
				getType()));
		return returnSet;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	private void damage(int damage) {
		health -= damage;
		if(health <= 0) {
			pcs.firePropertyChange(Message.ASTEROID_DIE.toString(), false, true);
		}
	}
}