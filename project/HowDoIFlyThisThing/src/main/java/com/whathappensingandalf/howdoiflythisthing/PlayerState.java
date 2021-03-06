package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;

/**
 *
 * @author Martin Nilsson
 */
public class PlayerState implements IUserState{
	
	private Spaceship spaceship;
	
	public PlayerState(Spaceship spaceship){
		this.spaceship = spaceship;
	}
	
	public void setSpaceship(Spaceship spaceship){
		this.spaceship=spaceship;
	}

	public void mainHold(boolean held) {
		if(held){
			spaceship.activateMainThruster();
		}else{
			spaceship.deactivateMainThruster();
		}
	}

	public void leftHold(boolean held) {
		if(held){
			spaceship.activateLeftThruster();
		}else{
			spaceship.deactivateLeftThruster();
		}
	}

	public void rightHold(boolean held) {
		if(held){
			spaceship.activateRightThruster();
		}else{
			spaceship.deactivateRightThruster();
		}
	}

	public void fireHold(boolean held) {
		if(held){
			spaceship.fireWeapon();
		}
	}

	public Point2f getSpaceshipPosition() {
		return spaceship.getPosition();
	}

	public Spaceship getSpaceship() {
		return spaceship;
	}

	@Override
	public void suicide() {
		spaceship.remove();
	}

	@Override
	public State getState() {
		return IUserState.State.PLAYER_STATE;
	}
	
	public int getHull(){
		return spaceship.getHull();
	}

	public int getShield(){
		return spaceship.getShield();
	}
}