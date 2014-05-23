package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
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
public class SpectatorStateTest {
	
	private SpectatorState specStat;
	
	public SpectatorStateTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		specStat = new SpectatorState();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testMainHold() {
		specStat.mainHold(true);
		assertTrue(specStat.getSpaceshipPosition().y==1-0.01f);
	}

	@Test
	public void testLeftHold() {
		specStat.leftHold(true);
		assertTrue(specStat.getSpaceshipPosition().x==1-0.01f);
	}

	@Test
	public void testRightHold() {
		specStat.rightHold(true);
		assertTrue(specStat.getSpaceshipPosition().x==1+0.01f);
	}

	@Test
	public void testFireHold() {
		specStat.fireHold(true);
		assertTrue(specStat.getSpaceshipPosition().y==1+0.01f);
	}

	@Test
	public void testSetCameraPoint() {
		specStat.setCameraPoint(new Point2f(5,5));
		assertTrue(specStat.getSpaceshipPosition().x==5);
		assertTrue(specStat.getSpaceshipPosition().y==5);
	}

	@Test
	public void testGetSpaceshipPosition() {
		assertTrue(specStat.getSpaceshipPosition().x==1);
		assertTrue(specStat.getSpaceshipPosition().y==1);
	}

	@Test
	public void testGetSpaceship() {
		assertTrue(specStat.getSpaceship()==null);
	}

	@Test
	public void testSuicide() {
		specStat.suicide();
		assertTrue(specStat.getSpaceshipPosition().x==1);
		assertTrue(specStat.getSpaceshipPosition().y==1);
	}

	@Test
	public void testGetState() {
		assertTrue(specStat.getState().toString().equals(PlayerState.State.SPECTATOR_STATE.toString()));
	}

	@Test
	public void testGetHull() {
		assertTrue(specStat.getHull()==0);
	}

	@Test
	public void testGetShield() {
		assertTrue(specStat.getShield()==0);
	}
	
}
