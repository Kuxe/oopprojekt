/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import java.util.Set;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Martin
 */
public class PlayerState implements IUserState{
	
	private Spaceship spaceship;
	//private Set<Integer> listOfPressedKeys;
	//private Set<Integer> listOfReleasedKeys;
	//private boolean leftHold, mainHold, rightHold, fireHold;
	//private int left, main, right, fire;
	
	
	public PlayerState(Set<Integer> listOfPressedKeys, Set<Integer> listOfReleasedKeys){
		//this.listOfPressedKeys=listOfPressedKeys;
		//this.listOfReleasedKeys=listOfReleasedKeys;
		//this.left=Keyboard.KEY_A;
		//this.main=Keyboard.KEY_W;
		//this.right=Keyboard.KEY_D;
		//this.fire=Keyboard.KEY_SPACE;
	}
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
	
}
