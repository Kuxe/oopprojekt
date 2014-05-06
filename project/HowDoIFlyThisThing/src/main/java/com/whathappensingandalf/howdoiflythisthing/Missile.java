package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import utils.TypeWrapper;

public class Missile implements IProjectile{
	
	private int width;
	private int height;
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	private Point2f position;
	private int damage = 1;  //@TODO
	//private Damage damage= new Damage();
	private MoveComponent mC;
	private CollidableComponent colliComp;
	
	private PropertyChangeSupport pcs;
	
	public Missile(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		this.width = width;
		this.height = height;
		mC= new MoveComponent(position, velocity, acceleration, direction, new TypeWrapper(0.0f),new TypeWrapper(0.0f));
		colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
	}
	
	public Missile(Missile missile){
		this.velocity=missile.getVelocity();
		this.acceleration= missile.getAcceleration();
		this.direction= missile.getDirection();
		this.position= missile.getPosition();
		this.width = missile.getWidth();
		this.height = missile.getHeight();
		mC= new MoveComponent(position, velocity, acceleration, direction, new TypeWrapper(0.0f),new TypeWrapper(0.0f));
		colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
	}

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
		this.pcs.removePropertyChangeListener(pcl);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}

}
