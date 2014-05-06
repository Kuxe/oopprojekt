package com.whathappensingandalf.howdoiflythisthing.factorys;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.Missile;

public class MissileFactory {
	public static Missile create(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		return new Missile(position, velocity, acceleration, direction, width, height);
	}
}
