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
	public static Projectile create(Point2f position, Vector2f speed, Vector2f acceleration, Vector2f direction){
		return new Projectile(position, speed, acceleration, direction);
		
	}
}
