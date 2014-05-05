package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Martin Nilsson
 */
public interface IArmable {
    public ArmsComponent getWeapon();
    public void setWeapon(ArmsComponent weapon);
    public IProjectile fireWeapon();
}
