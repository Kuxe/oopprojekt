package com.whathappensingandalf.howdoiflythisthing.factorys;

import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */
public class SpaceshipFactory {
    public static Spaceship create(Point2f point, Vector2f vector){
    	return new Spaceship(point, vector);
    }
}
