package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public interface IArmable {
    public ArmsComponent getWeapon();
    public void setWeapon(ArmsComponent weapon);
    public boolean fireWeapon();
}
