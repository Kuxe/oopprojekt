package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Nilsson
 */
public class HealthPickupTest {
	
	private HealthPickup hp;
	
	public HealthPickupTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		hp = new HealthPickup(new Point2f(100,100), 5, 10);
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testGetHealth() {
		assertTrue(hp.getHealth()==10);
	}

	@Test
	public void testAffectMe() {
	}

	@Test
	public void testRemove() {
	}

	@Test
	public void testCollideDetection() {
	}

	@Test
	public void testGetHeight() {
	}

	@Test
	public void testGetWidth() {
	}

	@Test
	public void testGetPosition() {
	}

	@Test
	public void testGetType() {
	}

	@Test
	public void testAccept() {
	}

	@Test
	public void testVisit_Spaceship() {
	}

	@Test
	public void testVisit_IProjectile() {
	}

	@Test
	public void testVisit_Asteroid() {
	}

	@Test
	public void testVisit_IPickup() {
	}

	@Test
	public void testGetDirection() {
	}

	@Test
	public void testClone() {
	}

	@Test
	public void testAddPropertyChangeListener() {
	}

	@Test
	public void testRemovePropertyChangeListener() {
	}
	
}
