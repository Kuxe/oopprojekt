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
	boolean aHold, wHold, dHold, spaceHold;
	
	
	public PlayerState(Set<Integer> listOfPressedKeys, Set<Integer> listOfReleasedKeys){
		this.listOfPressedKeys=listOfPressedKeys;
		this.listOfReleasedKeys=listOfReleasedKeys;
	}
	public void setSpaceship(Spaceship spaceship){
		this.spaceship=spaceship;
	}
	/**
	 * Set booleans representing if a key is held down or not
	 * If a key is inside pressedKeys (someone pressed a key), set the boolean to true
	 * If a key is inside releasedkeys (someone released a key), set the boolean to false
	 */
	public synchronized void manageInput() {
		for(int key : listOfPressedKeys) {
			switch(key) {
			case Keyboard.KEY_A: {
				aHold = true;
				break;
			}
			case Keyboard.KEY_W: {
				wHold = true;
				break;
			}
			case Keyboard.KEY_D: {
				dHold = true;
				break;
			}
			case Keyboard.KEY_SPACE: {
				spaceHold = true;
				break;
			}
			}
		}
		
		for(int key : listOfReleasedKeys) {
			switch(key) {
			case Keyboard.KEY_A: {
				aHold = false;
				break;
			}
			case Keyboard.KEY_W: {
				wHold = false;
				break;
			}
			case Keyboard.KEY_D: {
				dHold = false;
				break;
			}
			case Keyboard.KEY_SPACE: {
				spaceHold = false;
				break;
			}
			}
		}
		
		listOfPressedKeys.clear();
		listOfReleasedKeys.clear();
	}
	
	public void executeInput() {//MOVE TO INTERFACE
		if(spaceHold) {
			spaceship.fireWeapon();
		}
		if (wHold) {
			spaceship.activateMainThruste();
		} else {
			spaceship.deactivateMainThruster();
		}
		if (aHold) {
			spaceship.activateLeftThruste();
		} else {
			spaceship.deactivateLeftThruster();
		}
		if (dHold) {
			spaceship.activateRightThruste();
		} else {
			spaceship.deactivateRightThruster();
		}
	}
}
