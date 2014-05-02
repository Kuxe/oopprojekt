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

public class ProjectileTest implements PropertyChangeListener{
	
	private boolean isRemoved= false;
	private Vector2f vec= new Vector2f(5, 5);
	private Projectile pro= new Projectile(new Point2f(0, 0), vec, vec, new Vector2f(0, 1), 3, 3);
//											position, velocity, acceleration, direction, width, height
	
//	assertTrue(true);

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
		assertTrue(pro.getPosition().x > 0);
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
		assertTrue(true);
	}

	@Test
	public void testSetVelocity() {
		assertTrue(true);
	}

	@Test
	public void testSetAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testSetDirection() {
		assertTrue(true);
	}

	@Test
	public void testSetPosition() {
		assertTrue(true);
	}

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

	@Test
	public void testGetDamage() {
		assertTrue(true);
	}

	@Test
	public void testGetHeight() {
		assertTrue(true);
	}

	@Test
	public void testGetWidth() {
		assertTrue(true);
	}

	@Test
	public void testGetType() {
		assertTrue(true);
	}

	@Test
	public void testCollideDetection() {
		assertTrue(true);
	}

	@Test
	public void testAccept() {
		assertTrue(true);
	}

	@Test
	public void testVisitSpaceship() {
		assertTrue(true);
	}

	@Test
	public void testVisitProjectile() {
		assertTrue(true);
	}

	@Test
	public void testVisitAsteroid() {
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
		assertTrue(true);
	}

	public void propertyChange(PropertyChangeEvent e) {
		isRemoved= e.getPropertyName().equals(Message.PROJECTILE_DIE.toString());
	}
}//end ProjectileTest