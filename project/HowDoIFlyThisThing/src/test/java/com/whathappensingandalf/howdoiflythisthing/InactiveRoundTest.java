package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
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
		ir.addListener(this);
		User u1 = new User();
		HashSet set = new HashSet<User>();
		ir.addUser(u1, set);
		assertTrue(set.size()==1);
		assertFalse(start);
		User u2 = new User();
		ir.addUser(u2, set);
		assertTrue(set.size()==2);
		assertTrue(start);
	}

	@Test
	public void testRemoveUser() {
		Round round = new Round();
		ir.addListener(this);
		User u1 = new User();
		HashSet set = new HashSet<User>();
		ir.addUser(u1, set);
		ir.removeUser(u1, set, round);
		assertTrue(round.getUsersAlive()==0);
	}

	@Test
	public void testGetState() {
		assertTrue(ir.getState().toString().equals(IRoundstate.State.INACTIVE.toString()));
	}

	@Test
	public void testAddListener() {
		ir.addListener(this);
		User u1 = new User();
		User u2 = new User();
		HashSet set = new HashSet<User>();
		ir.addUser(u1, set);
		ir.addUser(u2, set);
		assertTrue(start);
	}

	@Test
	public void testGetStatus() {
		assertTrue(ir.getStatus().equals("Waiting for more players..."));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(InactiveRound.Message.START_ROUND.toString())){
			start = true;
		}
	}
	
}
