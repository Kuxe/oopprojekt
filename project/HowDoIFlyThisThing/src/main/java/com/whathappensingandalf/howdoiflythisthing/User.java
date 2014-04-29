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
	private int left, main, right, fire;
	
	public User(){
		spectatorState = new SpectatorState();
		playerState = new PlayerState();
		state = spectatorState;
		left=Keyboard.KEY_A;
		main=Keyboard.KEY_W;
		right=Keyboard.KEY_D;
		fire=Keyboard.KEY_SPACE;
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
	
	public void executeInput(Set<Integer> listOfHoldKeys) {
		state.fireHold(listOfHoldKeys.contains(fire));
		state.mainHold(listOfHoldKeys.contains(main));
		state.leftHold(listOfHoldKeys.contains(left));
		state.rightHold(listOfHoldKeys.contains(right));
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
