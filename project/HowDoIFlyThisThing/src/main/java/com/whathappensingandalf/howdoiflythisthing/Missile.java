package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

public class Missile implements IProjectile{

	@Override
	public void move(Timestep timestep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2f getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2f getAcceleration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2f getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2f getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getRotVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRotAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVelocity(Vector2f velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAcceleration(Vector2f acceleration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirection(Vector2f direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(Point2f position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRotVelocity(float rotVelocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRotAcceleration(float rotAcceleration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collideDetection(ICollidable rhs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(ICollidable visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Spaceship spaceship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IProjectile projectile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Asteroid asteroid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(HealthPickup healthPickup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		// TODO Auto-generated method stub
		
	}

}
