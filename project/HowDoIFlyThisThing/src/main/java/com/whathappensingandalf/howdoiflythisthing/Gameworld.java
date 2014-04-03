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
	
	/**
	 * HashMap which is unlocked by any gameworld object, ie spaceship.
	 * It retrieves a list of hashMaps, ie movables and removes the references
	 * to this spaceship from all lists upon spaceship destruction
	 */
	private Map<Object, List<Map<Object, ? extends IListable>>> removalMap;
	
	private Set<Object> listOfObjectsToBeRemoved;
	
	public Gameworld(){
		moveables = 					new HashMap();
		collidables =					new HashMap();
		removalMap = 					new HashMap();
		listOfObjectsToBeRemoved = 		new HashSet();
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
		
		//List of hashmaps which Spaceship is added to
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(moveables);
		listOfHashMaps.add(collidables);
		
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
		moveables.put(projectile, projectile);
		collidables.put(projectile, projectile);
		List<Map<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(moveables);
		listOfHashMaps.add(collidables);
		removalMap.put(projectile, listOfHashMaps);
	}
	
	/**
	 * Moves all objects that implements IMoveable
	 */
	private void movableUpdate(){
		int i = 1;
		for(IMovable ma : moveables.values()){
			ma.move();
			System.out.println(i + ": " + ma.getPosition());
			i+=1;
		}
	}
	
	/**
	 * Detects collisions and saves each collision for resolving later on
	 */
	private void collisionUpdate() {
		//TODO: This implementation is clearly broken as it does not test collision
		//on every permutation of values in collidables. How to test permutation of all objects
		for(ICollidable collidable1 : collidables.values()) {
			for(ICollidable collidable2 : collidables.values()) {
				if(collidable1.collideDetection(collidable2)) {
					collidable1.accept(collidable2);
					collidable2.accept(collidable1);
				}
			}
		}
	}
	
	/**
	 * Loop through all lists of interfaces and call their methods
	 */
	public void update(){
		movableUpdate();
		collisionUpdate();
		removalUpdate(); //IT IS VERY IMPORTANT THAT THIS METHOD IS CALLED LAST!!!
	}

	/**
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_FIRE.toString())) {
			addProjectile((Projectile)evt.getOldValue());
			System.out.println("here");
		} else if(evt.getPropertyName().equals(Projectile.Message.PROJECTILE_DIE.toString())) {
			
		}
		
	}
}
