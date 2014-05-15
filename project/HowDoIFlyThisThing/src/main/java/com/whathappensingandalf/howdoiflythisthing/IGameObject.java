package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IGameObject {
	public enum Type{
		SPACESHIP,
		BULLET,
		ASTEROID,
		PICKUP,
		HEALTH_PICKUP,
		WEAPON_PICKUP,
		MISSILE,
	}
	public String getType();
}
