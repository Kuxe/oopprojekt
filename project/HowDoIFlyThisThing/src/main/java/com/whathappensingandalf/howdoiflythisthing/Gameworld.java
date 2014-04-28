package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

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
	
	/**
	 * HashMaps containing all interfaces which objects in gameworld implements
	 * These are later on looped through and logic is computed centrally from this class, Gameworld.
	 */
	private Map<Object, IMovable> moveables;
	private Map<Object, ICollidable> collidables;
	private Map<Object, IDrawable> drawables;
	
	/**
	 * HashMap which is unlocked by any gameworld object, ie spaceship.
	 * It retrieves a list of hashMaps, ie movables and removes the references
	 * to this spaceship from all lists upon spaceship destruction
	 */
	private Map<Object, List<Map<Object, ? extends IListable>>> removalMap;
	
	private Set<Object> listOfObjectsToBeRemoved;
	
	private Timestep timestep;
	
	private WorldBorder worldBorder;
	
	public Gameworld(){
		moveables = 					new HashMap();
		collidables =					new HashMap();
		removalMap = 					new HashMap();
		listOfObjectsToBeRemoved = 		new HashSet();
		drawables =						new HashMap();
		
		worldBorder=new WorldBorder(540,1080);
		
		timestep = new Timestep();
	}
	
	public Map<Object, IDrawable> getIDrawables() {
		HashMap<Object, IDrawable> map = new HashMap();
		for(Object key : drawables.keySet()) {
			map.put(key, drawables.get(key).clone());
		}
		return map;
	}
	
	/**
	 * Adds object to list of slated removals. This list is iterated through 
	 * last of all updates in gameworld.update(). This is to prevent removal
	 * of references during updates, saving removals until later
	 * @param object
	 */
	public void slateObjectForRemoval(Object object) {
		listOfObjectsToBeRemoved.add(object);
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
	
	/**
	 * Adds a spaceship to the world
	 */
	public void addSpaceship(Spaceship spaceship){
		//Make gameworld listen to spaceship
		spaceship.addPropertyChangeListener(this);
		
		//Add spaceship to hashmap moveable, 
		moveables.put(spaceship, spaceship);
		collidables.put(spaceship, spaceship);
		drawables.put(spaceship, spaceship);
		
		//List of hashmaps which Spaceship is added to
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(moveables);
		listOfHashMaps.add(collidables);
		listOfHashMaps.add(drawables);
		
		//Finally make sure that the removalHashMap has the list of hashMaps in which this spaceship exist in.
		removalMap.put(spaceship, listOfHashMaps);
	}
	
	private void addObjectToHashMaps(List<HashMap<Object, Object>> list, Object object) {
		for(HashMap<Object, Object> item : list) {
			item.put(object, object);
		}
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		for(Map<Object, ? extends IListable> listable : listOfHashMaps) {
			listOfHashMaps.add(listable);
		}
		removalMap.put(object, listOfHashMaps);
	}

	/**
	 * Adds param projectile to the world
	 * @param projectile
	 */
	public void addProjectile(Projectile projectile) {
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
		collidables.put(asteroid, asteroid);
		drawables.put(asteroid, asteroid);
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(collidables);
		listOfHashMaps.add(drawables);
		removalMap.put(asteroid, listOfHashMaps);
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
	public void update(){
		timestep.start();
		
		movableUpdate();
		worldBounderyCheck();
		collisionUpdate();
		removalUpdate(); //IT IS VERY IMPORTANT THAT THIS METHOD IS CALLED LAST!!!
		
		timestep.end();
		timestep.calculateDeltatime();
	}

	/**
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_FIRE.toString())) {
			addProjectile((Projectile)evt.getOldValue());
		} else if(evt.getPropertyName().equals(Projectile.Message.PROJECTILE_DIE.toString())) {
			listOfObjectsToBeRemoved.add(evt.getSource());
		} else if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			listOfObjectsToBeRemoved.add(evt.getSource());
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
}
