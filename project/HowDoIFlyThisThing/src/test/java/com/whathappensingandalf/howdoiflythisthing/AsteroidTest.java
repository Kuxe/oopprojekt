package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;

import org.junit.Test;

public class AsteroidTest {

	@Test
	public void testAsteroidPoint2fIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testAsteroidAsteroid() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		assertTrue(testAsteroid.getPosition().x==30 &&
				testAsteroid.getPosition().y==40 &&
				testAsteroid.getWidth()==10 &&
				testAsteroid.getHeight()==20);
	}

	@Test
	public void testCollideDetection() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		Asteroid testAsteroid2=new Asteroid(new Point2f(30,30),10,20);
		assertTrue(testAsteroid.collideDetection(testAsteroid2));
	}

	@Test
	public void testGetHeight() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		assertTrue(testAsteroid.getHeight()==20);
	}

	@Test
	public void testGetWidth() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		assertTrue(testAsteroid.getWidth()==10);
	}

	@Test
	public void testGetPosition() {
		Point2f testPoint=new Point2f(30,40);
		Asteroid testAsteroid=new Asteroid(testPoint,10,20);
		assertTrue(testAsteroid.getPosition()==testPoint);
	}

	@Test
	public void testGetType() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		assertTrue(testAsteroid.getType().equals("ASTEROID"));
	}

	@Test
	public void testAccept() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitSpaceship() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitProjectile() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitAsteroid() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDirection() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

}
