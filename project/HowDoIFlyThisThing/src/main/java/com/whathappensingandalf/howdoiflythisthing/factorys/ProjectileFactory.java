package com.whathappensingandalf.howdoiflythisthing.factorys;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.Projectile;


/**
 * 
 * @author Mathias
 *
 */
public class ProjectileFactory {
	static int count = 0;
	
	public static Projectile create(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, int width, int height){
		return new Projectile(new Point2f(position), new Vector2f(velocity), new Vector2f(acceleration), new Vector2f(direction), width, height);
		
	}
	public static Projectile create(Projectile p){
		System.out.println(count);
		count += 1;
		return create(p.getPosition(), p.getVelocity(), p.getAcceleration(), p.getDirection(), p.getWidth(), p.getHeight());		
	}
}
