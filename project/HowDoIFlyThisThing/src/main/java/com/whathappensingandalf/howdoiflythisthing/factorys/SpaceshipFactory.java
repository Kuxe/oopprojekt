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
    	return create(new Point2f(point), new Vector2f(vector), 50, 50);
    }
    public static Spaceship create(Point2f point, Vector2f vector, int width, int height){
    	return new Spaceship(new Point2f(point), new Vector2f(vector), width, height);
    }
    public static Spaceship create(Point2f position){
		return create(position,new Vector2f((float)Math.random(), (float)Math.random()));
    }
}
