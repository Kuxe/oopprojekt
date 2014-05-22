package com.whathappensingandalf.howdoiflythisthing;

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
public class TimestepTest {
	
	Timestep time;
	
	public TimestepTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		time = new Timestep();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testStart() {
		time.start();
		time.calculateDeltatime();
		assertTrue(time.getDelta()<0);
	}

	@Test
	public void testEnd() {
		time.start();
		time.end();
		time.calculateDeltatime();
		assertTrue(time.getDelta()>0);
	}

	@Test
	public void testCalculateDeltatime() {
		time.calculateDeltatime();
		assertTrue(time.getDelta()==0);
	}

	@Test
	public void testGetDelta() {
		assertTrue(time.getDelta()==0);
	}
	
}
