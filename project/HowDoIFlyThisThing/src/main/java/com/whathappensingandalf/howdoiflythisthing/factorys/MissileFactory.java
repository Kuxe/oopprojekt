package com.whathappensingandalf.howdoiflythisthing.factorys;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.Missile;

public class MissileFactory {
	public static Missile create(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		return new Missile(new Point2f(position), new Vector2f(velocity), new Vector2f(acceleration), new Vector2f(direction), width, height);
	}
	
	public static Missile create(Missile missile){
		return create(missile.getPosition(), missile.getVelocity(), missile.getAcceleration(), missile.getDirection(), missile.getWidth(), missile.getHeight());
	}
}
