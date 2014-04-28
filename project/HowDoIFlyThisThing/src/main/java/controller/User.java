package controller;

import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 *
 * @author Martin Nilsson
 */
public class User implements KeyListener,PropertyChangeListener{
	
	private IUserState state;
	private SpectatorState spectatorState;
	private PlayerState playerState;
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	private boolean leftHold, mainHold, rightHold, fireHold;
	private int left, main, right, fire;
	//boolean aHold, wHold, dHold, spaceHold;
	
	public User(){
		listOfPressedKeys = new HashSet();
		listOfReleasedKeys = new HashSet();
		spectatorState = new SpectatorState(listOfPressedKeys, listOfReleasedKeys);
		playerState = new PlayerState(listOfPressedKeys, listOfReleasedKeys);
		state = spectatorState;
	}
	
	public void setSpaceship(Spaceship spaceship){
		this.state=playerState;
		this.playerState.setSpaceship(spaceship);
		spaceship.addPropertyChangeListener(this);
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
		state.fireHold(fireHold);
			
		state.mainHold(mainHold);
			
		state.leftHold(leftHold);
			
		state.rightHold(rightHold);
	}

	public synchronized void keyPressed(int key, char c) {
		listOfPressedKeys.add(key);
	}

	public synchronized void keyReleased(int key, char c) {
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

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			state = spectatorState;
		}
	}
	
}
