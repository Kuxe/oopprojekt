package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IGameObject {
	public enum Type{
		SPACESHIP,
		BULLET,
		THRUSTER_FIRE,
		ASTEROID,
		PICKUP,
		HEALTH_PICKUP,
		WEAPON_PICKUP,
		MISSILE,
		COOKIE_CRACKER
	}
	public String getType();
}
