package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public class SpaceShip implements IArmable, IMovable{

    private Weapon weapon;
    
    public SpaceShip(){
        
    }
    
    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon=weapon;
    }
    
}
