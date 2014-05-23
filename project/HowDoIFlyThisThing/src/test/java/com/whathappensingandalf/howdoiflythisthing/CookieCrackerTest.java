package com.whathappensingandalf.howdoiflythisthing;
import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.TypeWrapper;

import com.whathappensingandalf.howdoiflythisthing.CookieCracker;


public class CookieCrackerTest implements PropertyChangeListener{
	
	private Point2f position= new Point2f(1, 1);
	private Vector2f velocity= new Vector2f(1, 1);
	private Vector2f acceleration= new Vector2f(1, 1);
	private Vector2f direction= new Vector2f(1, 1);
	private int width= 1;
	private int height= 1;
	private CookieCracker cc= new CookieCracker(position, velocity, acceleration, direction, width, height);
	private boolean pcsChange= false;
	
	@Before
	public void setUp() throws Exception {
		pcsChange= false;
		cc.addPropertyChangeListener(this);
	}

	@Test
	public void testMove() {
		Timestep timestep= new Timestep();
		timestep.start();
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		timestep.end();
		timestep.calculateDeltatime();
		cc.move(timestep);
		assertTrue(cc.getPosition().x > velocity.x * timestep.getDelta() + 0.99);
		assertTrue(cc.getPosition().x < velocity.x * timestep.getDelta() + 1.10);
		assertTrue(cc.getPosition().y > velocity.y * timestep.getDelta() + 0.99);
		assertTrue(cc.getPosition().y < velocity.y * timestep.getDelta() + 1.10);
	}

	@Test
	public void testRemove() {
		cc.remove();
		assertTrue(pcsChange);
	}

	@Test
	public void testGetVelocity() {
		Vector2f vel= cc.getVelocity();
		assertTrue(vel.x == velocity.x);
	}

	@Test
	public void testGetAcceleration() {
		Vector2f acc= cc.getAcceleration();
		assertTrue(acc.x == acceleration.x);
		assertTrue(acc.y == acceleration.y);
	}

	@Test
	public void testGetDirection() {
		Vector2f dir= cc.getDirection();
		assertTrue(dir.x == direction.x);
		assertTrue(dir.y == direction.y);
	}

	@Test
	public void testGetPosition() {
		Point2f pos= cc.getPosition();
		assertTrue(pos.x == position.x);
		assertTrue(pos.y == position.y);
	}

	@Test
	public void testGetRotVelocity() {
		assertTrue(cc.getRotVelocity()== 0.0f);
	}

	@Test
	public void testGetRotAcceleration() {
		assertTrue(cc.getRotAcceleration()== 0.0f);
	}

	@Test
	public void testSetVelocity() {
		Vector2f vel= new Vector2f(3, 4);
		cc.setVelocity(vel);		
		assertTrue(vel.equals(cc.getVelocity()));
	}

	@Test
	public void testSetAcceleration() {
		Vector2f acc= new Vector2f(3, 4);
		cc.setAcceleration(acc);
		assertTrue(acc.equals(cc.getAcceleration()));
	}

	@Test
	public void testSetDirection() {
		Vector2f dir= new Vector2f(3, 4);
		cc.setDirection(dir);
		assertTrue(dir.equals(cc.getDirection()));
	}

	@Test
	public void testSetPosition() {
		Point2f pos= new Point2f(3, 4);
		cc.setPosition(pos);
		assertTrue(pos.equals(cc.getPosition()));
	}

	@Test
	public void testSetRotVelocity() {
		float rotVel= 5.0f;
		cc.setRotVelocity(rotVel);
		assertTrue(rotVel == cc.getRotVelocity());
	}

	@Test
	public void testSetRotAcceleration() {
		float rotAcc= 5.0f;
		cc.setRotAcceleration(rotAcc);
		assertTrue(rotAcc == cc.getRotAcceleration());
	}

	@Test
	public void testCollideDetection() {
		CookieCracker cc2= new CookieCracker(position, velocity, acceleration, direction, width, height);
		CookieCracker cc3= new CookieCracker(new Point2f(100, 100), velocity, acceleration, direction, width, height);
		assertTrue(cc.collideDetection(cc2));
		assertTrue(!(cc.collideDetection(cc3)));
	}

	@Test
	public void testGetHeight() {
		assertTrue(cc.getHeight() == 1);
	}

	@Test
	public void testGetWidth() {
		assertTrue(cc.getWidth() == 1);
	}

	@Test
	public void testGetType() {
		assertTrue(cc.getType().equals("COOKIE_CRACKER"));
	}

	@Test
	public void testAccept() {
		CookieCracker cc2= new CookieCracker(position, velocity, acceleration, direction, width, height);
		cc2.accept(cc);
		assertTrue(cc.getAcceleration().equals(acceleration));
		assertTrue(cc.getVelocity().equals(velocity));
		assertTrue(cc.getAcceleration().equals(acceleration));
		assertTrue(cc.getDirection().equals(direction));
		assertTrue(cc.getWidth() == width);
		assertTrue(cc.getHeight() == height);
	}

	@Test
	public void testVisitSpaceship() {
		Spaceship spaceship= new Spaceship(position, direction, width, height);
		cc.visit(spaceship);
		assertTrue(pcsChange);
	}

	@Test
	public void testVisitIProjectile() {
		Bullet bullet= new Bullet(position, velocity, acceleration, direction, width, height);
		cc.visit(bullet);
		assertTrue(pcsChange);
	}

	@Test
	public void testVisitAsteroid() {
		Asteroid asteroid= new Asteroid(position, width, height);
		cc.visit(asteroid);
		assertTrue(pcsChange);
	}

	@Test
	public void testVisitCookieCracker() {
		CookieCracker cc2= new CookieCracker(position, velocity, acceleration, direction, width, height);
		cc.visit(cc2);
		assertTrue(!pcsChange);
	}

	@Test
	public void testVisitIPickup() {
		HealthPickup hp= new HealthPickup(position, 3, 3);
		cc.visit(hp);
		assertTrue(!pcsChange);
	}
	
	@Test
	public void testGetDamage() {
		assertTrue(cc.getDamage() == 1);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		cc.removePropertyChangeListener(this);
		cc.accept(cc);
		assertTrue(!pcsChange);
	}

	@Test
	public void testAddPropertyChangeListener() {
		Bullet bullet= new Bullet(position, velocity, acceleration, direction, width, height);
		cc.removePropertyChangeListener(this);
		cc.visit(bullet);
		boolean temp= pcsChange;
		cc.addPropertyChangeListener(this);
		cc.visit(bullet);
		assertTrue(!temp);
		assertTrue(pcsChange);
	}

	@Test
	public void testClone() {
		CookieCracker cc2= cc.clone();
		assertTrue(cc2.getAcceleration().equals(cc.getAcceleration()));
		assertTrue(cc2.getDirection().equals(cc.getDirection()));
		assertTrue(cc2.getHeight() == cc.getHeight());
		assertTrue(cc2.getPosition().equals(cc.getPosition()));
		assertTrue(cc2.getRotAcceleration() == cc.getRotAcceleration());
		assertTrue(cc2.getRotVelocity() == cc.getRotVelocity());
		assertTrue(cc2.getVelocity().equals(cc.getVelocity()));
		assertTrue(cc2.getWidth() == cc.getWidth());
	}

	@Test
	public void testGetCollectionDrawables() {
		boolean boo= false;
		Iterator<? extends DrawableData> it= cc.getCollectionDrawables().iterator();
		while(it.hasNext() && !boo){
			DrawableData dd= (DrawableData)it.next();
			boo= (dd.getDirection().equals(cc.getDirection()) &&
					dd.getPosition().equals(cc.getPosition()) &&
					dd.getType().equals(cc.getType()) &&
					dd.getHeight()== cc.getHeight() &&
					dd.getWidth() == cc.getWidth());
			assertTrue(boo);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("PROJECTILE_DIE")){
			pcsChange= true;
		}
	}
}