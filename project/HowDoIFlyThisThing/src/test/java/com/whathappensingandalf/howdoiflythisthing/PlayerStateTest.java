package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerStateTest implements PropertyChangeListener{
	
	private PlayerState ps= new PlayerState();
	private PlayerState ps2= new PlayerState();
	private boolean bo1= false;
	private boolean bo2= true;
	private Point2f pos= new Point2f(1, 2);
	private Vector2f dir= new Vector2f(3, 4);
	private int w= 50;
	private int h= 40;
	private Point2f pos2= new Point2f(1, 2);
	private Vector2f dir2= new Vector2f(3, 4);
	private int w2= 50;
	private int h2= 40;
	private Spaceship s1= new Spaceship(pos, dir, w, h);
	private Spaceship s2= new Spaceship(pos2, dir2, w2, h2);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetSpaceship() {
		ps.setSpaceship(s1);
		assertTrue(ps.getSpaceship().getPosition().equals(pos) && ps.getSpaceship().getDirection().equals(dir) &&
				ps.getSpaceship().getWidth()== w && ps.getSpaceship().getHeight()== h);
	}

	@Test
	public void testMainHold() {
		ps.setSpaceship(s1);
		ps2.setSpaceship(s2);
		ps.mainHold(bo2);	//Testing if
		ps2.mainHold(bo1);	//Testing else
		assertTrue(ps.getSpaceship().isMainThusterActive() && !(ps2.getSpaceship().isMainThusterActive()));
	}

	@Test
	public void testLeftHold() {
		ps.setSpaceship(s1);
		ps2.setSpaceship(s2);
		ps.leftHold(bo2);	//Testing if
		ps2.leftHold(bo1);	//Testing else
		assertTrue(ps.getSpaceship().isLeftThusterActive() && !(ps2.getSpaceship().isLeftThusterActive()));
	}

	@Test
	public void testRightHold() {
		ps.setSpaceship(s1);
		ps2.setSpaceship(s2);
		ps.rightHold(bo2);	//Testing if
		ps2.rightHold(bo1);	//Testing else
		assertTrue(ps.getSpaceship().isRightThusterActive() && !(ps2.getSpaceship().isRightThusterActive()));
	}

	@Test
	public void testFireHold() {
//		TODO
//		ps.getSpaceship().addPropertyChangeListener(this);
		assertTrue(true);
	}

	@Test
	public void testGetSpaceshipPosition() {
//		TODO
		assertTrue(true);
	}

	@Test
	public void testGetSpaceship() {
		ps.setSpaceship(s1);
		assertTrue(ps.getSpaceship().equals(s1));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("SPACESHIP_FIRE")){
			bo1= true;
		}
		
	}

}
