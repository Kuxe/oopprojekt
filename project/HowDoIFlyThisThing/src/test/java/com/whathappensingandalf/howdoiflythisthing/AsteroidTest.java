package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;

import org.junit.Test;

public class AsteroidTest {

	@Test
	public void testAsteroidPoint2fIntInt() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		assertTrue(testAsteroid.getPosition().x==30 &&
				testAsteroid.getPosition().y==40 &&
				testAsteroid.getWidth()==10 &&
				testAsteroid.getHeight()==20);
	}

	@Test
	public void testAsteroidAsteroid() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		Asteroid testAsteroid2=new Asteroid(testAsteroid);
		assertTrue(testAsteroid2.getPosition()==testAsteroid.getPosition() &&
				testAsteroid2.getPosition().x==30 &&
				testAsteroid2.getPosition().y==40 &&
				testAsteroid2.getWidth()==10 &&
				testAsteroid2.getHeight()==20);
	}

	@Test
	public void testCollideDetection() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		Asteroid testAsteroid2=new Asteroid(new Point2f(30,40),10,20);
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
		assertTrue(testAsteroid.getPosition().x==testPoint.x &&
				testAsteroid.getPosition().y==testPoint.y);
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
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		assertTrue(testAsteroid.getDirection().x==0 &&
				testAsteroid.getDirection().y==1);
	}

	@Test
	public void testClone() {
		Asteroid testAsteroid=new Asteroid(new Point2f(30,40),10,20);
		Asteroid testAsteroidClone=testAsteroid.clone();
		assertTrue(
				testAsteroid!=testAsteroidClone &&
				testAsteroid.getDirection()!=testAsteroidClone.getDirection() &&
				testAsteroid.getDirection().x==testAsteroidClone.getDirection().x &&
				testAsteroid.getDirection().y==testAsteroidClone.getDirection().y &&
				testAsteroid.getPosition()!=testAsteroidClone.getPosition() &&
				testAsteroid.getPosition().x==testAsteroidClone.getPosition().x &&
				testAsteroid.getPosition().y==testAsteroidClone.getPosition().y &&
				testAsteroid.getWidth()==testAsteroidClone.getWidth() &&
				testAsteroid.getHeight()==testAsteroidClone.getHeight());
	}

}
