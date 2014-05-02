/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

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
public class TypeWrapperTest {
	private TypeWrapper tw;
	private float defaultFloat;
	
	public TypeWrapperTest() {
		defaultFloat = 5;
		tw = new TypeWrapper(defaultFloat);
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testSetValue() {
		tw.setValue(7);
		assertTrue(tw.getValue()==7);
	}

	@Test
	public void testGetValue() {
		assertTrue(tw.getValue()==this.defaultFloat);
	}

	@Test
	public void testEquals() {
	}
	
}
