package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class InactiveRoundTest implements PropertyChangeListener{
	
	private InactiveRound ir;
	private boolean start;
	
	public InactiveRoundTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		ir = new InactiveRound();
		start = false;
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testAddUser() {
	}

	@Test
	public void testRemoveUser() {
	}

	@Test
	public void testGetState() {
	}

	@Test
	public void testAddListener() {
	}

	@Test
	public void testGetStatus() {
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(""));
	}
	
}
