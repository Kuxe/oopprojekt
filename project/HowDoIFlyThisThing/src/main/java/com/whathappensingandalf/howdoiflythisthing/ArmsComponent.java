package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.factorys.BulletFactory;

/**
 *
 * @author Joakim Thorén
 */
public class ArmsComponent {
    
	private IProjectile equipedProjectile;
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
	public void setWeapon(IProjectile weapon){
		
	}
	
	/**
	 * 
	 * @return Projectile that the weapon fires
	 */
	public IProjectile fire(){
		Point2f tempPoint = new Point2f(point.x, point.y);
		tempPoint.add(weaponPipePosition);
		
		float baseSpeedMagnitude = 100.0f;
		Vector2f baseSpeed = new Vector2f(direction.x, direction.y);
		baseSpeed.normalize();
		baseSpeed.scale(baseSpeedMagnitude);
		baseSpeed.add(velocity);
		return BulletFactory.create(tempPoint, baseSpeed, acceleration, direction, 3, 3);
	}
	public boolean equals(Object rhs){
		if(rhs==this){
			return true;
		}else if(!(rhs instanceof ArmsComponent)){
			return false;
		}else{
			ArmsComponent other = (ArmsComponent)rhs;
			return 	this.acceleration.equals(other.acceleration)&&
					this.direction.equals(other.direction)&&
					this.point.equals(other.point)&&
					this.velocity.equals(other.velocity)&&
					this.weaponPipePosition.equals(other.weaponPipePosition);
		}
	}
	
}
