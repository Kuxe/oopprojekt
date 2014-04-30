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
public class SpectatorState implements IUserState{

	public void mainHold(boolean held) {
		//To change body of generated methods, choose Tools | Templates.
	}

	public void leftHold(boolean held) {
		//To change body of generated methods, choose Tools | Templates.
	}

	public void rightHold(boolean held) {
		//To change body of generated methods, choose Tools | Templates.
	}

	public void fireHold(boolean held) {
		//To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * No spaceship avaiable so returns (0, 0)
	 */
	public Point2f getSpaceshipPosition() {
		return new Point2f(0, 0);
	}

	public Spaceship getSpaceship() {
		//Has no spaceship
		return null;
	}

}
