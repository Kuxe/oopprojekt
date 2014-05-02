package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.whathappensingandalf.howdoiflythisthing.Projectile.Message;
import com.whathappensingandalf.howdoiflythisthing.factorys.AsteroidFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

public class ProjectileTest implements PropertyChangeListener{
	
	private boolean isRemoved= false;
	private Vector2f vec= new Vector2f(5, 5);
	private Projectile pro= new Projectile(new Point2f(1, 1), vec, vec, new Vector2f(0, 1), 3, 3);
	private Projectile pro2= new Projectile(new Point2f(1, 1), vec, vec, new Vector2f(0, 1), 3, 3);
//											position, velocity, acceleration, direction, width, height
	private Spaceship ss= SpaceshipFactory.create(new Point2f(1, 1), vec, 50, 50);
	private Asteroid ast= AsteroidFactory.create(new Point2f(1, 1), 100, 100);

	@Test
	public void testProjectilePoint2fVector2fVector2fVector2fIntInt(){
//		should constructor be tested?
		assertTrue(true);
		}

	@Test
	public void testProjectileProjectile() {
		assertTrue(true);
	}

	@Test
	public void testMove(){
		Timestep timestep= new Timestep();
		timestep.start();
		timestep.end();
		timestep.calculateDeltatime();
		pro.move(timestep);
		assertTrue(pro.getPosition().x > 1);
	}

	@Test
	public void testRemove(){
		pro.addPropertyChangeListener(this);
		pro.remove();
		assertTrue(isRemoved);
	}

	@Test
	public void testGetVelocity() {
		assertTrue(pro.getVelocity().x== 5 && pro.getVelocity().y== 5);
	}

	@Test
	public void testGetAcceleration() {
		assertTrue(pro.getAcceleration().x== 5 && pro.getAcceleration().y== 5);
	}

	@Test
	public void testGetDirection() {
		assertTrue(pro.getDirection().x== 0 && pro.getDirection().y== 1);
	}

	@Test
	public void testGetPosition() {
		assertTrue(pro.getPosition().x== 1 && pro.getPosition().y== 1);
	}

	@Test
	public void testSetVelocity() {
		pro.setVelocity(new Vector2f(4, 5));
		assertTrue(pro.getVelocity().x== 4 && pro.getVelocity().y== 5);
	}

	@Test
	public void testSetAcceleration() {
		pro.setAcceleration(new Vector2f(56, 7));
		assertTrue(pro.getAcceleration().x== 56 && pro.getAcceleration().y== 7);
	}

	@Test
	public void testSetDirection() {
		pro.setDirection(new Vector2f(6, 7));
		assertTrue(pro.getDirection().x== 6 && pro.getDirection().y== 7);
	}

	@Test
	public void testSetPosition() {
		pro.setPosition(new Point2f(2, 2));
		assertTrue(pro.getPosition().x== 2 && pro.getPosition().y== 2);
	}

	/*
	@Test
	public void testGetRotVelocity() {
		assertTrue(true);
	}

	@Test
	public void testGetRotAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testSetRotVelocity() {
		assertTrue(true);
	}

	@Test
	public void testSetRotAcceleration() {
		assertTrue(true);
	}
	*/
	@Test
	public void testGetDamage() {
		assertTrue(pro.getDamage()== 1);
	}

	@Test
	public void testGetHeight() {
		assertTrue(pro.getHeight()== 3);
	}

	@Test
	public void testGetWidth() {
		assertTrue(pro.getWidth()== 3);
	}

	@Test
	public void testGetType() {
		assertTrue(pro.getType().equals("PROJECTILE"));
	}

	@Test
	public void testCollideDetection() {
		assertTrue(pro.collideDetection(pro2));
	}

	@Test
	public void testAccept() {
//		TODO
		assertTrue(true);
	}

	@Test
	public void testVisitSpaceship() {
//		TODO
		assertTrue(true);
	}

	@Test
	public void testVisitProjectile() {
//		TODO
		assertTrue(true);
	}

	@Test
	public void testVisitAsteroid() {
//		TODO
		assertTrue(true);
	}

	@Test
	public void testAddPropertyChangeListener() {
		assertTrue(true);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		assertTrue(true);
	}

	@Test
	public void testClone() {
		Projectile p= pro.clone();
		assertTrue(true);
	}

	public void propertyChange(PropertyChangeEvent e) {
		isRemoved= e.getPropertyName().equals(Message.PROJECTILE_DIE.toString());
	}
}//end ProjectileTest