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

import com.whathappensingandalf.howdoiflythisthing.CookieCracker;


public class CookieCrackerTest implements PropertyChangeListener{
	
	private Point2f position= new Point2f(0, 0);
	private Vector2f velocity= new Vector2f(0, 0);
	private Vector2f acceleration= new Vector2f(0, 0);
	private Vector2f direction= new Vector2f(0, 0);
	private int width= 0;
	private int height= 0;
	private CookieCracker cc= new CookieCracker(position, velocity, acceleration, direction, width, height);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMove() {
		assertTrue(true);
	}

	@Test
	public void testRemove() {
		assertTrue(true);
	}

	@Test
	public void testGetVelocity() {
		assertTrue(true);
	}

	@Test
	public void testGetAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testGetDirection() {
		assertTrue(true);
	}

	@Test
	public void testGetPosition() {
		assertTrue(true);
	}

	@Test
	public void testGetRotVelocity() {
		assertTrue(true);
	}

	@Test
	public void testGetRotAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testSetVelocity() {
		assertTrue(true);
	}

	@Test
	public void testSetAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testSetDirection() {
		assertTrue(true);
	}

	@Test
	public void testSetPosition() {
		assertTrue(true);
	}

	@Test
	public void testSetRotVelocity() {
		assertTrue(true);
	}

	@Test
	public void testSetRotAcceleration() {
		assertTrue(true);
	}

	@Test
	public void testCollideDetection() {
		assertTrue(true);
	}

	@Test
	public void testGetHeight() {
		assertTrue(true);
	}

	@Test
	public void testGetWidth() {
		assertTrue(true);
	}

	@Test
	public void testGetType() {
		assertTrue(true);
	}

	@Test
	public void testAccept() {
		assertTrue(true);
	}

	@Test
	public void testVisitSpaceship() {
		assertTrue(true);
	}

	@Test
	public void testVisitIProjectile() {
		assertTrue(true);
	}

	@Test
	public void testVisitAsteroid() {
		assertTrue(true);
	}

	@Test
	public void testVisitCookieCracker() {
		assertTrue(true);
	}

	@Test
	public void testVisitIPickup() {
		assertTrue(true);
	}

	@Test
	public void testGetDamage() {
		assertTrue(true);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		assertTrue(true);
	}

	@Test
	public void testAddPropertyChangeListener() {
		assertTrue(true);
	}

	@Test
	public void testClone() {
		assertTrue(true);
	}

	@Test
	public void testGetCollectionDrawables() {
		assertTrue(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("PROJECTILE_DIE")){
			
		}
	}
}