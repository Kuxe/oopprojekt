package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.TypeWrapper;

public class ThrusterComponentTest {
	
	private Vector2f acceleration= new Vector2f(1, 0);
	private Vector2f direction= new Vector2f(1, 0);
	private TypeWrapper rotationAcceleration= new TypeWrapper(0.0f);
	private TypeWrapper rotationVelocity= new TypeWrapper(0.0f);
	private ThrusterComponent tc= new ThrusterComponent(acceleration, direction, rotationAcceleration, rotationVelocity);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculateAceleration() {
		tc.activateLeftThruster();
		tc.activateMainThruster();
		tc.activateRightThruster();
		tc.calculateAceleration();
		assertTrue(acceleration.x == 0);
		assertTrue(acceleration.y == 220);
	}

	@Test
	public void testCalculateRotation() {
		Timestep time= new Timestep();
		time.start();
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		time.end();
		time.calculateDeltatime();
		
		tc.activateLeftThruster();
		tc.calculateRotation(time);
		
		assertTrue(rotationAcceleration.getValue() > Math.PI * time.getDelta() - 0.001);
		assertTrue(rotationAcceleration.getValue() < Math.PI * time.getDelta() + 0.001);
		assertTrue(rotationVelocity.getValue() == rotationAcceleration.getValue());
	}

	@Test
	public void testActivateMainThruster() {
//		TODO
		assertTrue(true);
	}

	@Test
	public void testActivateLeftThruster() {
		assertTrue(true);
	}

	@Test
	public void testActivateRightThruster() {
		assertTrue(true);
	}

	@Test
	public void testDeactivateMainThruster() {
		assertTrue(true);
	}

	@Test
	public void testDeactivateLeftThruster() {
		assertTrue(true);
	}

	@Test
	public void testDeactivateRightThruster() {
		assertTrue(true);
	}

	@Test
	public void testIsMainThusterActive() {
		assertTrue(true);
	}

	@Test
	public void testIsLeftThusterActive() {
		assertTrue(true);
	}

	@Test
	public void testIsRightThusterActive() {
		assertTrue(true);
	}
}//end ThrusterComponentTest