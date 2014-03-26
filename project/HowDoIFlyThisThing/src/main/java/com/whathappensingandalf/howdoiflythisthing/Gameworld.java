package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashMap;


/**
 * 
 * @author Kuxe
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

public class Gameworld {
	
	/**
	 * HashMaps containing all interfaces which objects in gameworld implements
	 * These are later on looped through and logic is computed centrally from this class, Gameworld.
	 */
	private HashMap<IMoveable, Object> moveables;
	private HashMap<IArmable, Object> armables;
	
	public Gameworld(){
		
	}	
}
