package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IPickup extends ICollidable, IGameObject, IDrawable, Cloneable{
	public static enum Message{
		PICKUP_DIE
	}
	public void affectMe(Spaceship spaceship);
}
