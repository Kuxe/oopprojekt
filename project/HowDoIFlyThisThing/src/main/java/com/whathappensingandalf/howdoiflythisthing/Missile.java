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
	private TypeWrapper rotationVelocity;
	private TypeWrapper rotationAcceleration;
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
		this.rotationVelocity.setValue(0.0f);
		this.rotationAcceleration.setValue(0.0f);
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
		this.rotationVelocity.setValue(missile.getRotVelocity());
		this.rotationAcceleration.setValue(missile.getRotAcceleration());
		mC= new MoveComponent(position, velocity, acceleration, direction, new TypeWrapper(0.0f),new TypeWrapper(0.0f));
		colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
	}

	@Override
	public void move(Timestep timestep) {
		this.mC.move(timestep);
	}

	@Override
	public void remove() {
		pcs.firePropertyChange(Message.PROJECTILE_DIE.toString(),this,true);
		
	}

	@Override
	public Vector2f getVelocity() {
		return new Vector2f(this.velocity.x,this.velocity.y);
	}

	@Override
	public Vector2f getAcceleration() {
		return new Vector2f(this.acceleration.x,this.acceleration.y);
	}

	@Override
	public Vector2f getDirection() {
		return new Vector2f(this.direction.x,this.direction.y);
	}

	@Override
	public Point2f getPosition() {
		return new Point2f(this.position.x,this.position.y);
	}

	@Override
	public float getRotVelocity() {
		return this.rotationVelocity.getValue();
	}

	@Override
	public float getRotAcceleration() {
		return this.rotationAcceleration.getValue();
	}

	@Override
	public void setVelocity(Vector2f velocity) {
		this.velocity=velocity;
	}

	@Override
	public void setAcceleration(Vector2f acceleration) {
		this.acceleration=acceleration;
	}

	@Override
	public void setDirection(Vector2f direction) {
		this.direction=direction;
	}

	@Override
	public void setPosition(Point2f position) {
		this.position=position;
	}

	@Override
	public void setRotVelocity(float rotVelocity) {
		this.rotationVelocity.setValue(rotVelocity);
	}

	@Override
	public void setRotAcceleration(float rotAcceleration) {
		this.rotationAcceleration.setValue(rotAcceleration);
	}

	@Override
	public boolean collideDetection(ICollidable rhs) {
		return this.colliComp.collideDetection(rhs);
	}

	@Override
	public int getHeight() {
		return this.getHeight();
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public String getType() {
		return type.PROJECTILE.toString();
	}

	@Override
	public void accept(ICollidable visitor) {
		visitor.visit(this);
	}

	@Override
	public void visit(Spaceship spaceship) {
		this.remove();
	}

	@Override
	public void visit(IProjectile projectile) {
		//Do nothing
	}

	@Override
	public void visit(Asteroid asteroid) {
		this.remove();
		
	}

	@Override
	public void visit(HealthPickup healthPickup) {
		// Do nothing
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}
	
	public Missile clone(){
		return new Missile(this);
	}

}
