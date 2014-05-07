package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Martin Nilsson
 */
public interface IPickup extends ICollidable, IGameObject, IDrawable, Cloneable{
	public static enum Message{
		PICKUP_DIE
	}
	public void affectMe(Spaceship spaceship);
	

    public void addPropertyChangeListener(PropertyChangeListener pcl);
    
    public void removePropertyChangeListener(PropertyChangeListener pcl);
}
