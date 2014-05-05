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

import com.whathappensingandalf.howdoiflythisthing.Projectile.Message;
import com.whathappensingandalf.howdoiflythisthing.factorys.AsteroidFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

public class ProjectileTest implements PropertyChangeListener{
	
	private boolean isRemoved= false;
	private Vector2f vec= new Vector2f(5, 5);
	private Projectile pro= new Projectile(new Point2f(1, 1), vec, vec, new Vector2f(0, 1), 3, 3);
	private Projectile pro2= new Projectile(new Point2f(1, 1), vec, vec, new Vector2f(0, 1), 3, 3);
//											position, velocity, acceleration, direction, width, height
	private Spaceship ss= SpaceshipFactory.create(new Point2f(1, 1), vec, 50, 50);
	private Asteroid ast= AsteroidFactory.create(new Point2f(1, 1), 100, 100);

	@Before
	public void setUp() {
		isRemoved= false;
	}
	
	@After
	public void tearDown() {
		pro.removePropertyChangeListener(this);
	}
	
	@Test
	public void testProjectilePoint2fVector2fVector2fVector2fIntInt(){
		Point2f pos= new Point2f(1, 1);
		Vector2f vel= new Vector2f(2, 2);
		Vector2f acc= new Vector2f(3, 3);
		Vector2f dir= new Vector2f(4, 4);
		int w= 10;
		int h= 7;
		Projectile p= new Projectile(pos, vel, acc, dir, w, h);
		assertTrue(p.getPosition().equals(pos) && p.getVelocity().equals(vel) && p.getAcceleration().equals(acc) &&
				p.getDirection().equals(dir) && p.getWidth()== w && p.getHeight()== h);
		}

	@Test
	public void testProjectileProjectile() {
		Point2f pos= new Point2f(1, 1);
		Vector2f vel= new Vector2f(2, 2);
		Vector2f acc= new Vector2f(3, 3);
		Vector2f dir= new Vector2f(4, 4);
		int w= 10;
		int h= 7;
		Projectile p= new Projectile(pos, vel, acc, dir, w, h);
		Projectile pr= new Projectile(p);
		assertTrue(pr.getPosition().equals(pos) && pr.getVelocity().equals(vel) && pr.getAcceleration().equals(acc) &&
				pr.getDirection().equals(dir) && pr.getWidth()== w && pr.getHeight()== h);
	}

	@Test
	public void testMove(){
		Timestep timestep= new Timestep();
		timestep.start();
		timestep.end();
		timestep.calculateDeltatime();
		pro.move(timestep);
		assertTrue(pro.getPosition().x > 1);
	}

	@Test
	public void testRemove(){
//		isRemoved= false;
		System.out.println(isRemoved);
		pro.addPropertyChangeListener(this);
		pro.remove();
		assertTrue(isRemoved);
	}

	@Test
	public void testGetVelocity() {
		assertTrue(pro.getVelocity().x== 5 && pro.getVelocity().y== 5);
	}

	@Test
	public void testGetAcceleration() {
		assertTrue(pro.getAcceleration().x== 5 && pro.getAcceleration().y== 5);
	}

	@Test
	public void testGetDirection() {
		assertTrue(pro.getDirection().x== 0 && pro.getDirection().y== 1);
	}

	@Test
	public void testGetPosition() {
		assertTrue(pro.getPosition().x== 1 && pro.getPosition().y== 1);
	}

	@Test
	public void testSetVelocity() {
		pro.setVelocity(new Vector2f(4, 5));
		assertTrue(pro.getVelocity().x== 4 && pro.getVelocity().y== 5);
	}

	@Test
	public void testSetAcceleration() {
		pro.setAcceleration(new Vector2f(56, 7));
		assertTrue(pro.getAcceleration().x== 56 && pro.getAcceleration().y== 7);
	}

	@Test
	public void testSetDirection() {
		pro.setDirection(new Vector2f(6, 7));
		assertTrue(pro.getDirection().x== 6 && pro.getDirection().y== 7);
	}

	@Test
	public void testSetPosition() {
		pro.setPosition(new Point2f(2, 2));
		assertTrue(pro.getPosition().x== 2 && pro.getPosition().y== 2);
	}

	/*
	@Test
	public void testGetRotVelocity() {
		assertTrue(true);
	}

	@Test
	public void testGetRotAcceleration() {
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
	*/
	@Test
	public void testGetDamage() {
		assertTrue(pro.getDamage()== 1);
	}

	@Test
	public void testGetHeight() {
		assertTrue(pro.getHeight()== 3);
	}

	@Test
	public void testGetWidth() {
		assertTrue(pro.getWidth()== 3);
	}

	@Test
	public void testGetType() {
		assertTrue(pro.getType().equals("PROJECTILE"));
	}

	@Test
	public void testCollideDetection() {
		assertTrue(pro.collideDetection(pro2));
	}

	@Test
	public void testAccept() {
//		isRemoved= false;
		pro.addPropertyChangeListener(this);
		pro.accept(pro2);
		assertTrue(!isRemoved);
	}

	@Test
	public void testVisitSpaceship() {
//		isRemoved= false;
		pro.addPropertyChangeListener(this);
		pro.visit(ss);
		assertTrue(isRemoved);
	}

	@Test
	public void testVisitProjectile() {
		pro.visit(pro);
		assertTrue(pro.equals(pro2));
	}

	@Test
	public void testVisitAsteroid() {
//		isRemoved= false;
		pro.addPropertyChangeListener(this);
		pro.visit(ast);
		assertTrue(isRemoved);
	}

	@Test
	public void testAddPropertyChangeListener() {
//		isRemoved= false;
		pro.addPropertyChangeListener(this);
		pro.remove();
		assertTrue(isRemoved);
	}

	@Test
	public void testRemovePropertyChangeListener() {
//		isRemoved= false;
		pro.removePropertyChangeListener(this);
		pro.remove();;
		assertTrue(!isRemoved);
	}

	@Test
	public void testClone() {
		Projectile p= pro.clone();
		assertTrue(p.equals(pro) && (p!=pro));
	}

	public void propertyChange(PropertyChangeEvent e) {
		isRemoved= e.getPropertyName().equals(Message.PROJECTILE_DIE.toString());
	}
	
	@Test
	public void testEquals(){
		assertTrue(pro.equals(pro2));
	}
}//end ProjectileTest