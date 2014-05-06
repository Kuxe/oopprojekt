package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
 * @author Martin Nilsson
 */
public class HealthPickupTest implements PropertyChangeListener{
	
	private HealthPickup hp;
	private Boolean isRemoved;
	
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
		isRemoved = false;
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
		Spaceship s = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		hp.affectMe(s);
		assertTrue(s.getHull()==20);
	}

	@Test
	public void testRemove() {
		hp.addPropertyChangeListener(this);
		hp.remove();
		assertTrue(isRemoved);
	}

	@Test
	public void testCollideDetection() {
		HealthPickup hp2 = new HealthPickup(new Point2f(102,102), 5, 10);
		assertTrue(hp.collideDetection(hp2));
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

	public void propertyChange(PropertyChangeEvent evt) {
		this.isRemoved = evt.getPropertyName().equals(HealthPickup.Message.PICKUP_DIE.toString());
	}
	
}
