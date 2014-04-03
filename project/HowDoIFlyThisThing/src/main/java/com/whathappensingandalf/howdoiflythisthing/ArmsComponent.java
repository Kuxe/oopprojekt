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

	public ArmsComponent(Point2f point, Vector2f velocity, Vector2f acceleration, Vector2f direction) {
		this.point 			= point;
		this.velocity 		= velocity;
		this.acceleration 	= acceleration;
		this.direction		= direction;
	}
	
	/**
	 * 
	 * @return Projectile that the weapon fires
	 */
	public Projectile fire(){
		return ProjectileFactory.create(point, velocity, acceleration, direction);
	}
}
