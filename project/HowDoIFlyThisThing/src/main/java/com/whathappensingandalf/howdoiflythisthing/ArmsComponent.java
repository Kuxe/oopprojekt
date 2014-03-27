package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;
import com.whathappensingandalf.howdoiflythisthing.factorys.ProjectileFactory;

/**
 *
 * @author Joakim Thorén
 */
public class ArmsComponent {
    
	private Projectile equipedProjectile;
	private Point2f point;
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	
	//baseSpeed is the inherent speed of the bullet which is fired, while speed is the speed of the spaceship
	//We know from physics that the speed of the bullet will be equal to the speed gained from weapon firepower
	//that is, baseSpeed, plus speed from space ship.
	private Vector2f baseVelocity;
	private final int baseVelocityMagnitude = 3;
	
	//Vector containing sum of baseSpeed and speed
	private Vector2f sumVelocity;
	
	public ArmsComponent(Point2f point, Vector2f velocity, Vector2f acceleration, Vector2f direction) {
		this.point 			= point;
		this.velocity 		= velocity;
		this.acceleration 	= acceleration;
		this.direction		= direction;
		
		baseVelocity = new Vector2f(0, 0);
		sumVelocity = new Vector2f(0, 0);
	}
	
	/**
	 * 
	 * @return Projectile that the weapon fires
	 */
	public Projectile fire(){
		//Set baseSpeed angle to the same as speed angle
		baseVelocity.set(velocity);
		baseVelocity.normalize();
		//and scale it by the fixed magnitude
		baseVelocity.scale(baseVelocityMagnitude);
		
		//Reset sumSpeed
		sumVelocity.set(0, 0);
		sumVelocity.add(velocity);
		sumVelocity.add(baseVelocity);
		return ProjectileFactory.create(point, sumVelocity, acceleration, direction);
	}
}
