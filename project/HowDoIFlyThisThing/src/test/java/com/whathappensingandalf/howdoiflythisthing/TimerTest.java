package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import java.sql.Date;

import utils.Timer;

import org.junit.Test;

public class TimerTest {

	@Test
	public void testTimer() {
		Timer t=new Timer();
		assertTrue(t.getTimerLength()==0 && t.getStartTime().getTime()==0);
	}

	@Test
	public void testTimerInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		Timer t=new Timer();
		Date d=new Date(0);
		t.start();
		assertTrue(t.getStartTime().getTime()>d.getTime());
	}

	@Test
	public void testIsTimerDone() {
		Timer t=new Timer(60000);
		assertTrue(t.isTimerDone());
		t.start();
		assertTrue(!t.isTimerDone());
	}

	@Test
	public void testSetTimerLength() {
		Timer t= new Timer(0);
		t.setTimerLength(10);
		assertTrue(t.getTimerLength()==10);
	}

}
