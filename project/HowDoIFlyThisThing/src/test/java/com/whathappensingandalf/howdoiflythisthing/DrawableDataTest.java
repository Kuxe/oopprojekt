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
public class DrawableDataTest {
	
	private DrawableData drawDat;
	
	public DrawableDataTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		drawDat = new DrawableData(new Point2f(1,1), 2, 3, new Vector2f(4,4), "TYPE");
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testGetPosition() {
		assertTrue(drawDat.getPosition().x==1);
		assertTrue(drawDat.getPosition().y==1);
	}

	@Test
	public void testGetDirection() {
		assertTrue(drawDat.getDirection().x==4);
		assertTrue(drawDat.getDirection().y==4);
	}

	@Test
	public void testGetHeight() {
		assertTrue(drawDat.getHeight()==3);
	}

	@Test
	public void testGetWidth() {
		assertTrue(drawDat.getWidth()==2);
	}

	@Test
	public void testGetType() {
		assertTrue(drawDat.getType().equals("TYPE"));
	}
	
}
