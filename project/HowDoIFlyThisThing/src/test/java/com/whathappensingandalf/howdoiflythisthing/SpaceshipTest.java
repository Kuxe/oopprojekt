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
	
	private Spaceship s = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 50, 50);
	
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
	}

	@Test
	public void testGetDirection() {
	}

	@Test
	public void testGetPosition() {
		assertTrue(s.getPosition().equals(new Point2f(10, 10)));
	}

	@Test
	public void testGetVelocity() {
	}

	@Test
	public void testSetVelocity() {
	}

	@Test
	public void testSetAcceleration() {
	}

	@Test
	public void testSetDirection() {
	}

	@Test
	public void testSetPosition() {
	}

	@Test
	public void testGetRotVelocity() {
	}

	@Test
	public void testGetRotAcceleration() {
	}

	@Test
	public void testSetRotVelocity() {
	}

	@Test
	public void testSetRotAcceleration() {
	}

	@Test
	public void testGetHeight() {
	}

	@Test
	public void testGetWidth() {
	}

	@Test
	public void testGetType() {
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
	}

	@Test
	public void testGetRightmostCoordinate() {
	}

	@Test
	public void testGetTopmostCoordinate() {
	}

	@Test
	public void testGetBottommostCoordinate() {
	}
	
}
