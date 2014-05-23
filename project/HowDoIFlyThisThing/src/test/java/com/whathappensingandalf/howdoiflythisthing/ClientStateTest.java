package com.whathappensingandalf.howdoiflythisthing;

import controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ClientStateTest {
	
	private ClientState cState;
	private String ip;
	private Keybindings keybindings;
	private Controller controller;
	
	public ClientStateTest() {
	}
	
	@BeforeClass
	public static void setUpClass(){
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() throws IOException {
		ip = "127.0.0.1";
		keybindings = new Keybindings();
		controller = new Controller(1,2,3,4);
	}
	
	@After
	public void tearDown() {
		controller.cleanup();
	}

	@Test
	public void testGetState(){
		try {
			cState = new ClientState(ip, keybindings);
			assertTrue(cState.getState().toString().equals(IModelNetworkState.State.CLIENT.toString()));
		} catch (IOException ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testAddUser() throws IOException {
		try {
			cState = new ClientState(ip, keybindings);
			cState.addUser(0);
			assertTrue(true);
		} catch (IOException ex) {
			assertTrue(false);
		} catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	public void testUpdate() {
		assertTrue(false);
	}

	@Test
	public void testGetDrawableData() {
		try {
			cState = new ClientState(ip, keybindings);
			assertTrue(cState.getDrawableData().isEmpty());
		} catch (IOException ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetSpaceshipPoint() {
		try {
			cState = new ClientState(ip, keybindings);
			assertTrue(cState.getSpaceshipPoint().x==0);
			assertTrue(cState.getSpaceshipPoint().y==0);
		} catch (IOException ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testCleanup() {
	}

	@Test
	public void testGetListOfSounds() {
	}

	@Test
	public void testGetHull() {
	}

	@Test
	public void testGetShield() {
	}

	@Test
	public void testGetCountdown() {
	}

	@Test
	public void testGetModelStatus() {
	}

	@Test
	public void testGetWorldBorder() {
	}

	@Test
	public void testAddPropertyChangeListener() {
	}
	
}
