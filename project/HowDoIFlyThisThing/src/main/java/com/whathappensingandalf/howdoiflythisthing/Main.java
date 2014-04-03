package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Kuxe
 *
 *	Main-class with main-method
 */
public class Main {
	public static void main(String argc[]){
		Gameworld gameworld = new Gameworld();
		
		//Create two spaceships and make the first one shoot bullets on the other one
		Spaceship spaceship1 = new Spaceship(new Point2f(0, 0), new Vector2f(0, 0), 5, 5);
		Spaceship spaceship2 = new Spaceship(new Point2f(10, 10), new Vector2f(0, 0), 5, 5);
		gameworld.addSpaceship(spaceship1);
		gameworld.addSpaceship(spaceship2);
		spaceship1.fireWeapon();
		
		//Update gameworld 10 times.
		for(int i = 0; i < 5; i++){
			gameworld.update();
		}
	}
}
