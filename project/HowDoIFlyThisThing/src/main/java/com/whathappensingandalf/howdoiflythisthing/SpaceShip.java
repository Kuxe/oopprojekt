package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public class SpaceShip implements IArmable, IMovable{

    private Weapon weapon;
    private MoveComponent moveComponent;
    private Point position;
    private Vector acceleration;
    private Vector direction;
    private Vector speed;
    private Thruster[] thruster = new Thruster[3];
    
    public SpaceShip(Point position, Vector direction){
        this.moveComponent = new MoveComponent();
        this.position = position;
        this.acceleration = 0; //TODO create a new vector.
        this.direction = direction;
        this.speed = 0; //TODO create a new vector.
    }
    
    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon=weapon;
    }

    public void move() {
        this.moveComponent.move();
    }

    public Vector getAcceleration() {
        return this.acceleration;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public Point getPosition() {
        return this.position;
    }

    public Vector getSpeed() {
        return this.speed;
    }

    public void setSpeed(Vector speed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAcceleration(Vector accelertion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDirection(Vector direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPosition(Point possition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
