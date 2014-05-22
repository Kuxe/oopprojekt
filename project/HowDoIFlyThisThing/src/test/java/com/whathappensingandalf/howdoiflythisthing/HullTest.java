package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import org.junit.Test;

public class HullTest {

	@Test
	public void testHull() {
		Hull h=new Hull();
		assertTrue(h.getHull()==10);
	}

	@Test
	public void testHullInt() {
		Hull h=new Hull(100);
		assertTrue(h.getHull()==100);
	}

	@Test
	public void testHurt() {
		Hull h=new Hull();
		h.hurt(1);
		assertTrue(h.getHull()==9);
	}

	@Test
	public void testSetHull() {
		Hull h=new Hull();
		h.setHull(1);
		assertTrue(h.getHull()==1);
	}

	@Test
	public void testRepair() {
		Hull h=new Hull();
		h.repair(10);
		assertTrue(h.getHull()==10);
		h.setHull(1);
		h.repair(2);
		assertTrue(h.getHull()==3);
	}

	@Test
	public void testGetHull() {
		Hull h=new Hull();
		assertTrue(h.getHull()==10);
	}

}
