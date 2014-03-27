package com.whathappensingandalf.howdoiflythisthing;

import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;


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

public class Gameworld implements PropertyChangeListener{
	
	/**
	 * HashMaps containing all interfaces which objects in gameworld implements
	 * These are later on looped through and logic is computed centrally from this class, Gameworld.
	 */
	private HashMap<Object, IMovable> moveables;
	private HashMap<Object, IArmable> armables;
	
	
	public Gameworld(){
		moveables = 	new HashMap();
		armables = 		new HashMap();
	}

	/**
	 * Adds a spaceship to the world
	 */
	public void addSpaceship(Point2f point){
		//temporary spaceship creating before Spaceship factory is available
		SpaceShip ss = SpaceshipFactory.create(point, new Vector2f(1, 1));
		
		//Add spaceship to hashmap moveable, 
		moveables.put(ss, ss);
		armables.put(ss, ss);
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
