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
		tc.calculateAceleration();
		boolean deactivate= (acceleration.x == 0 && acceleration.y == 0);
		tc.activateMainThruster();
		tc.calculateAceleration();
		boolean activate= (acceleration.x == 0 && acceleration.y == 200);
		assertTrue(deactivate);
		assertTrue(activate);
	}

	@Test
	public void testActivateLeftThruster() {
		tc.calculateAceleration();
		boolean deactivate= (acceleration.x == 0 && acceleration.y == 0);
		tc.activateLeftThruster();
		tc.calculateAceleration();
		boolean activate= (acceleration.x == 0 && acceleration.y == 10);
		assertTrue(deactivate);
		assertTrue(activate);
	}

	@Test
	public void testActivateRightThruster() {
		tc.calculateAceleration();
		boolean deactivate= (acceleration.x == 0 && acceleration.y == 0);
		tc.activateRightThruster();
		tc.calculateAceleration();
		boolean activate= (acceleration.x == 0 && acceleration.y == 10);
		assertTrue(deactivate);
		assertTrue(activate);
	}

	@Test
	public void testDeactivateMainThruster() {
		tc.activateMainThruster();
		tc.calculateAceleration();
		boolean activate= (acceleration.x == 0 && acceleration.y == 200);
		tc.deactivateMainThruster();
		tc.calculateAceleration();
		boolean deactivate= (acceleration.x == 0 && acceleration.y == 0);
		assertTrue(activate);
		assertTrue(deactivate);
	}

	@Test
	public void testDeactivateLeftThruster() {
		tc.activateLeftThruster();
		tc.calculateAceleration();
		boolean activate= (acceleration.x == 0 && acceleration.y == 10);
		tc.deactivateLeftThruster();
		tc.calculateAceleration();
		boolean deactivate= (acceleration.x == 0 && acceleration.y == 0);
		assertTrue(activate);
		assertTrue(deactivate);
	}

	@Test
	public void testDeactivateRightThruster() {
		tc.activateRightThruster();
		tc.calculateAceleration();
		boolean activate= (acceleration.x == 0 && acceleration.y == 10);
		tc.deactivateRightThruster();
		tc.calculateAceleration();
		boolean deactivate= (acceleration.x == 0 && acceleration.y == 0);
		assertTrue(activate);
		assertTrue(deactivate);
	}

	@Test
	public void testIsMainThusterActive() {
		boolean d= tc.isMainThusterActive();
		tc.activateMainThruster();
		boolean a= tc.isMainThusterActive();
		assertTrue(!d);
		assertTrue(a);
	}

	@Test
	public void testIsLeftThusterActive() {
		boolean d= tc.isLeftThusterActive();
		tc.activateLeftThruster();
		boolean a= tc.isLeftThusterActive();
		assertTrue(!d);
		assertTrue(a);
	}

	@Test
	public void testIsRightThusterActive() {
		boolean d= tc.isRightThusterActive();
		tc.activateRightThruster();
		boolean a= tc.isRightThusterActive();
		assertTrue(!d);
		assertTrue(a);
	}
}//end ThrusterComponentTest