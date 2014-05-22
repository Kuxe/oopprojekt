package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShieldTest {

	@Test
	public void testShield() {
		Shield s=new Shield();
		assertTrue(s.getShield()==3);
	}

	@Test
	public void testShieldInt() {
		Shield s=new Shield(10);
		assertTrue(s.getShield()==10);
	}

	@Test
	public void testHurt() {
		Shield s=new Shield(3);
		assertTrue(s.hurt(1)==0);
		assertTrue(s.hurt(3)==1);
	}

	@Test
	public void testRecharge() {
		Shield s=new Shield(3);
		s.hurt(1);
		assertTrue(s.getShield()==2);
		s.recharge();
		assertTrue(s.getShield()==3);
	}

	@Test
	public void testSetShield() {
		Shield s=new Shield();
		s.setShield(10);
		assertTrue(s.getShield()==3);
		s.setShield(1);
		assertTrue(s.getShield()==1);
	}

	@Test
	public void testGetShield() {
		Shield s=new Shield(3);
		assertTrue(s.getShield()==3);
	}

}
