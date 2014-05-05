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
		fail("Not yet implemented");
	}

	@Test
	public void testGetHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented");
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
