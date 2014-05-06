package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IPickup extends ICollidable, IGameObject, IDrawable, Cloneable{
	public void affectMe(Spaceship spaceship);
}
