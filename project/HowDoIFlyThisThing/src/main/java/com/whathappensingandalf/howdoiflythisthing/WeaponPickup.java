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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public int getWidth() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Point2f getPosition() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String getType() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void accept(ICollidable visitor) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void visit(Spaceship spaceship) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void visit(Asteroid asteroid) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void visit(IProjectile projectile) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void visit(IPickup iPickup) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Vector2f getDirection() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public IDrawable clone() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
