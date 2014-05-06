/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeSupport;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin
 */
public class WeaponPickup implements IPickup{

	private CollidableComponent collidableComp;
	private Point2f position;
	private int radius;
	private IProjectile projectile;
	private PropertyChangeSupport pcs;
	
	public WeaponPickup(Point2f position, int radius, IProjectile projectile){
		this.position		=	position;
		this.radius			=	radius;
		this.projectile		=	projectile;
		pcs				=	new PropertyChangeSupport(this);
		collidableComp	=	new CollidableComponent(position, radius);
	}
	public void affectMe(Spaceship spaceship) {
		spaceship.setWeapon(projectile);
	}
	public void remove(){
		this.pcs.firePropertyChange(IPickup.Message.PICKUP_DIE.toString(), this, true);
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
		return type.WEAPON_PICKUP.toString();
	}

	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	public void visit(Spaceship spaceship) {
		this.remove();
	}

	public void visit(Asteroid asteroid) {
		this.remove();
	}

	public void visit(IProjectile projectile) {
		//Nothing should happen.		
	}

	public void visit(IPickup iPickup) {
		//Nothing should happen.		
	}

	public Vector2f getDirection() {
		return new Vector2f(1,0);
	}

	public IDrawable clone() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
