package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public class SpaceShip implements IArmable, IMovable{

    private Weapon weapon;
    private MoveComponent moveComponent = new MoveComponent();
    private Thruster[] thruster = new Thruster[3];
    
    public SpaceShip(){
        
    }
    
    
    
    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon=weapon;
    }

    public Vector getSpeed() {
        return this.moveComponent.getSpeed();
    }

    public Vector getAcc() {
        return this.moveComponent.getAcc();
    }

    public Point getPos() {
        return this.moveComponent.getPos();
    }

    public void setSpeed() {
        this.moveComponent.setSpeed();
    }

    public void setAcc() {
        this.moveComponent.setAcc();
    }

    public void setPos() {
        this.moveComponent.setPos();
    }
    
}
