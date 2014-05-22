package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import utils.TypeWrapper;


/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Class representing a CookieCracker, a kind of projectile that's
 * capable of destroying asteroids
 */
public class CookieCracker implements IProjectile{
	private int width;
	private int height;
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	private Point2f position;
    private TypeWrapper rotationVelocity;
	private TypeWrapper rotationAcceleration;
	private MoveComponent mC;
	private CollidableComponent colliComp;
	
	private PropertyChangeSupport pcs;
	
	public CookieCracker(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		this.velocity= velocity;
		this.acceleration= acceleration;
		this.direction= direction;
		this.position= position;
		this.width = width;
		this.height = height;
		this.rotationVelocity = new TypeWrapper(0.0f);
		this.rotationAcceleration = new TypeWrapper(0.0f);
		mC= new MoveComponent(position, velocity, acceleration, direction, new TypeWrapper(0.0f),new TypeWrapper(0.0f));
		colliComp = new CollidableComponent(position, width, height);
		pcs = new PropertyChangeSupport(this);
	}
	
	public CookieCracker(CookieCracker cookieCracker){
		this.velocity=cookieCracker.getVelocity();
		this.acceleration= cookieCracker.getAcceleration();
		this.direction= cookieCracker.getDirection();
		this.position= cookieCracker.getPosition();
		this.width = cookieCracker.getWidth();
		this.height = cookieCracker.getHeight();
		this.rotationVelocity.setValue(cookieCracker.getRotVelocity());
		this.rotationAcceleration.setValue(cookieCracker.getRotAcceleration());
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
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public String getType() {
		return Type.COOKIE_CRACKER.toString();
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
		this.remove();
	}

	@Override
	public void visit(Asteroid asteroid) {
		this.remove();
	}
	
	@Override
	public void visit(CookieCracker cookieCracker) {
		//Double cookieCracker, ohhohoooo!
	}

	@Override
	public void visit(IPickup iPickup) {
		// Do nothing
	}

	@Override
	public int getDamage() {
        return 1;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}
	
	public CookieCracker clone(){
		return new CookieCracker(this);
	}

	@Override
	public Collection<? extends DrawableData> getCollectionDrawables() {
		Set<DrawableData> returnSet=new HashSet<DrawableData>();
		returnSet.add(new DrawableData(
				getPosition(),
				getHeight(),
				getWidth(),
				getDirection(),
				getType()));
		return returnSet;
	}
}
