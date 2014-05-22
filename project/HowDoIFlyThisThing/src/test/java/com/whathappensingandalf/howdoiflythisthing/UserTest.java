package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;
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
public class UserTest  implements PropertyChangeListener{
	
	private User user;
	private Spaceship s;
	private boolean shipIsFireing;
	private boolean shipSuicide;
	private boolean UserRequest;
	private String msg;
	
	public UserTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		user = new User();
		s = new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50);
		shipIsFireing = false;
		shipSuicide = false;
		UserRequest = false;
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testGetListOfHoldKeys() {
		assertTrue(user.getListOfHoldKeys().isEmpty());
	}

	@Test
	public void testSetListOfHoldKeys() {
		HashSet list = new HashSet<Integer>();
		list.add(50);
		user.setListOfHoldKeys(list);
		assertTrue(user.getListOfHoldKeys().size()==1);
		assertTrue(user.getListOfHoldKeys().contains(50));
	}

	@Test
	public void testGetSpaceship() {
		assertTrue(user.getSpaceship()==null);
		user.setSpaceship(s);
		assertTrue(user.getSpaceship().equals(s));
	}

	@Test
	public void testSetSpaceship() {
		user.setSpaceship(s);
		assertTrue(user.getSpaceship().equals(s));
	}

	@Test
	public void testSetLeftButton() {
		user.setLeftButton(Keyboard.KEY_0);
		user.setSpaceship(s);
		HashSet list = new HashSet<Integer>();
		list.add(Keyboard.KEY_0);
		user.executeInput(list);
		assertTrue(s.isLeftThusterActive());
	}

	@Test
	public void testSetRightButton() {
		user.setRightButton(Keyboard.KEY_0);
		user.setSpaceship(s);
		HashSet list = new HashSet<Integer>();
		list.add(Keyboard.KEY_0);
		user.executeInput(list);
		assertTrue(s.isRightThusterActive());
	}

	@Test
	public void testSetMainButton() {
		user.setMainButton(Keyboard.KEY_0);
		user.setSpaceship(s);
		HashSet list = new HashSet<Integer>();
		list.add(Keyboard.KEY_0);
		user.executeInput(list);
		assertTrue(s.isMainThusterActive());
	}

	@Test
	public void testSetFireButton() {
		s.addPropertyChangeListener(this);
		user.setFireButton(Keyboard.KEY_0);
		user.setSpaceship(s);
		HashSet list = new HashSet<Integer>();
		list.add(Keyboard.KEY_0);
		user.executeInput(list);
		assertTrue(shipIsFireing);
	}

	@Test
	public void testSetKeybindings() {
		Keybindings k = new Keybindings();
		k.setFire(Keyboard.KEY_0);
		user.setKeybindings(k);
		s.addPropertyChangeListener(this);
		user.setSpaceship(s);
		HashSet list = new HashSet<Integer>();
		list.add(Keyboard.KEY_0);
		user.executeInput(list);
		assertTrue(shipIsFireing);
	}

	@Test
	public void testExecuteInput() {
		s.addPropertyChangeListener(this);
		user.setSpaceship(s);
		HashSet list = new HashSet<Integer>();
		list.add(Keyboard.KEY_SPACE);
		user.executeInput(list);
		assertTrue(shipIsFireing);
	}

	@Test
	public void testGetSpaceshipPoint() {
		assertTrue(user.getSpaceshipPoint().x==1);
		assertTrue(user.getSpaceshipPoint().y==1);
		user.setSpaceship(s);
		s.setPosition(new Point2f(10,10));
		assertTrue(user.getSpaceshipPoint().x==10);
		assertTrue(user.getSpaceshipPoint().y==10);
	}

	@Test
	public void testSuicide() {
		s.addPropertyChangeListener(this);
		user.setSpaceship(s);
		user.suicide();
		assertTrue(shipSuicide);
	}

	@Test
	public void testAddPropertyChangeListener() {
		user.addPropertyChangeListener(this);
		user.requestSpaceship();
		assertTrue(UserRequest);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		user.addPropertyChangeListener(this);
		user.removePropertyChangeListener(this);
		user.requestSpaceship();
		assertFalse(UserRequest);
	}

	@Test
	public void testGetState() {
		assertTrue(user.getState().toString().equals("SPECTATOR_STATE"));
		user.setSpaceship(s);
		assertTrue(user.getState().toString().equals("PLAYER_STATE"));
	}

	@Test
	public void testRequestSpaceship() {
		user.addPropertyChangeListener(this);
		user.requestSpaceship();
		assertTrue(UserRequest);
	}

	@Test
	public void testPropertyChange() {
		s.addPropertyChangeListener(user);
		user.addPropertyChangeListener(this);
		s.remove();
		assertTrue(shipSuicide);
	}

	@Test
	public void testGetHull() {
		assertTrue(user.getHull()==0);
		user.setSpaceship(s);
		assertTrue(user.getHull()==10);
	}

	@Test
	public void testGetShield() {
		assertTrue(user.getShield()==0);
		user.setSpaceship(s);
		assertTrue(user.getShield()==3);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_FIRE.toString())){
			shipIsFireing = true;
		}else if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())){
			shipSuicide = true;
		}else if(evt.getPropertyName().equals(User.Message.REQUEST_SPACESHIP.toString())){
			UserRequest = true;
		}else if(evt.getPropertyName().equals(User.Message.LOST_SPACESHIP.toString())){
			shipSuicide = true;
		}
	}
	
}
