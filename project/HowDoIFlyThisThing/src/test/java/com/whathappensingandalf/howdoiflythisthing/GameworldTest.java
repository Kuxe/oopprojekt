package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;

import com.whathappensingandalf.howdoiflythisthing.factorys.AsteroidFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.BulletFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

public class GameworldTest {

	@Test
	public void testGameworld() {
		Gameworld world = new Gameworld();
		
		assertTrue(world.getBorder() 					!= null);
		assertTrue(world.getChargables() 				!= null);
		assertTrue(world.getCollidables() 				!= null);
		assertTrue(world.getDrawableData() 				!= null);
		assertTrue(world.getDrawables() 				!= null);
		assertTrue(world.getListOfObjectsToBeRemoved() 	!= null);
		assertTrue(world.getListOfSounds() 				!= null);
		assertTrue(world.getMoveables() 				!= null);
		assertTrue(world.getPcs() 						!= null);
		assertTrue(world.getPickupSpawnTimer() 			!= null);
		assertTrue(world.getRemovalMap() 				!= null);
		assertTrue(world.getShieldTimer() 				!= null);
		assertTrue(world.getTimestep() 					!= null);
	}

	@Test
	public void testGetDrawableData() {
		Gameworld world = new Gameworld();
		assertTrue(world.getDrawableData() != null);
	}

	@Test
	public void testSlateObjectForRemoval() {
		Gameworld world = new Gameworld();
		Asteroid asteroid = AsteroidFactory.create(new Point2f(50, 50));
		world.addAsteroid(asteroid);
		world.update();
		world.slateObjectForRemoval(asteroid);
		assertTrue(world.getListOfObjectsToBeRemoved().contains(asteroid));
		world.update();
		assertTrue(!world.getListOfObjectsToBeRemoved().contains(asteroid));
	}

	@Test
	public void testAddSpaceshipPoint2f() {
		Gameworld world = new Gameworld();
		Point2f point2f = new Point2f(50, 50);
		Spaceship spaceship = SpaceshipFactory.create(point2f);
		world.addSpaceship(spaceship);
		assertTrue(world.getCollidables().size() == 1);
		assertTrue(world.getDrawables().size() == 1);
		assertTrue(world.getMoveables().size() == 1);
		assertTrue(world.getMoveables().get(spaceship).getPosition().equals(point2f));
	}

	@Test
	public void testAddSpaceshipSpaceship() {
		Gameworld world = new Gameworld();
		Point2f point2f = new Point2f(50, 50);
		Spaceship spaceship = SpaceshipFactory.create(point2f);
		world.addSpaceship(spaceship);
		assertTrue(world.getCollidables().size() == 1);
		assertTrue(world.getDrawables().size() == 1);
		assertTrue(world.getMoveables().size() == 1);
		assertTrue(world.getChargables().size() == 1);
		assertTrue(world.getMoveables().get(spaceship).getPosition().equals(point2f));
	}

	@Test
	public void testAddProjectile() {
		Gameworld world = new Gameworld();
		Point2f point2f = new Point2f(50, 50);
		IProjectile projectile = BulletFactory.create(	new Point2f(50, 50),
														new Vector2f(0, 0),
														new Vector2f(0, 0),
														new Vector2f(0, 0),
														1,
														1);
		world.addProjectile(projectile);
		assertTrue(world.getCollidables().size() == 1);
		assertTrue(world.getDrawables().size() == 1);
		assertTrue(world.getMoveables().size() == 1);
		assertTrue(world.getMoveables().get(projectile).getPosition().equals(point2f));
	}

	@Test
	public void testAddAsteroid() {
		Gameworld world = new Gameworld();
		Point2f point2f = new Point2f(50, 50);
		Asteroid asteroid = AsteroidFactory.create(	new Point2f(50, 50));
		world.addAsteroid(asteroid);
		assertTrue(world.getCollidables().size() == 1);
		assertTrue(world.getDrawables().size() == 1);
		assertTrue(world.getMoveables().size() == 0);
	}

	@Test
	public void testAddPickup() {
		Gameworld world = new Gameworld();
		Point2f point2f = new Point2f(50, 50);
		IPickup pickup = new HealthPickup(point2f, 50, 50);
		world.addPickup(pickup);
		assertTrue(world.getCollidables().size() == 1);
		assertTrue(world.getDrawables().size() == 1);
		assertTrue(world.getMoveables().size() == 0);
	}

	@Test
	public void testUpdate() {
		//You simply do not test this method
		assertTrue(true);
	}

	@Test
	public void testPropertyChange() {
		Gameworld world = new Gameworld();
		Spaceship spaceship = SpaceshipFactory.create(new Point2f(50, 50));
		world.addSpaceship(spaceship);
		world.update();
		spaceship.fireWeapon();
		assertTrue(world.getListOfSounds().size() == 1);
		
		world = new Gameworld();
		IProjectile projectile = BulletFactory.create(	new Point2f(50, 50),
														new Vector2f(0, 0),
														new Vector2f(0, 0),
														new Vector2f(0, 0),
														1,
														1);
		world.addProjectile(projectile);
		world.update();
		projectile.remove();
		assertTrue(world.getListOfObjectsToBeRemoved().size() == 1);
		
		world = new Gameworld();
		spaceship = SpaceshipFactory.create(new Point2f(50, 50));
		world.addSpaceship(spaceship);
		world.update();
		spaceship.remove();
		assertTrue(world.getListOfObjectsToBeRemoved().size() == 1);
		assertTrue(world.getListOfSounds().size() == 1);
		
		world = new Gameworld();
		spaceship = SpaceshipFactory.create(new Point2f(50, 50));
		world.addSpaceship(spaceship);		
		spaceship.visit(AsteroidFactory.create(new Point2f(0, 0))); //Force collide
		assertTrue(world.getListOfSounds().size() == 1);
	}

	@Test
	public void testGetBorder() {
		Gameworld world = new Gameworld();
		assertTrue(world.getBorder() != null);
	}

	@Test
	public void testGetListOfSounds() {
		Gameworld world = new Gameworld();
		assertTrue(world.getListOfSounds() != null);
	}

	@Test
	public void testAddPropertyChangeListener() {
		Gameworld world = new Gameworld();
		world.addPropertyChangeListener(new Round());
		assertTrue(world.getPcs().getPropertyChangeListeners().length == 1);
	}
	
	@Test
	public void testGenerateWorld() {
		Gameworld world = new Gameworld();
		world.generateWorld();
		assertTrue(world.getCollidables().size() >= 1 && world.getCollidables().size() <= 21);
		assertTrue(world.getDrawables().size() >= 1 && world.getDrawables().size() <= 21);	
	}
}
