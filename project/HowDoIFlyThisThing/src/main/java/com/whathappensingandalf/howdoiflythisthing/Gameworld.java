package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	private HashMap<Object, IMovable> moveables;
	
	/**
	 * HashMap which is unlocked by any gameworld object, ie spaceship.
	 * It retrieves a list of hashMaps, ie movables and removes the references
	 * to this spaceship from all lists upon spaceship destruction
	 */
	private HashMap<Object, List<HashMap<Object, ? extends IListable>>> removalHashMap;
	
	
	public Gameworld(){
		moveables = 		new HashMap();		
		removalHashMap = 	new HashMap();
	}
	
	/**
	 * Method for removing an object from all HashMaps which it has been placed into
	 * Should ALWAYS be called whenever a gameworld object is destroyed or killed
	 * Elsewise these hashmaps will keep references to the object and nasty
	 * nullptrexceptions will instantly appear
	 * @param key
	 */
	public void removeObjectFromHashMaps(Object key) {
		//Loop through the List containing the HashMaps in which an gameworld object (ie spaceship) is stored
		//That is, each hashMap in the list is one of the hashMaps in which a reference to the key object is stored
		//These hashMaps shall remove this reference upon object destruction, which is done bellow
		for(HashMap<Object, ? extends IListable> hashMap : removalHashMap.get(key)) {
			hashMap.remove(key);
		}
		//Also remove the list containing the hashMaps from removalHashMap
		removalHashMap.remove(key);
	}

	/**
	 * Adds a spaceship to the world
	 */
	public void addSpaceship(Point2f point){
		//Temporary hardcoded direction set to 1, 1
		Spaceship ss = SpaceshipFactory.create(point, new Vector2f(1, 1));
				
		//Add spaceship to hashmap moveable, 
		moveables.put(ss, ss);
		
		//List of hashmaps which Spaceship is added to
		List<HashMap<Object, ? extends IListable>> listOfHashMaps = new LinkedList();
		listOfHashMaps.add(moveables);
		
		//Finally make sure that the removalHashMap has the list of hashMaps in which this spaceship exist in.
		removalHashMap.put(ss, listOfHashMaps);
	}
	
	/**
	 * Adds param projectile to the world
	 * @param projectile
	 */
	public void addProjectile(Projectile projectile) {
		//Only add projectile if it exists
		if(projectile != null) {
			moveables.put(projectile, projectile);
		}
	}
	
	/**
	 * Moves all objects that implements IMoveable
	 */
	private void movableUpdate(){
		for(IMovable ma : moveables.values()){
			ma.move();
			System.out.println(ma.getPosition());
		}
	}
	
	/**
	 * Loop through all lists of interfaces and call their methods
	 */
	public void update(){
		movableUpdate();
	}

	/**
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
}
