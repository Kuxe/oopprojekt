package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IGameObject {
	public enum type{
		SPACESHIP,
		BULLET,
		ASTEROID,
		PICKUP,
		HEALTH_PICKUP,
		WEAPON_PICKUP,
		MISSILE,
		EXPLOSION,
	}
	public String getType();
}
