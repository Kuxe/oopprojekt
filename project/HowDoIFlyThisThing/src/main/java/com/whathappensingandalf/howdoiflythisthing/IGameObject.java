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
		MISSILE
	}
	public String getType();
}
