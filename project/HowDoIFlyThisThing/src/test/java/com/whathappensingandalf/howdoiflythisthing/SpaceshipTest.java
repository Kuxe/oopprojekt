/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Martin
 */
public class SpaceshipTest implements PropertyChangeListener{
	
	private boolean isRemoved= false;
	private boolean isFireing= false;
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
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.setVelocity(new Vector2f(100,0));
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.move(t);
		assertTrue(ship.getPosition().x>100);
	}

	@Test
	public void testRemove() {
		s.addPropertyChangeListener(this);
		s.remove();
		assertTrue(isRemoved);
	}

	@Test
	public void testGetAcceleration() {
		assertTrue(s.getAcceleration().equals(new Vector2f(0, 0)));
	}

	@Test
	public void testGetDirection() {
		Vector2f v2 = new Vector2f(1, 1);
		v2.normalize();
		assertTrue(s.getDirection().equals(v2));
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
		assertTrue(s.getWeapon().equals(IGameObject.Type.BULLET.toString()));
	}
	
	@Test
	public void testGetHull() {
		Spaceship s2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		assertTrue(s2.getHull()==10);
	}

	@Test
	public void testSetWeapon() {
		IProjectile projectile = new Missile(new Point2f(5,9), new Vector2f(7,9), new Vector2f(3,6), new Vector2f(7,3), 3, 3);
		s.setWeapon(projectile);
		assertTrue(s.getWeapon().equals(IGameObject.Type.MISSILE.toString()));
	}

	@Test
	public void testFireWeapon() {
		s.addPropertyChangeListener(this);
		isFireing=false;
		s.fireWeapon();
		assertTrue(isFireing);
	}

	@Test
	public void testCalculateThrust() {
		s.setAcceleration(new Vector2f(0,70));
		s.setVelocity(new Vector2f(0,10));
		s.setRotAcceleration(5);
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		s.calculateThrust(t);
		
		assertTrue(s.getAcceleration().equals(new Vector2f(0,0))&&s.getVelocity().equals(new Vector2f(0,10))&&s.getRotAcceleration()==0);
	}

	@Test
	public void testAccept() {
		s.addPropertyChangeListener(this);
		this.isRemoved=false;
		s.accept(s);
		assertTrue(isRemoved);
	}

	@Test
	public void testVisit_Spaceship() {
		Spaceship s2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		s.addPropertyChangeListener(this);
		this.isRemoved=false;
		s.visit(s2);
		assertTrue(isRemoved);
	}

	@Test
	public void testVisit_Projectile() {
		IProjectile p = new Bullet(null, null, null, null, 5, 5);
		int h = s.getHull();
		s.visit(p);
		assertTrue(s.getHull()<h);
	}

	@Test
	public void testVisit_Asteroid() {
		Asteroid a = new Asteroid(new Point2f(0,0), 5, 5);
		s.addPropertyChangeListener(this);
		this.isRemoved=false;
		s.visit(a);
		assertTrue(isRemoved);
	}
	public void testVisit_IPickup(){
		HealthPickup hp = new HealthPickup(new Point2f(0,0), 5, 10);
		int h = s.getHull();
		s.visit(hp);
		assertTrue(s.getHull()>h);
	}

	@Test
	public void testAddPropertyChangeListener() {
		s.addPropertyChangeListener(this);
		s.remove();
		s.fireWeapon();
		assertTrue(isRemoved||isFireing);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		isRemoved=false;
		s.addPropertyChangeListener(this);
		s.removePropertyChangeListener(this);
		s.remove();
		assertFalse(isRemoved);
	}

	@Test
	public void testCollideDetection() {
		Spaceship ship1 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship1.addPropertyChangeListener(this);
		Spaceship ship2 = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		assertTrue(ship1.collideDetection(ship2));
	}

	@Test
	public void testClone() {
		Spaceship s2 = s.clone();
		assertTrue(s.equals(s2)&&(!(s==s2)));
	}

	@Test
	public void testActivateMainThruste() {
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.activateMainThruster();
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.calculateThrust(t);
		assertTrue(!(ship.getAcceleration().equals(new Vector2f(0,0))));
	}

	@Test
	public void testActivateLeftThruste() {
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.activateLeftThruster();
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.calculateThrust(t);
		assertTrue(!(ship.getAcceleration().equals(new Vector2f(0,0)))&&ship.getRotAcceleration()>0);
	}

	@Test
	public void testActivateRightThruste() {
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.activateRightThruster();
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.calculateThrust(t);
		System.out.println(ship.getRotAcceleration());
		assertTrue(!(ship.getAcceleration().equals(new Vector2f(0,0)))&&ship.getRotAcceleration()<0);
	}

	@Test
	public void testDeactivateMainThruster() {
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.activateMainThruster();
		ship.deactivateMainThruster();
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.calculateThrust(t);
		assertTrue(ship.getAcceleration().equals(new Vector2f(0,0)));
	}

	@Test
	public void testDeactivateLeftThruster() {
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.activateLeftThruster();
		ship.deactivateLeftThruster();
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.calculateThrust(t);
		assertTrue(ship.getAcceleration().equals(new Vector2f(0,0))&&ship.getRotAcceleration()==0);
	}

	@Test
	public void testDeactivateRightThruster() {
		Spaceship ship = new Spaceship(new Point2f(100, 100), new Vector2f(1,0), 10, 10);
		ship.activateRightThruster();
		ship.deactivateRightThruster();
		Timestep t = new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		ship.calculateThrust(t);
		assertTrue(ship.getAcceleration().equals(new Vector2f(0,0))&&ship.getRotAcceleration()==0);
	}
	
	@Test
	public void testEquals() {
		Spaceship st1 = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		Spaceship st2 = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		assertTrue(st1.equals(st2) && st2.equals(st1));
	}

	public void propertyChange(PropertyChangeEvent evt) {
		this.isRemoved = evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString());
		this.isFireing = evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_FIRE.toString());
	}
	
}
