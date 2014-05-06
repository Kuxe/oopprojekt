package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerStateTest {
	
	private PlayerState playerState= new PlayerState();
	private boolean held= false;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetSpaceship() {
		Point2f pos= new Point2f(1, 2);
		Vector2f dir= new Vector2f(3, 4);
		int w= 50;
		int h= 40;
		Spaceship s1= new Spaceship(pos, dir, w, h);
		playerState.setSpaceship(s1);
		assertTrue(playerState.getSpaceship().getPosition().equals(pos) && playerState.getSpaceship().getDirection().equals(dir) &&
				playerState.getSpaceship().getWidth()== w && playerState.getSpaceship().getHeight()== h);
	}

	@Test
	public void testMainHold() {
		assertTrue(true);
	}

	@Test
	public void testLeftHold() {
		assertTrue(true);
	}

	@Test
	public void testRightHold() {
		assertTrue(true);
	}

	@Test
	public void testFireHold() {
		assertTrue(true);
	}

	@Test
	public void testGetSpaceshipPosition() {
		assertTrue(true);
	}

	@Test
	public void testGetSpaceship() {
		assertTrue(true);
	}

}
