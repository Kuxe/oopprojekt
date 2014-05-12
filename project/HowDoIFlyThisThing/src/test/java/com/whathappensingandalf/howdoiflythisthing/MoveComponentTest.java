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

	private Point2f pos= new Point2f(0, 0);
	private Vector2f vel= new Vector2f(0, 1);
	private Vector2f acc= new Vector2f(0, 1);
	private Vector2f dir= new Vector2f(0, 1);
	private TypeWrapper wrapper= new TypeWrapper(0.0f);
	private MoveComponent mc= new MoveComponent(pos, vel, acc, dir, wrapper, wrapper);

	@Test
	public void testMove() {
		Timestep t= new Timestep();
		t.start();
		t.end();
		t.calculateDeltatime();
		mc.move(t);
		assertTrue(!pos.equals(new Point2f(5, 6)));
	}

	@Test
	public void testSetVelocity() {
		Vector2f vec= new Vector2f(2, 3);
		mc.setVelocity(vec);
		assertTrue(vel.equals(vec));
	}

	@Test
	public void testSetAcceleration() {
		Vector2f vec= new Vector2f(3, 3);
		mc.setAcceleration(vec);
		assertTrue(acc.equals(vec));
	}

	@Test
	public void testSetDirection() {
		Vector2f vec= new Vector2f(4, 3);
		mc.setDirection(vec);
		assertTrue(dir.equals(vec));
	}

	@Test
	public void testSetPosition() {
		Point2f point= new Point2f(67, 3);
		mc.setPosition(point);
		assertTrue(pos.equals(point));
	}
}