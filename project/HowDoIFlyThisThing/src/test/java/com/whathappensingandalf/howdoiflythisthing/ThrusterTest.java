package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThrusterTest {

	@Test
	public void testThruster() {
		Thruster t=new Thruster(10,20,50,1);
		assertTrue(t.getDirection().x==10 && t.getDirection().y==20 && t.getDirection().length()==50 && t.getRotationAcceleration()==1);
	}

	@Test
	public void testActivate() {
		Thruster t=new Thruster(10,20,50,1);
		assertTrue(!t.isThrusterActive());
		t.activate();
		assertTrue(t.isThrusterActive());
	}

	@Test
	public void testDeactivate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMagnitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAcceleration() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRotationAcceleration() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsThrusterActive() {
		fail("Not yet implemented");
	}

}
