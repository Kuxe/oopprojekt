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

import utils.TypeWrapper;

import com.whathappensingandalf.howdoiflythisthing.CookieCracker;


public class CookieCrackerTest implements PropertyChangeListener{
	
	private Point2f position= new Point2f(0, 0);
	private Vector2f velocity= new Vector2f(0, 0);
	private Vector2f acceleration= new Vector2f(0, 0);
	private Vector2f direction= new Vector2f(0, 0);
	private int width= 0;
	private int height= 0;
	private CookieCracker cc= new CookieCracker(position, velocity, acceleration, direction, width, height);
	private boolean pcsChange= false;
	
	@Before
	public void setUp() throws Exception {
		pcsChange= false;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMove() {
		assertTrue(true);
	}

	@Test
	public void testRemove() {
		cc.addPropertyChangeListener(this);
		cc.remove();
		assertTrue(pcsChange);
	}

	@Test
	public void testGetVelocity() {
		Vector2f vel= cc.getVelocity();
		assertTrue(vel.x == velocity.x);
		assertTrue(vel.y == velocity.y);
	}

	@Test
	public void testGetAcceleration() {
		Vector2f acc= cc.getAcceleration();
		assertTrue(acc.x == acceleration.x);
		assertTrue(acc.y == acceleration.y);
	}

	@Test
	public void testGetDirection() {
		Vector2f dir= cc.getDirection();
		assertTrue(dir.x == direction.x);
		assertTrue(dir.y == direction.y);
	}

	@Test
	public void testGetPosition() {
		Point2f pos= cc.getPosition();
		assertTrue(pos.x == position.x);
		assertTrue(pos.y == position.y);
	}

	@Test
	public void testGetRotVelocity() {
		assertTrue(cc.getRotVelocity()== 0.0f);
	}

	@Test
	public void testGetRotAcceleration() {
		assertTrue(cc.getRotAcceleration()== 0.0f);
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
	public void testSetRotVelocity() {
		assertTrue(true);
	}

	@Test
	public void testSetRotAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testCollideDetection() {
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
	public void testAccept() {
		assertTrue(true);
	}

	@Test
	public void testVisitSpaceship() {
		assertTrue(true);
	}

	@Test
	public void testVisitIProjectile() {
		assertTrue(true);
	}

	@Test
	public void testVisitAsteroid() {
		assertTrue(true);
	}

	@Test
	public void testVisitCookieCracker() {
		assertTrue(true);
	}

	@Test
	public void testVisitIPickup() {
		assertTrue(true);
	}

	@Test
	public void testGetDamage() {
		assertTrue(true);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		assertTrue(true);
	}

	@Test
	public void testAddPropertyChangeListener() {
		assertTrue(true);
	}

	@Test
	public void testClone() {
		assertTrue(true);
	}

	@Test
	public void testGetCollectionDrawables() {
		assertTrue(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("PROJECTILE_DIE")){
			pcsChange= true;
		}
	}
}