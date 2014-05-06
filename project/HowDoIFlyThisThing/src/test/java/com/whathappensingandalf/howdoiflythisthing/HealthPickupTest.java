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
		assertTrue(hp.getHeight()==5);
	}

	@Test
	public void testGetWidth() {
		assertTrue(hp.getWidth()==5);
	}

	@Test
	public void testGetPosition() {
		assertTrue(hp.getPosition().equals(new Point2f(100,100)));
	}

	@Test
	public void testGetType() {
		assertTrue(hp.getType().equals(IGameObject.type.HEALTH_PICKUP.toString()));
	}

	@Test
	public void testAccept() {
		Spaceship s2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		hp.accept(s2);
		assertTrue(s2.getHull()>10);
	}

	@Test
	public void testVisit_Spaceship() {
		Spaceship s2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		hp.addPropertyChangeListener(this);
		hp.visit(s2);
		assertTrue(this.isRemoved);
	}

	@Test
	public void testVisit_IProjectile() {
		//Nothing should happen.
	}

	@Test
	public void testVisit_Asteroid() {
		Asteroid a = new Asteroid(new Point2f(100, 100), 10, 10);
		hp.addPropertyChangeListener(this);
		hp.visit(a);
		assertTrue(this.isRemoved);
	}

	@Test
	public void testVisit_IPickup() {
		//Nothing should happen.
	}

	@Test
	public void testGetDirection() {
		//Is irrelevant.
	}

	@Test
	public void testClone() {
		HealthPickup hp2 = (HealthPickup)hp.clone();
		assertTrue(hp.equals(hp2)&&hp!=hp2);
	}

	@Test
	public void testAddPropertyChangeListener() {
		hp.addPropertyChangeListener(this);
		hp.remove();
		assertTrue(this.isRemoved);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		hp.addPropertyChangeListener(this);
		hp.removePropertyChangeListener(this);
		hp.remove();
		assertFalse(this.isRemoved);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		this.isRemoved = evt.getPropertyName().equals(HealthPickup.Message.PICKUP_DIE.toString());
	}
	
}
