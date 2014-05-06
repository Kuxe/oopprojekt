/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing.factorys;

import com.whathappensingandalf.howdoiflythisthing.Bullet;
import com.whathappensingandalf.howdoiflythisthing.IGameObject;
import com.whathappensingandalf.howdoiflythisthing.IProjectile;
import com.whathappensingandalf.howdoiflythisthing.Missile;
import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin
 */
public class IProjectileFactory {

	public static IProjectile create(Point2f position, Vector2f velocity, Vector2f acceleration, Vector2f direction, String type) {
		if(type.equals(IGameObject.type.MISSILE.toString())){
			return new Missile(new Point2f(position), new Vector2f(velocity.x/2, velocity.y/2), new Vector2f(direction.x*100, direction.y*100), new Vector2f(direction), 5, 10);
		}else{
			return new Bullet(new Point2f(position), new Vector2f(velocity), new Vector2f(acceleration), new Vector2f(direction), 3, 3);
		}
	}
	
}
