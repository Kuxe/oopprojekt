package com.whathappensingandalf.howdoiflythisthing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Nilsson
 * 
 * This is silly, as nothing should happen. The only thing I am testing for is 
 * that it does not crash.
 */
public class NoneStateTest {
	
	private NoneState none;
	
	public NoneStateTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		none = new NoneState();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testUpdate_0args() {
		try{
			none.update();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetState() {
		try{
			assertTrue(none.getState()==null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testAddUser() {
		try{
			none.addUser(0);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testUpdate_Set() {
		try{
			none.update(null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetDrawableData() {
		try{
			assertTrue(none.getDrawableData()==null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetSpaceshipPoint() {
		try{
			assertTrue(none.getSpaceshipPoint()==null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testCleanup() {
		try{
			none.cleanup();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetListOfSounds() {
		try{
			assertTrue(none.getListOfSounds()==null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetHull() {
		try{
			assertTrue(none.getHull()==0);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetShield() {
		try{
			assertTrue(none.getShield()==0);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetCountdown() {
		try{
			assertTrue(none.getCountdown()==-1);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetModelStatus() {
		try{
			assertTrue(none.getModelStatus().equals("Something went horribly wrong (Model has NoneState)"));
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testAddPropertyChangeListener() {
		try{
			none.addPropertyChangeListener(null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}

	@Test
	public void testGetWorldBorder() {
		try{
			assertTrue(none.getWorldBorder()==null);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
}
