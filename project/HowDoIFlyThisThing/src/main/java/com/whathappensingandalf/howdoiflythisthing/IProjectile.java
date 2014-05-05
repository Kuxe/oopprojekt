package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;

public interface IProjectile extends IMovable, ICollidable, IGameObject, IDrawable, Cloneable{
	public int getDamage();
	
	public void removePropertyChangeListener(PropertyChangeListener pcl);
	
	public void addPropertyChangeListener(PropertyChangeListener pcl);
	
	public static enum Message{
		PROJECTILE_DIE
	}
}
