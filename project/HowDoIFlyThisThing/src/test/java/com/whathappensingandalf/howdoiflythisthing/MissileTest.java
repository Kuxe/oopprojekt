package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;

public class MissileTest {

	@Test
	public void testMissilePoint2fVector2fVector2fVector2fIntInt() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getPosition().x==10);
		assertTrue(m.getPosition().y==11);
		assertTrue(m.getVelocity().x==2);
		assertTrue(m.getVelocity().y==3);
		assertTrue(m.getAcceleration().x==4);
		assertTrue(m.getAcceleration().y==5);
		assertTrue(m.getDirection().x==1);
		assertTrue(m.getDirection().y==6);
	}

	@Test
	public void testMissileMissile() {
		Missile m1=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		Missile m=new Missile(m1);
		assertTrue(m.getPosition().x==10);
		assertTrue(m.getPosition().y==11);
		assertTrue(m.getVelocity().x==2);
		assertTrue(m.getVelocity().y==3);
		assertTrue(m.getAcceleration().x==4);
		assertTrue(m.getAcceleration().y==5);
		assertTrue(m.getDirection().x==1);
		assertTrue(m.getDirection().y==6);
	}

	@Test
	public void testMove() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		Timestep t=new Timestep();
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.start();
		m.move(t);
		assertTrue(m.getPosition().x==10+t.getDelta()*m.getVelocity().x);
		assertTrue(m.getPosition().y==11+t.getDelta()*m.getVelocity().y);
		
	}

	@Test
	public void testGetVelocity() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getVelocity().x==10);
		assertTrue(m.getVelocity().y==11);
	}

	@Test
	public void testGetAcceleration() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getAcceleration().x==4);
		assertTrue(m.getAcceleration().y==5);
	}

	@Test
	public void testGetDirection() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getDirection().x==1);
		assertTrue(m.getDirection().y==6);
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRotVelocity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRotAcceleration() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetVelocity() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAcceleration() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDirection() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRotVelocity() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRotAcceleration() {
		fail("Not yet implemented");
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
	public void testVisitIProjectile() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitCookieCracker() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitAsteroid() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitIPickup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDamage() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePropertyChangeListener() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPropertyChangeListener() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCollectionDrawables() {
		fail("Not yet implemented");
	}

}
