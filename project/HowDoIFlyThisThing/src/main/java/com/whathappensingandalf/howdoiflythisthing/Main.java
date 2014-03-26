package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Kuxe
 *
 *	Main-class with main-method
 */
public class Main {
	public static void main(String argc[]){
		Gameworld gameworld = new Gameworld();
		
		//Update gameworld 10 times.
		for(int i = 0; i < 10; i++){
			gameworld.update();
		}
	}
}
