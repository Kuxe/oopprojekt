package com.whathappensingandalf.howdoiflythisthing.factorys;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.Bullet;


/**
 * 
 * @author Mathias
 *
 */
public class BulletFactory {
	static int count = 0;
	
	public static Bullet create(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		return new Bullet(new Point2f(position), new Vector2f(velocity), new Vector2f(acceleration), new Vector2f(direction), width, height);
		
	}
	public static Bullet create(Bullet p){
		return create(p.getPosition(), p.getVelocity(), p.getAcceleration(), p.getDirection(), p.getWidth(), p.getHeight());		
	}
}
