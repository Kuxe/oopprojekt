package controller;

import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import java.util.HashSet;
import java.util.Set;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 *
 * @author Martin Nilsson
 */
public class User implements KeyListener{
	
	private IUserState state;
	private Spaceship spaceship;
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	boolean aHold, wHold, dHold, spaceHold;
	
	public User(){
		listOfPressedKeys = new HashSet();
		listOfReleasedKeys = new HashSet();
	}
	
	public void setSpaceship(Spaceship spaceship){
		this.spaceship=spaceship;
	}

	public void keyPressed(int key, char c) {
		listOfPressedKeys.add(key);
	}

	public void keyReleased(int key, char c) {
		listOfReleasedKeys.add(key);
	}

	public void setInput(Input input) {
		// TODO Auto-generated method stub
	}

	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	public void inputEnded() {
		// TODO Auto-generated method stub
	}

	public void inputStarted() {
		// TODO Auto-generated method stub
	}
	private void executeInput() {
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
	
	/**
	 * Set booleans representing if a key is held down or not
	 * If a key is inside pressedKeys (someone pressed a key), set the boolean to true
	 * If a key is inside releasedkeys (someone released a key), set the boolean to false
	 */
	private synchronized void manageInput() {
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
	
}
