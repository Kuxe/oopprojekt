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
		PICKUP
	}
	public String getType();
}
