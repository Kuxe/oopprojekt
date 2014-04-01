package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IGameObject {
	public enum type{
		SPASESHIP,
		PROJECTILE,
		PICKUP
	}
	public String getType();
}
