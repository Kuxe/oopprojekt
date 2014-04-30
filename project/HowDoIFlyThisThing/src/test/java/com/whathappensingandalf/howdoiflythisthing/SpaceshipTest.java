/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class SpaceshipTest {
	
	private Spaceship s = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
	
	public SpaceshipTest() {
		
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testMove() {
	}

	@Test
	public void testRemove() {
	}

	@Test
	public void testGetAcceleration() {
		assertTrue(s.getAcceleration().equals(new Vector2f(0, 0)));
	}

	@Test
	public void testGetDirection() {
		assertTrue(s.getDirection().equals(new Vector2f(1, 1)));
	}

	@Test
	public void testGetPosition() {
		assertTrue(s.getPosition().equals(new Point2f(10, 10)));
	}

	@Test
	public void testGetVelocity() {
		assertTrue(s.getVelocity().equals(new Vector2f(0, 0)));
	}

	@Test
	public void testSetVelocity() {
		s.setVelocity(new Vector2f(67, 80));
		assertTrue(s.getVelocity().equals(new Vector2f(67, 80)));
	}

	@Test
	public void testSetAcceleration() {
		s.setAcceleration(new Vector2f(77, 56));
		assertTrue(s.getAcceleration().equals(new Vector2f(77, 56)));
	}

	@Test
	public void testSetDirection() {
		s.setDirection(new Vector2f(55, 12));
		assertTrue(s.getDirection().equals(new Vector2f(55, 12)));
	}

	@Test
	public void testSetPosition() {
		s.setPosition(new Point2f(98, 41));
		assertTrue(s.getPosition().equals(new Point2f(98, 41)));
	}

	@Test
	public void testGetRotVelocity() {
		assertTrue(s.getRotVelocity()==0);
	}

	@Test
	public void testGetRotAcceleration() {
		assertTrue(s.getRotAcceleration()==0);
	}

	@Test
	public void testSetRotVelocity() {
		s.setRotVelocity(50);
		assertTrue(s.getRotVelocity()==50);
	}

	@Test
	public void testSetRotAcceleration() {
		s.setRotAcceleration(10);
		assertTrue(s.getRotAcceleration()==10);
	}

	@Test
	public void testGetHeight() {
		assertTrue(s.getHeight()==50);
	}

	@Test
	public void testGetWidth() {
		assertTrue(s.getWidth()==70);
	}

	@Test
	public void testGetType() {
		assertTrue(s.getType().equals("SPACESHIP"));
	}

	@Test
	public void testGetWeapon() {
	}

	@Test
	public void testSetWeapon() {
	}

	@Test
	public void testFireWeapon() {
	}

	@Test
	public void testCalculateThrust() {
	}

	@Test
	public void testAccept() {
	}

	@Test
	public void testVisit_Spaceship() {
	}

	@Test
	public void testVisit_Projectile() {
	}

	@Test
	public void testVisit_Asteroid() {
	}

	@Test
	public void testAddPropertyChangeListener() {
	}

	@Test
	public void testRemovePropertyChangeListener() {
	}

	@Test
	public void testCollideDetection() {
	}

	@Test
	public void testClone() {
		Spaceship s2 = s.clone();
		assertTrue(s.equals(s2)&&(!(s==s2)));
	}

	@Test
	public void testActivateMainThruste() {
	}

	@Test
	public void testActivateLeftThruste() {
	}

	@Test
	public void testActivateRightThruste() {
	}

	@Test
	public void testDeactivateMainThruster() {
	}

	@Test
	public void testDeactivateLeftThruster() {
	}

	@Test
	public void testDeactivateRightThruster() {
	}

	@Test
	public void testGetLeftmostCoordinate() {
		Spaceship ship = new Spaceship(new Point2f(10, 10), new Vector2f(1,0), 70, 50);
		float f = ship.getLeftmostCoordinate().x;
		assertTrue(f==-25);
	}

	@Test
	public void testGetRightmostCoordinate() {
		Spaceship ship = new Spaceship(new Point2f(10, 10), new Vector2f(1,0), 70, 50);
		float f = ship.getRightmostCoordinate().x;
		assertTrue(f==45);
	}

	@Test
	public void testGetTopmostCoordinate() {
		Spaceship ship = new Spaceship(new Point2f(10, 10), new Vector2f(1,0), 70, 50);
		float f = ship.getTopmostCoordinate().y;
		assertTrue(f==-15);
	}

	@Test
	public void testGetBottommostCoordinate() {
		Spaceship ship = new Spaceship(new Point2f(10, 10), new Vector2f(1,0), 70, 50);
		float f = ship.getBottommostCoordinate().y;
		assertTrue(f==35);
	}
	
	@Test
	public void testEquals() {
		Spaceship st1 = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		Spaceship st2 = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		assertTrue(st1.equals(st2));
	}
	
}
