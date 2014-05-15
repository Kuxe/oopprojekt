/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
		return Type.WEAPON_PICKUP.toString();
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

	@Override
	public IDrawable clone() {
		return new WeaponPickup(this.position, this.radius, this.projectile);
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
	
	@Override
	public boolean equals(Object rhs){
		if(rhs==this){
			return true;
		}else if(!(rhs instanceof WeaponPickup)){
			return false;
		}else{
			WeaponPickup other = (WeaponPickup)rhs;
			return	this.projectile.getType().equals(other.projectile.getType())&&
					this.position.equals(other.position)&&
					this.radius==other.radius;
		}
	}
	
	@Override
	public int hashCode(){
		return radius * 7919 + (int)Math.round(position.x) * 7907 + (int)Math.round(position.y) * 7901;
	}
	
	@Override
	public Collection<? extends DrawableData> getCollectionDrawables() {
		Set<DrawableData> returnSet=new HashSet<DrawableData>();
		returnSet.add(new DrawableData(
				getPosition(),
				getHeight(),
				getWidth(),
				getDirection(),
				getType().toString()));
		return returnSet;
	}
	
}
