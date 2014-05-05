package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IGameObject {
	public enum type{
		SPACESHIP,
		PROJECTILE,
		ASTEROID,
		PICKUP,
		HEALTH_PICKUP
	}
	public String getType();
}
