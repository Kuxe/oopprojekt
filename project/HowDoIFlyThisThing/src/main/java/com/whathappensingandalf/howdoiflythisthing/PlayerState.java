/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;

/**
 *
 * @author Martin Nilsson
 */
public class PlayerState implements IUserState{
	
	private Spaceship spaceship;
	
	public void setSpaceship(Spaceship spaceship){
		this.spaceship=spaceship;
	}

	public void mainHold(boolean held) {
		if(held){
			spaceship.activateMainThruste();
		}else{
			spaceship.deactivateMainThruster();
		}
	}

	public void leftHold(boolean held) {
		if(held){
			spaceship.activateLeftThruste();
		}else{
			spaceship.deactivateLeftThruster();
		}
	}

	public void rightHold(boolean held) {
		if(held){
			spaceship.activateRightThruste();
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
	
}
