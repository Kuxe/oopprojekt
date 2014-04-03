package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IGameObject {
	public enum type{
		SPASESHIP,
		PROJECTILE,
		ASTEROID,
		PICKUP
	}
	public String getType();
}
