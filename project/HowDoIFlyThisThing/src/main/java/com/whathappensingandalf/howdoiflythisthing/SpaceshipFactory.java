package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class SpaceshipFactory {
    public static SpaceShip create(Point2f point, Vector2f vector){
    	return new SpaceShip(point, vector);
    }
}
