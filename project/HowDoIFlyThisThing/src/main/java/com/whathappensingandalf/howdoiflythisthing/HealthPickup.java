/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin
 */
public class HealthPickup implements IPickup{

	public static enum Message{
		PICKUP_DIE
	}
	private CollidableComponent collidableComp;
	private Point2f position;
	private int radius;
	private int health;
	private PropertyChangeSupport pcs;
	
	public HealthPickup(Point2f position, int radius, int health){
		this.position=new Point2f();
		this.radius=radius;
		this.health = health;
		collidableComp = new CollidableComponent(position, radius);
	}
	public int getHealth(){
		return this.health;
	}
	public void affectMe(Spaceship spaceship) {
		spaceship.repair(this.health);
	}
	public void remove(){
		this.pcs.firePropertyChange(HealthPickup.Message.PICKUP_DIE.toString(), this, true);
	}
	public boolean collideDetection(ICollidable rhs) {
		return this.collidableComp.collideDetection(rhs);
	}

	public int getHeight() {
		return this.radius;
	}

	public int getWidth() {
		return this.radius;
	}

	public Point2f getPosition() {
		return new Point2f(this.position);
	}

	public String getType() {
		return type.HEALTH_PICKUP.toString();
	}

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(Spaceship spaceship) {
		this.remove();
	}

	public void visit(IProjectile projectile) {
		//Nothing should happen.	
	}

	public void visit(Asteroid asteroid) {
		this.remove();
	}

	public void visit(IPickup iPickup) {
		//Nothing should happen.
	}
	
	public Vector2f getDirection() {
		return new Vector2f(1,0);
	}

	public IDrawable clone() {
		return new HealthPickup(this.position, this.radius, this.health);
	}

	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
    public void addPropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.addPropertyChangeListener(pcl);
	}
	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
    public void removePropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.removePropertyChangeListener(pcl);
	}
	
}
