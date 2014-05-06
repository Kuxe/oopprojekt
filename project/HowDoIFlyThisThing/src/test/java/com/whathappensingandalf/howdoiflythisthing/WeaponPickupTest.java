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
public class WeaponPickupTest  implements PropertyChangeListener{
	
	private WeaponPickup wp;
	private Boolean isRemoved;
	private IProjectile projectile;
	
	public WeaponPickupTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		projectile = new Bullet(new Point2f(1,1), new Vector2f(1,1), new Vector2f(1,1), new Vector2f(1,1),5,5);
		wp = new WeaponPickup(new Point2f(100,100), 5, projectile);
		isRemoved = false;
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testAffectMe() {
		Spaceship s = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		Missile projectile = new Missile(new Point2f(1,1), new Vector2f(1,1), new Vector2f(1,1), new Vector2f(1,1),5,5);
		WeaponPickup wp2 = new WeaponPickup(new Point2f(100, 100), 5, projectile);
		wp2.affectMe(s);
		assertTrue(s.getWeapon().equals(projectile.getType()));
	}

	@Test
	public void testRemove() {
		wp.addPropertyChangeListener(this);
		wp.remove();
		assertTrue(isRemoved);
	}

	@Test
	public void testCollideDetection() {
		WeaponPickup wp2 = new WeaponPickup(new Point2f(102,102), 5, projectile);
		assertTrue(wp.collideDetection(wp2));
	}

	@Test
	public void testGetHeight() {
		assertTrue(wp.getHeight()==5);
	}

	@Test
	public void testGetWidth() {
		assertTrue(wp.getHeight()==5);
	}

	@Test
	public void testGetPosition() {
		assertTrue(wp.getPosition().equals(new Point2f(100,100)));
	}

	@Test
	public void testGetType() {
		assertTrue(wp.getType().equals(IGameObject.type.WEAPON_PICKUP.toString()));
	}

	@Test
	public void testAccept() {
		Spaceship s = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		Missile projectile = new Missile(new Point2f(1,1), new Vector2f(1,1), new Vector2f(1,1), new Vector2f(1,1),5,5);
		WeaponPickup wp2 = new WeaponPickup(new Point2f(100, 100), 5, projectile);
		wp2.accept(s);
		assertTrue(s.getWeapon().equals(projectile.getType()));
	}

	@Test
	public void testVisit_Spaceship() {
		Spaceship s2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		wp.addPropertyChangeListener(this);
		wp.visit(s2);
		assertTrue(this.isRemoved);
	}

	@Test
	public void testVisit_Asteroid() {
		Asteroid a = new Asteroid(new Point2f(100, 100), 10, 10);
		wp.addPropertyChangeListener(this);
		wp.visit(a);
		assertTrue(this.isRemoved);
	}

	@Test
	public void testVisit_IProjectile() {
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

	public void propertyChange(PropertyChangeEvent evt) {
		this.isRemoved = evt.getPropertyName().equals(HealthPickup.Message.PICKUP_DIE.toString());
	}
	
}
