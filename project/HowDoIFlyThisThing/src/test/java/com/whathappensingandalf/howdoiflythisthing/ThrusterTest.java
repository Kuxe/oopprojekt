package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThrusterTest {

	@Test
	public void testThruster() {
		Thruster t=new Thruster(10,20,50,1);
		assertTrue(t.getDirection().x!=10 && t.getDirection().x!=20 && t.getDirection().x<t.getDirection().y && t.getRotationAcceleration()==0);
		t.activate();
		assertTrue(t.getDirection().x!=10 && t.getDirection().x!=20 && t.getDirection().x<t.getDirection().y && t.getRotationAcceleration()==1);
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
		Thruster t=new Thruster(10,20,50,1);
		t.activate();
		assertTrue(t.isThrusterActive());
		t.deactivate();
		assertTrue(!t.isThrusterActive());
	}

	@Test
	public void testSetMagnitude() {
		Thruster t=new Thruster(10,20,50,1);
		t.setMagnitude(100);
		assertTrue(t.getDirection().length()==100);
	}

	@Test
	public void testGetAcceleration() {
		Thruster t=new Thruster(10,20,50,1);
		t.deactivate();
		assertTrue(t.getAcceleration().x==0 && t.getAcceleration().y==0);
		t.activate();
		assertTrue(t.getAcceleration().x<t.getAcceleration().y);
	}

	@Test
	public void testGetRotationAcceleration() {
		Thruster t=new Thruster(10,20,50,1);
		t.deactivate();
		assertTrue(t.getRotationAcceleration()==0);
		t.activate();
		assertTrue(t.getRotationAcceleration()==1);
	}

	@Test
	public void testIsThrusterActive() {
		Thruster t=new Thruster(10,20,50,1);
		t.deactivate();
		assertTrue(!t.isThrusterActive());
	}

}
