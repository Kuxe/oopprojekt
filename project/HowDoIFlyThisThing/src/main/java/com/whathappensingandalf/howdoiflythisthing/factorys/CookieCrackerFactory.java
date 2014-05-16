package com.whathappensingandalf.howdoiflythisthing.factorys;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.CookieCracker;
import com.whathappensingandalf.howdoiflythisthing.Missile;

public class CookieCrackerFactory {
	public static CookieCracker create(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		return new CookieCracker(new Point2f(position), new Vector2f(velocity), new Vector2f(acceleration), new Vector2f(direction), width, height);
	}
	
	public static CookieCracker create(CookieCracker cookieCracker){
		return create(cookieCracker.getPosition(), cookieCracker.getVelocity(), cookieCracker.getAcceleration(), cookieCracker.getDirection(), cookieCracker.getWidth(), cookieCracker.getHeight());
	}
}
