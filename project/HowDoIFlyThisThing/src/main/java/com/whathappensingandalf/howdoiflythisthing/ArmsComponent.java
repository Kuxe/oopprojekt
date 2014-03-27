package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.factorys.ProjectileFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.*;
/**
 *
 * @author Joakim Thorén
 */
public class ArmsComponent {
    
	private Projectile equipedProjectile;
	private Point2f point;
	private Vector2f speed;
	private Vector2f acceleration;
	private Vector2f direction;
	
	//baseSpeed is the inherent speed of the bullet which is fired, while speed is the speed of the spaceship
	//We know from physics that the speed of the bullet will be equal to the speed gained from weapon firepower
	//that is, baseSpeed, plus speed from space ship.
	private Vector2f baseSpeed;
	private final int baseSpeedMagnitude = 3;
	
	//Vector containing sum of baseSpeed and speed
	Vector2f sumSpeed;
	
	public ArmsComponent(Point2f point, Vector2f speed, Vector2f acceleration, Vector2f direction) {
		this.point 			= point;
		this.speed 			= speed;
		this.acceleration 	= acceleration;
		this.direction		= direction;
		
		baseSpeed = new Vector2f(0, 0);
		sumSpeed = new Vector2f(0, 0);
	}
	
	/**
	 * 
	 * @return Projectile that the weapon fires
	 */
	public Projectile fire(){
		//Set baseSpeed angle to the same as speed angle
		baseSpeed.set(speed);
		baseSpeed.normalize();
		//and scale it by the fixed magnitude
		baseSpeed.scale(baseSpeedMagnitude);
		
		//Reset sumSpeed
		sumSpeed.set(0, 0);
		sumSpeed.add(speed);
		sumSpeed.add(baseSpeed);
		return ProjectileFactory.create(point, sumSpeed, acceleration, direction);
	}
}
