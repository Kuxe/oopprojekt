package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.TypeWrapper;

public class MoveComponentTest {

	private Vector2f vec= new Vector2f(0, 1);
	private TypeWrapper wrapper= new TypeWrapper(0.0f);
	private MoveComponent mc= new MoveComponent(new Point2f(0, 0), vec, vec, vec, wrapper, wrapper);
//										position, velocity, acceleration, direction, rotationVelocity, rotationAcceleration
	
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
	public void testMoveComponent() {
		Point2f pos= new Point2f(0, 0);
		Vector2f vel= new Vector2f(1, 1);
		Vector2f acc= new Vector2f(0, 0);
		Vector2f dir= new Vector2f(-1, -1);
		TypeWrapper tw1= new TypeWrapper(0.0f);
		TypeWrapper tw2= new TypeWrapper(1.0f);
		MoveComponent mc= new MoveComponent(pos, vel, acc, dir, tw1, tw2);
//		TODO- how to test without getters?
		assertTrue(true);
	}

	@Test
	public void testMove() {
		assertTrue(true);
	}

	@Test
	public void testSetVelocity() {
		assertTrue(true);
	}

	@Test
	public void testSetAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testSetDirection() {
		assertTrue(true);
	}

	@Test
	public void testSetPosition() {
		assertTrue(true);
	}
}