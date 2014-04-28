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
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	private boolean leftHold, mainHold, rightHold, fireHold;
	private int left, main, right, fire;
	
	
	public PlayerState(Set<Integer> listOfPressedKeys, Set<Integer> listOfReleasedKeys){
		this.listOfPressedKeys=listOfPressedKeys;
		this.listOfReleasedKeys=listOfReleasedKeys;
		this.left=Keyboard.KEY_A;
		this.main=Keyboard.KEY_W;
		this.right=Keyboard.KEY_D;
		this.fire=Keyboard.KEY_SPACE;
	}
	public void setSpaceship(Spaceship spaceship){
		this.spaceship=spaceship;
	}
	public void setLeftButton(int key){
		this.left=key;
	}
	public void setRightButton(int key){
		this.right=key;
	}
	public void setMainButton(int key){
		this.main=key;
	}
	public void setFireButton(int key){
		this.fire=key;
	}
	/**
	 * Set booleans representing if a key is held down or not
	 * If a key is inside pressedKeys (someone pressed a key), set the boolean to true
	 * If a key is inside releasedkeys (someone released a key), set the boolean to false
	 */
	public synchronized void manageInput() {
		for(int key : listOfPressedKeys) {
			if (key == left) {
				leftHold = true;
			}
			else if (key == main) {
				mainHold = true;
			}
			else if (key == right) {
				rightHold = true;
			}
			else if (key == fire) {
				fireHold = true;
			}
		}
		
		for(int key : listOfReleasedKeys) {
			if (key == left) {
				leftHold = false;
			}
			else if (key == main) {
				mainHold = false;
			}
			else if (key == right) {
				rightHold = false;
			}
			else if (key == fire) {
				fireHold = false;
			}
		}
		
		listOfPressedKeys.clear();
		listOfReleasedKeys.clear();
	}
	
	public void executeInput() {//MOVE TO INTERFACE
		if(fireHold) {
			spaceship.fireWeapon();
		}
		if (mainHold) {
			spaceship.activateMainThruste();
		} else {
			spaceship.deactivateMainThruster();
		}
		if (leftHold) {
			spaceship.activateLeftThruste();
		} else {
			spaceship.deactivateLeftThruster();
		}
		if (rightHold) {
			spaceship.activateRightThruste();
		} else {
			spaceship.deactivateRightThruster();
		}
	}
}
