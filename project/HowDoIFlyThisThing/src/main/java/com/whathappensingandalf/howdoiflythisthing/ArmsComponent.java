package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import utils.ITimer;
import utils.Timer;

import com.whathappensingandalf.howdoiflythisthing.factorys.BulletFactory;
import com.whathappensingandalf.howdoiflythisthing.factorys.IProjectileFactory;

/**
 *
 * @author Joakim Thorén
 */
public class ArmsComponent {
    
	private Point2f point;
	private Vector2f velocity;
	private Vector2f acceleration;
	private Vector2f direction;
	private String type;
	private ITimer timer;
	
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
		this.type					= IGameObject.Type.BULLET.toString();
		this.timer					= new Timer(1000);
	}
	
	public void setWeaponPipePosition(Vector2f weaponPipePosition) {
		this.weaponPipePosition = weaponPipePosition;
	}
	public void setWeapon(IProjectile weapon){
		this.type = weapon.getType();
		if(type.equals("BULLET")){
			this.timer.setTimerLength(1000);
		}else if(type.equals("MISSILE")){
			this.timer.setTimerLength(2000);
		}
	}
	public String getWeapon(){
		return this.type;
	}
	
	public boolean canFire(){
		if(timer.isTimerDone()){
			timer.start();
			return true;
		}
		return false;
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
		return IProjectileFactory.create(tempPoint, baseSpeed, acceleration, direction, type);
	}
	
	@Override
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
