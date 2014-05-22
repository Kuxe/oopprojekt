package com.whathappensingandalf.howdoiflythisthing;

import com.whathappensingandalf.howdoiflythisthing.factorys.AsteroidFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import network.AsteroidDieNetworkPacket;
import network.ExplosionNetworkPacket;
import network.SparkleNetworkPacket;
import services.SoundEffects;
import utils.ITimer;
import utils.Timer;

import com.whathappensingandalf.howdoiflythisthing.factorys.CookieCrackerFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.MissileFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;


/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 *
 *	Gameworld is responsible for doing computations within the gameworld.
 *	Gameworld has and maintains a list of interfaces which game world objects (eg. spaceship) implements
 *	These interfaces should provide methods for accesing data required for logic.
 *	The logic is calculated in this class; all spaceships and asteroids, and so forth, rely on the same piece of code which
 *	moves an object.
 *
 * 	This is somewhat similar to Entity-component-system design. The purpose is to eliminate duplicate code, for example
 *  moving an asteroid and moving a spaceship is exactly the same piece of code - instead of duplicating the code.
 */

public class Gameworld implements PropertyChangeListener{

	public static enum Message{
		EXPLOSION,
		SPARKLE,
		ASTEROID_EXP
	}
	
	/**
	 * HashMaps containing all interfaces which objects in gameworld implements
	 * These are later on looped through and logic is computed centrally from this class, Gameworld.
	 */
	private Map<Object, IMovable> moveables;
	private Map<Object, ICollidable> collidables;
	private Map<Object, IDrawable> drawables;
	private Map<Object, IRechargable> chargables;
	
	/**
	 * HashMap which is unlocked by any gameworld object, ie spaceship.
	 * It retrieves a list of hashMaps, ie movables and removes the references
	 * to this spaceship from all lists upon spaceship destruction
	 */
	private Map<Object, List<Map<Object, ? extends IListable>>> removalMap;
	private Set<Object> listOfObjectsToBeRemoved;
	private Timestep timestep;
	private WorldBorder worldBorder;
	private Set<String> listOfSounds;
	private PropertyChangeSupport pcs;
	private ITimer pickupSpawnTimer;
	private ITimer shieldTimer;
	
	public Gameworld(){
		moveables = 					new HashMap();
		collidables =					new HashMap();
		removalMap = 					new HashMap();
		listOfObjectsToBeRemoved = 		new HashSet();
		drawables =						new HashMap();
		listOfSounds = 					new HashSet();
		chargables =					new HashMap();
		
		worldBorder=new WorldBorder(1000, 1000);
		timestep = new Timestep();
		pcs = new PropertyChangeSupport(this);
		pickupSpawnTimer = new Timer(10000);
		shieldTimer=new Timer(10000);
	}
	
	public void generateWorld(){
		Random random = new Random();
		int numberOfAsteroids = random.nextInt(20)+1;
		for(int i = 0; i<numberOfAsteroids; i++){
			Point2f position = new Point2f((float)(Math.random())*this.worldBorder.getWorldWidth(),
					(float)(Math.random())*this.worldBorder.getWorldHeight());
			Asteroid ast = AsteroidFactory.create(position);
			this.addAsteroid(ast);
		}
	}
	
	public Set<DrawableData> getDrawableData() {
		HashSet<DrawableData> set = new HashSet();
		for(IDrawable drawable : drawables.values()) {
			set.addAll(drawable.getCollectionDrawables());
		}
		return set;
	}
	
	/**
	 * Adds object to list of slated removals. This list is iterated through 
	 * last of all updates in gameworld.update(). This is to prevent removal
	 * of references during updates, saving removals until later
	 * @param object
	 */
	public void slateObjectForRemoval(Object object) {
		if(object != null) {
			listOfObjectsToBeRemoved.add(object);
		}
	}
	/**
	 * Method for removing an object from all HashMaps which it has been placed into
	 * Should ALWAYS be called whenever a gameworld object is destroyed or killed
	 * Elsewise these hashmaps will keep references to the object and nasty
	 * nullptrexceptions will instantly appear
	 * @param key
	 */
	private void removeObjectFromMaps(Object key) {
		//Loop through the List containing the HashMaps in which an gameworld object (ie spaceship) is stored
		//That is, each hashMap in the list is one of the hashMaps in which a reference to the key object is stored
		//These hashMaps shall remove this reference upon object destruction, which is done bellow
		for(Map<Object, ? extends IListable> map : removalMap.get(key)) {
				map.remove(key);
		}
		//Also remove the list containing the hashMaps from removalHashMap
		removalMap.remove(key);
	}
	
	/**
	 * Iterate through list of slated objects and remove them
	 */
	private void removalUpdate() {
		for(Object object : listOfObjectsToBeRemoved) {
			removeObjectFromMaps(object);
		}
		
		//Objects are now removed and only left removal is form the list iteself
		listOfObjectsToBeRemoved.clear();
	}

	/**
	 * Adds a spaceship to the world
	 */
	public void addSpaceship(Point2f point){
		//Temporary hardcoded direction set to 1, 1
		addSpaceship(SpaceshipFactory.create(point, new Vector2f(1, 1)));
	}
	private boolean isValidSpawn(ICollidable spawner){
		for(ICollidable coli : collidables.values()){
			if(spawner.collideDetection(coli)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds a spaceship to the world
	 */
	public void addSpaceship(Spaceship spaceship){
		//Make gameworld listen to spaceship
		spaceship.addPropertyChangeListener(this);
		//plases the spaceship at a possition that is free.
		while(!isValidSpawn(spaceship)){
			spaceship.setPosition(new Point2f ((float)(Math.random())*this.worldBorder.getWorldWidth(),
					(float)(Math.random())*this.worldBorder.getWorldHeight()));
		}
		
		//Add spaceship to hashmap moveable, 
		moveables.put(spaceship, spaceship);
		collidables.put(spaceship, spaceship);
		drawables.put(spaceship, spaceship);
		chargables.put(spaceship, spaceship);
		
		//List of hashmaps which Spaceship is added to
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(moveables);
		listOfHashMaps.add(collidables);
		listOfHashMaps.add(drawables);
		
		//Finally make sure that the removalHashMap has the list of hashMaps in which this spaceship exist in.
		removalMap.put(spaceship, listOfHashMaps);
	}

	/**
	 * Adds param projectile to the world
	 * @param projectile
	 */
	public void addProjectile(IProjectile projectile) {
		projectile.addPropertyChangeListener(this);
		moveables.put(projectile, projectile);
		collidables.put(projectile, projectile);
		drawables.put(projectile, projectile);
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(moveables);
		listOfHashMaps.add(collidables);
		listOfHashMaps.add(drawables);
		removalMap.put(projectile, listOfHashMaps);
	}
	
	public void addAsteroid(Asteroid asteroid) {
		asteroid.addPropertyChangeListener(this);
		collidables.put(asteroid, asteroid);
		drawables.put(asteroid, asteroid);
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(collidables);
		listOfHashMaps.add(drawables);
		removalMap.put(asteroid, listOfHashMaps);
	}
	
	public void addPickup(IPickup pickup){
		pickup.addPropertyChangeListener(this);
		
		collidables.put(pickup, pickup);
		drawables.put(pickup, pickup);
		
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(collidables);
		listOfHashMaps.add(drawables);
		
		removalMap.put(pickup, listOfHashMaps);
	}
	
	/**
	 * Moves all objects that implements IMoveable
	 */
	private void movableUpdate(){
		for(IMovable ma : moveables.values()){
			ma.move(timestep);
		}
	}
	
	/**
	 * Detects collisions and saves each collision for resolving later on
	 */
	private void collisionUpdate() {
		ICollidable[] array = collidables.values().toArray(new ICollidable[collidables.values().size()]);
		for(int first = 0; first < array.length; first++) {
			for(int second = first + 1; second < array.length; second++) {
				if(array[first].collideDetection(array[second])) {
					array[first].accept(array[second]);
					array[second].accept(array[first]);
				}
			}
		}
	}
	
	/**
	 * Loop through all lists of interfaces and call their methods
	 */
	public synchronized void update(){
		timestep.start();
		
		
		pickupSpawnUppdate();
		chargableUpdate();
		movableUpdate();
		worldBounderyCheck();
		collisionUpdate();
		removalUpdate(); //IT IS VERY IMPORTANT THAT THIS METHOD IS CALLED LAST!!!
		
		timestep.end();
		timestep.calculateDeltatime();
	}

	private void chargableUpdate() {
		
		if(shieldTimer.isTimerDone()){
			for(IRechargable chargable : chargables.values()){
				chargable.recharge();
			}
			shieldTimer.start();
		}
		
	}


	/**
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_FIRE.toString())) {
			addProjectile((IProjectile)evt.getOldValue());
			listOfSounds.add(SoundEffects.Sound.SPACESHIP_FIRE.toString());
		} else if(evt.getPropertyName().equals(IProjectile.Message.PROJECTILE_DIE.toString())) {
			listOfObjectsToBeRemoved.add(evt.getSource());
			SparkleNetworkPacket exnp=new SparkleNetworkPacket(((IProjectile)evt.getSource()).getPosition());
			pcs.firePropertyChange(Message.SPARKLE.toString(),exnp,false); //Forward event
		} else if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			this.slateObjectForRemoval(evt.getSource());
			listOfSounds.add(SoundEffects.Sound.SPACESHIP_DIE.toString());
			ExplosionNetworkPacket exnp=new ExplosionNetworkPacket(((Spaceship)evt.getSource()).getPosition());
			pcs.firePropertyChange(Message.EXPLOSION.toString(),exnp,false); //Forward event
		} else if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_HIT.toString())){
			listOfSounds.add(SoundEffects.Sound.SPACESHIP_HIT.toString());
		} else if(evt.getPropertyName().equals(IPickup.Message.PICKUP_DIE.toString())) {
			listOfObjectsToBeRemoved.add(evt.getSource());
			listOfSounds.add(SoundEffects.Sound.PICKUP_DIE.toString());
			pcs.firePropertyChange(evt); //Forward event
		} else if(evt.getPropertyName().equals(Asteroid.Message.ASTEROID_DIE.toString())) {
			slateObjectForRemoval(evt.getSource());
			AsteroidDieNetworkPacket asteroidDie = new AsteroidDieNetworkPacket(((Asteroid)evt.getSource()).getPosition());
			pcs.firePropertyChange(Message.ASTEROID_EXP.toString(),asteroidDie,false); //Forward event
		}
	}
	
	private void worldBounderyCheck(){
		for(IMovable item: moveables.values()){
			if(item.getPosition().x<0.0f || item.getPosition().x>worldBorder.getWorldWidth() || item.getPosition().y<0.0f || item.getPosition().y>worldBorder.getWorldHeight()){
//				slateObjectForRemoval(item);
				item.remove();
			}
		}
	}
	
	
	
	public Map<Object, IMovable> getMoveables() {
		return moveables;
	}

	public void setMoveables(Map<Object, IMovable> moveables) {
		this.moveables = moveables;
	}

	public Map<Object, ICollidable> getCollidables() {
		return collidables;
	}

	public void setCollidables(Map<Object, ICollidable> collidables) {
		this.collidables = collidables;
	}

	public Map<Object, IDrawable> getDrawables() {
		return drawables;
	}

	public void setDrawables(Map<Object, IDrawable> drawables) {
		this.drawables = drawables;
	}

	public Map<Object, IRechargable> getChargables() {
		return chargables;
	}

	public void setChargables(Map<Object, IRechargable> chargables) {
		this.chargables = chargables;
	}

	public Map<Object, List<Map<Object, ? extends IListable>>> getRemovalMap() {
		return removalMap;
	}

	public void setRemovalMap(
			Map<Object, List<Map<Object, ? extends IListable>>> removalMap) {
		this.removalMap = removalMap;
	}

	public Set<Object> getListOfObjectsToBeRemoved() {
		return listOfObjectsToBeRemoved;
	}

	public void setListOfObjectsToBeRemoved(Set<Object> listOfObjectsToBeRemoved) {
		this.listOfObjectsToBeRemoved = listOfObjectsToBeRemoved;
	}

	public Timestep getTimestep() {
		return timestep;
	}

	public void setTimestep(Timestep timestep) {
		this.timestep = timestep;
	}

	public WorldBorder getBorder() {
		return worldBorder;
	}

	public void setBorder(WorldBorder worldBorder) {
		this.worldBorder = worldBorder;
	}

	public Set<String> getListOfSounds() {
		return listOfSounds;
	}

	public void setListOfSounds(Set<String> listOfSounds) {
		this.listOfSounds = listOfSounds;
	}

	public PropertyChangeSupport getPcs() {
		return pcs;
	}

	public void setPcs(PropertyChangeSupport pcs) {
		this.pcs = pcs;
	}

	public ITimer getPickupSpawnTimer() {
		return pickupSpawnTimer;
	}

	public void setPickupSpawnTimer(ITimer pickupSpawnTimer) {
		this.pickupSpawnTimer = pickupSpawnTimer;
	}

	public ITimer getShieldTimer() {
		return shieldTimer;
	}

	public void setShieldTimer(ITimer shieldTimer) {
		this.shieldTimer = shieldTimer;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	private void pickupSpawnUppdate(){
		if(pickupSpawnTimer.isTimerDone()){
			pickupSpawnTimer.start();
			double spawnChance=Math.random();
			if(spawnChance<0.1){
				addPickup(
						new WeaponPickup(
								new Point2f(
										(int)(Math.random()*worldBorder.getWorldWidth()),
										(int)(Math.random()*worldBorder.getWorldHeight())),
									12,
									MissileFactory.create(
											new Point2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											0,
											0)));
			}else if(spawnChance<0.2){
				addPickup(
						new HealthPickup(
								new Point2f(
										(int)(Math.random()*worldBorder.getWorldWidth()),
										(int)(Math.random()*worldBorder.getWorldHeight())),
									12,
									5));
			} else if(spawnChance < 0.3) {
				addPickup(new WeaponPickup(new Point2f((int)(Math.random()*worldBorder.getWorldWidth()),
													(int)(Math.random()*worldBorder.getWorldHeight())),
													12, CookieCrackerFactory.create(new Point2f(),
																					new Vector2f(),
																					new Vector2f(),
																					new Vector2f(),
																					0,
																					0)
											)
				);
			}
		}
	}
	
	
}
