package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;

public class AsteroidTest implements PropertyChangeListener{

	private boolean evtBool;
	
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
		assertTrue(testAsteroid2.getPosition().x==30 &&
				testAsteroid2.getPosition().y==40 &&
				testAsteroid2.getWidth()==10 &&
				testAsteroid2.getHeight()==20 &&
				testAsteroid2.getPosition().x==testAsteroid.getPosition().x &&
				testAsteroid2.getPosition().y==testAsteroid.getPosition().y &&
				testAsteroid2.getWidth()==testAsteroid.getWidth() &&
				testAsteroid2.getHeight()==testAsteroid.getHeight());
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
		Spaceship s2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		s2.addPropertyChangeListener(this);
		Asteroid ta = new Asteroid(new Point2f(30,40),10,20);
		ta.accept(s2);
		assertTrue(this.evtBool);
	}

	@Test
	public void testVisitSpaceship() {
		//Nothing should happen.
	}

	@Test
	public void testVisitProjectile() {
		//Nothing should happen.
	}

	@Test
	public void testVisitAsteroid() {
		//Nothing should happen.
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

	public void propertyChange(PropertyChangeEvent evt) {
		this.evtBool = evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString());
	}

}
