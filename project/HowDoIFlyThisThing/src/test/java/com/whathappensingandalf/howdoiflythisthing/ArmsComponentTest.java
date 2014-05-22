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
public class ArmsComponentTest {
	
	private ArmsComponent ac;
	
	public ArmsComponentTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		ac = new ArmsComponent(new Point2f(0,0), new Vector2f(0,0), new Vector2f(0,0), new Vector2f(0,0), new Vector2f(0,0));
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testSetWeaponPipePosition() {
		ac.setWeaponPipePosition(new Vector2f(10,10));
		IProjectile proj = ac.fire();
		assertTrue(proj.getPosition().x==10);
		assertTrue(proj.getPosition().y==10);
	}

	@Test
	public void testSetWeapon() {
		IProjectile proj = new CookieCracker(new Point2f(0,0), new Vector2f(0,0), new Vector2f(0,0), new Vector2f(0,0),0,0);
		ac.setWeapon(proj);
		assertTrue(ac.getWeapon().equals("COOKIE_CRACKER"));
	}

	@Test
	public void testGetWeapon() {
		assertTrue(ac.getWeapon().equals("BULLET"));
	}

	@Test
	public void testCanFire() {
		assertTrue(ac.canFire());
		ac.fire();
		assertTrue(!ac.canFire());
	}

	@Test
	public void testFire() {
		assertTrue(ac.fire().getType().equals("BULLET"));
	}

	@Test
	public void testEquals() {
		ArmsComponent ac2 = new ArmsComponent(new Point2f(0,0), new Vector2f(0,0), new Vector2f(0,0), new Vector2f(0,0), new Vector2f(0,0));
		assertTrue(ac.equals(ac2));
	}
	
}
