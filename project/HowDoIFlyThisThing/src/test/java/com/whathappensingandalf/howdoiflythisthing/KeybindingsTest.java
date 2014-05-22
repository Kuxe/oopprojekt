/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Martin
 */
public class KeybindingsTest {
	
	private Keybindings keybindings;
	
	public KeybindingsTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		keybindings = new Keybindings();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testReset() {
		keybindings.setFire(0);
		keybindings.setLeft(0);
		keybindings.setMain(0);
		keybindings.setRight(0);
		keybindings.reset();
		assertTrue(keybindings.getFire()==Keyboard.KEY_SPACE);
		assertTrue(keybindings.getLeft()==Keyboard.KEY_A);
		assertTrue(keybindings.getMain()==Keyboard.KEY_W);
		assertTrue(keybindings.getRight()==Keyboard.KEY_D);	
	}

	@Test
	public void testSetLeft() {
		keybindings.setLeft(Keyboard.KEY_0);
		assertTrue(keybindings.getLeft()==Keyboard.KEY_0);
	}

	@Test
	public void testSetMain() {
		keybindings.setMain(Keyboard.KEY_0);
		assertTrue(keybindings.getMain()==Keyboard.KEY_0);
	}

	@Test
	public void testSetRight() {
		keybindings.setRight(Keyboard.KEY_0);
		assertTrue(keybindings.getRight()==Keyboard.KEY_0);
	}

	@Test
	public void testSetFire() {
		keybindings.setFire(Keyboard.KEY_0);
		assertTrue(keybindings.getFire()==Keyboard.KEY_0);
	}

	@Test
	public void testGetLeft() {
		assertTrue(keybindings.getLeft()==Keyboard.KEY_A);
	}

	@Test
	public void testGetMain() {
		assertTrue(keybindings.getMain()==Keyboard.KEY_W);
	}

	@Test
	public void testGetRight() {
		assertTrue(keybindings.getRight()==Keyboard.KEY_D);
	}

	@Test
	public void testGetFire() {
		assertTrue(keybindings.getFire()==Keyboard.KEY_SPACE);
	}
	
}
