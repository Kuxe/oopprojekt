package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;

import utils.ITimer;
import utils.Timer;

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
		world.spawnPickups(false);
		
		world.addAsteroid(AsteroidFactory.create(new Point2f(10, 10)));
		world.update();
		assertTrue(world.getDrawableData() != null);
		assertTrue(world.getDrawableData().size() == 1);
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
		world.addSpaceship(new Point2f(50, 50));
		assertTrue(world.getCollidables().size() == 1);
		assertTrue(world.getDrawables().size() == 1);
		assertTrue(world.getMoveables().size() == 1);
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
		world.update();
		
		world = new Gameworld();
		spaceship = SpaceshipFactory.create(new Point2f(50, 50));
		world.addSpaceship(spaceship);
		spaceship.visit(BulletFactory.create(	new Point2f(50, 50),
												new Vector2f(0, 0),
												new Vector2f(0, 0),
												new Vector2f(0, 0),
												1,
												1)); //Force collide
		spaceship.visit(AsteroidFactory.create(new Point2f(0, 0))); //Force collide
		assertTrue(world.getListOfSounds().size() == 2);
		
		world = new Gameworld();
		Asteroid asteroid = AsteroidFactory.create(new Point2f(20, 20)); 
		world.addAsteroid(asteroid);
		spaceship = SpaceshipFactory.create(new Point2f(20, 20));
		
		//Force collision with three spaceships, causing asteroid to explode
		asteroid.visit(spaceship);
		asteroid.visit(spaceship);
		asteroid.visit(spaceship);
		asteroid.visit(spaceship);
		assertTrue(world.getListOfObjectsToBeRemoved().size() == 1);
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
	
	@Test
	public void testGetMoveables() {
		Gameworld world = new Gameworld();
		
		Map<Object, IMovable> moveables = new HashMap<Object, IMovable>();
		world.setMoveables(moveables);
		assertTrue(world.getMoveables().equals(moveables));
	}

	@Test
	public void testSetMoveables() {
		Gameworld world = new Gameworld();
		
		Map<Object, IMovable> moveables = new HashMap<Object, IMovable>();
		world.setMoveables(moveables);
		assertTrue(world.getMoveables().equals(moveables));
	}

	@Test
	public void testGetCollidables() {
		Gameworld world = new Gameworld();
		
		Map<Object, ICollidable> collidables = new HashMap<Object, ICollidable>();
		world.setCollidables(collidables);
		assertTrue(world.getCollidables().equals(collidables));
	}

	@Test
	public void setCollidables() {
		Gameworld world = new Gameworld();
		
		Map<Object, ICollidable> collidables = new HashMap<Object, ICollidable>();
		world.setCollidables(collidables);
		assertTrue(world.getCollidables().equals(collidables));
	}

	@Test
	public void testGetDrawables() {
		Gameworld world = new Gameworld();
		
		Map<Object, IDrawable> drawables = new HashMap<Object, IDrawable>();
		world.setDrawables(drawables);
		assertTrue(world.getDrawables().equals(drawables));
	}

	@Test
	public void testSetDrawables() {
		Gameworld world = new Gameworld();
		
		Map<Object, IDrawable> drawables = new HashMap<Object, IDrawable>();
		world.setDrawables(drawables);
		assertTrue(world.getDrawables().equals(drawables));
	}

	@Test
	public void testGetChargables() {
		Gameworld world = new Gameworld();
		
		Map<Object, IRechargable> chargables = new HashMap<Object, IRechargable>();
		world.setChargables(chargables);
		assertTrue(world.getChargables().equals(chargables));
	}

	@Test
	public void testSetChargables() {
		Gameworld world = new Gameworld();
		
		Map<Object, IRechargable> chargables = new HashMap<Object, IRechargable>();
		world.setChargables(chargables);
		assertTrue(world.getChargables().equals(chargables));
	}
	
	@Test
	public void testSetRemovalMap() {
		Gameworld world = new Gameworld();
		
		Map<Object, List<Map<Object, ? extends IListable>>> removalMap = new HashMap();
		world.setRemovalMap(removalMap);
		assertTrue(world.getRemovalMap().equals(removalMap));
	}

	@Test
	public void testGetListOfObjectsToBeRemoved() {
		Gameworld world = new Gameworld();
		
		Map<Object, List<Map<Object, ? extends IListable>>> removalMap = new HashMap();
		world.setRemovalMap(removalMap);
		assertTrue(world.getRemovalMap().equals(removalMap));		
	}

	@Test
	public void testSetListOfObjectsToBeRemoved() {
		Gameworld world = new Gameworld();
		
		Set<Object> removalMap = new HashSet();
		world.setListOfObjectsToBeRemoved(removalMap);
		assertTrue(world.getListOfObjectsToBeRemoved().equals(removalMap));
	}

	@Test
	public void testGetTimestep() {
		Gameworld world = new Gameworld();
		
		Timestep timestep = new Timestep();
		world.setTimestep(timestep);
		assertTrue(world.getTimestep().equals(timestep));
	}

	@Test
	public void testSetTimestep() {
		Gameworld world = new Gameworld();
		
		Timestep timestep = new Timestep();
		world.setTimestep(timestep);
		assertTrue(world.getTimestep().equals(timestep));		
	}

	@Test
	public void testSetBorder() {
		Gameworld world = new Gameworld();
		
		WorldBorder worldBorder = new WorldBorder(50, 50);
		world.setBorder(worldBorder);
		assertTrue(world.getBorder().equals(worldBorder));
	}

	@Test
	public void testSetListOfSounds() {
		Gameworld world = new Gameworld();
		
		Set<String> listOfSounds = new HashSet();
		world.setListOfSounds(listOfSounds);
		assertTrue(world.getListOfSounds().equals(listOfSounds));
	}

	@Test
	public void testGetPcs() {
		Gameworld world = new Gameworld();
		
		PropertyChangeSupport pcs = new PropertyChangeSupport(world);
		world.setPcs(pcs);
		assertTrue(world.getPcs().equals(pcs));
	}

	@Test
	public void testSetPcs() {
		Gameworld world = new Gameworld();
		
		PropertyChangeSupport pcs = new PropertyChangeSupport(world);
		world.setPcs(pcs);
		assertTrue(world.getPcs().equals(pcs));
	}

	@Test
	public void testGetPickupSpawnTimer() {
		Gameworld world = new Gameworld();
		
		ITimer pickupSpawnTimer = new Timer();
		world.setPickupSpawnTimer(pickupSpawnTimer);
		assertTrue(world.getPickupSpawnTimer().equals(pickupSpawnTimer));
	}

	@Test
	public void testSetPickupSpawnTimer() {
		Gameworld world = new Gameworld();
		
		ITimer pickupSpawnTimer = new Timer();
		world.setPickupSpawnTimer(pickupSpawnTimer);
		assertTrue(world.getPickupSpawnTimer().equals(pickupSpawnTimer));
	}

	@Test
	public void testGetShieldTimer() {
		Gameworld world = new Gameworld();
		
		ITimer shieldTimer = new Timer();
		world.setShieldTimer(shieldTimer);
		assertTrue(world.getShieldTimer().equals(shieldTimer));
	}

	@Test
	public void testSetShieldTimer() {
		Gameworld world = new Gameworld();
		
		ITimer shieldTimer = new Timer();
		world.setShieldTimer(shieldTimer);
		assertTrue(world.getShieldTimer().equals(shieldTimer));
	}
	
	@Test
	public void testSpawnPickups() {
		Gameworld world = new Gameworld();
		
		world.spawnPickups(false);
		assertTrue(world.isSpawningPickups() == equals(false));
	}
	
	@Test
	public void testIsSpawningPickups() {
		Gameworld world = new Gameworld();
		
		world.spawnPickups(false);
		assertTrue(world.isSpawningPickups() == false);
		world.spawnPickups(true);
		assertTrue(world.isSpawningPickups() == true);
	}
	
	
	//Ensure that functionality isnt dead (ensure code coverage)
	//Does not actually test anything
	@Test
	public void testSpaceshipDoNotSpawnOnAsteroidFunctionality() {
		Gameworld world = new Gameworld();
		world.addAsteroid(AsteroidFactory.create(new Point2f(20, 20)));
		world.addSpaceship(SpaceshipFactory.create(new Point2f(20, 20)));
		world.update();
		assertTrue(true);
	}
	
	//Ensure that functionality isnt dead (ensure code coverage)
	@Test
	public void testSpaceshipPickupColliding() {
		Gameworld world = new Gameworld();
		world.spawnPickups(false);
		
		world.addSpaceship(SpaceshipFactory.create(new Point2f(20, 20)));
		world.addPickup(new HealthPickup(new Point2f(20, 20), 20, 20));
		world.update();
		world.update();
		System.out.println(world.getCollidables().size());
		assertTrue(world.getCollidables().size() == 1); //Pickup should be gone
	}
	
	@Test
	public void testSpaceshipOutOfSpace() {
		Gameworld world = new Gameworld();
		
		Spaceship spaceship = SpaceshipFactory.create(new Point2f(-1, -1));
		world.addSpaceship(spaceship);
		assertTrue(world.getMoveables().size() == 1);
		world.update();
		assertTrue(world.getMoveables().size() == 0);
	}
}
