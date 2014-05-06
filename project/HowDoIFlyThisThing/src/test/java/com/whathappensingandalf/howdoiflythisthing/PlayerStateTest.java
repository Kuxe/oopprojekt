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
	private boolean bo3= false;
	private Point2f pos= new Point2f(1, 2);
	private Vector2f dir= new Vector2f(3, 4);
	private int w= 50;
	private int h= 40;
	private int number= 0;
	private Point2f pos2= new Point2f(1, 2);
	private Vector2f dir2= new Vector2f(3, 4);
	private int w2= 50;
	private int h2= 40;
	private Spaceship s1= new Spaceship(pos, dir, w, h);
	private Spaceship s2= new Spaceship(pos2, dir2, w2, h2);

	@Before
	public void setUp() throws Exception {
		bo1= false;
		bo2= true;
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
//		TODO- better way to check time in spaceship
		ps.setSpaceship(s1);
		ps.getSpaceship().addPropertyChangeListener(this);
		ps.fireHold(true);	//hope bo1 becomes true
		ps.fireHold(true);	//hope bo3 is unchanged (still false), time should been to short for ArmsComponent to
							//	accept Spaceship to fire again
		ps.fireHold(false);	//hope all variables are unchanged
		assertTrue(bo1 && bo2 && !bo3);
	}

	@Test
	public void testGetSpaceshipPosition() {
		ps.setSpaceship(s1);
		ps.getSpaceship().setPosition(new Point2f(34, 58));
		assertTrue(ps.getSpaceshipPosition().equals(new Point2f(34, 58)));
	}

	@Test
	public void testGetSpaceship() {
		ps.setSpaceship(s1);
		assertTrue(ps.getSpaceship().equals(s1));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("SPACESHIP_FIRE")){
			if(number== 0){
				bo1= true;
				number++;
			}else if(number== 1){
				bo3= true;
			}
		}

	}
}//end PlayerStateTest