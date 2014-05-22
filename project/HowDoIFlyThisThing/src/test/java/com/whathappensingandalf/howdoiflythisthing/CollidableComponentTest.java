package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;

public class CollidableComponentTest {

	@Test
	public void testCollidableComponentPoint2fDouble() {
		assertTrue(true);
	}
	
	@Test
	public void testCollidableComponentPoint2fIntInt() {
		assertTrue(true);
	}

	@Test
	public void testCollideDetection() {
		CollidableComponent c1=new CollidableComponent(new Point2f(10,10),1);
		ICollidable c2=new Spaceship(new Point2f(10,10),new Vector2f(0,0),2,2);
		assertTrue(c1.collideDetection(c2));
	}

}
