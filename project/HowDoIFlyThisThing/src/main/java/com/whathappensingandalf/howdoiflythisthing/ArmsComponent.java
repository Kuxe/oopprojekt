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
	
	//vector from armsComponent owner point to weapon pipe
	//this is neccesary to calculate where the projectile should be shot
	//if not for this vector, projectiles would be shot from owners point
	//this is obviously wrong
	private Vector2f weaponPipePosition;

	public ArmsComponent(Point2f point, Vector2f velocity, Vector2f acceleration, Vector2f direction, Vector2f weaponPipePosition) {
		this.point 					= point;
		this.velocity 				= velocity;
		this.acceleration 			= acceleration;
		this.direction				= direction;
		this.weaponPipePosition 	= weaponPipePosition;
	}
	
	public void setWeaponPipePosition(Vector2f weaponPipePosition) {
		this.weaponPipePosition = weaponPipePosition;
	}
	
	/**
	 * 
	 * @return Projectile that the weapon fires
	 */
	public Projectile fire(){
		Point2f tempPoint = new Point2f();
		tempPoint.add(point);
		tempPoint.add(weaponPipePosition);
		
		float baseSpeedMagnitude = 1.0f;
		Vector2f baseSpeed = new Vector2f(direction);
		baseSpeed.scale(baseSpeedMagnitude);
		baseSpeed.normalize();
		System.out.println(baseSpeed);
		baseSpeed.add(velocity);
		return ProjectileFactory.create(tempPoint, baseSpeed, acceleration, direction, 1, 1);
	}
}
