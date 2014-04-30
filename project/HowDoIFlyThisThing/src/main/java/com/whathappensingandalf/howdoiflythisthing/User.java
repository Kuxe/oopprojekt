package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import javax.vecmath.Point2f;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Martin Nilsson
 */
public class User implements PropertyChangeListener{
	
	private IUserState state;
	private final SpectatorState spectatorState;
	private final PlayerState playerState;
	//private int left, main, right, fire;
	private final Keybindings keybindings;
	
	public User(){
		spectatorState = new SpectatorState();
		playerState = new PlayerState();
		state = spectatorState;
		keybindings = new Keybindings();
		keybindings.setLeft(Keyboard.KEY_A);
		keybindings.setMain(Keyboard.KEY_W);
		keybindings.setRight(Keyboard.KEY_D);
		keybindings.setFire(Keyboard.KEY_SPACE);
	}
	
	public Spaceship getSpaceship() {
		return state.getSpaceship();
	}
	
	public void setSpaceship(Spaceship spaceship){
		this.state=playerState;
		this.playerState.setSpaceship(spaceship);
		spaceship.addPropertyChangeListener(this);
	}
	
	public void setLeftButton(int key){
		keybindings.setLeft(key);
	}
	public void setRightButton(int key){
		keybindings.setRight(key);
	}
	public void setMainButton(int key){
		keybindings.setMain(key);
	}
	public void setFireButton(int key){
		keybindings.setFire(key);
	}
	
	public void executeInput(Set<Integer> listOfHoldKeys) {
		state.fireHold(listOfHoldKeys.contains(keybindings.getFire()));
		state.mainHold(listOfHoldKeys.contains(keybindings.getMain()));
		state.leftHold(listOfHoldKeys.contains(keybindings.getLeft()));
		state.rightHold(listOfHoldKeys.contains(keybindings.getRight()));
	}
	
	public Point2f getSpaceshipPoint() {
		return state.getSpaceshipPosition();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			state = spectatorState;
		}
	}
	
}
