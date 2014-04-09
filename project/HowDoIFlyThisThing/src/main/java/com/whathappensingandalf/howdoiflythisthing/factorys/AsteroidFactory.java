package com.whathappensingandalf.howdoiflythisthing.factorys;

import javax.vecmath.Point2f;

import com.whathappensingandalf.howdoiflythisthing.Asteroid;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 */
public class AsteroidFactory {
	/**
	 * 
	 * @param position
	 * @param width
	 * @param height
	 * @return an instance of asteroid
	 */
	public static Asteroid create(Point2f position, int width, int height) {
		return new Asteroid(new Point2f(position), width, height);
	}
	
	/**
	 * 
	 * @param position
	 * @return an instance of asteroid with default width and height 150x150
	 */
	public static Asteroid create(Point2f position) {
		return new Asteroid(new Point2f(position), 150, 150);
	}
}
