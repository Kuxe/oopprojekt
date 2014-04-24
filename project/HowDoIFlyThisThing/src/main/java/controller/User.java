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
		playerState.setLeftButton(key);
	}
	public void setRightButton(int key){
		playerState.setRightButton(key);
	}
	public void setMainButton(int key){
		playerState.setMainButton(key);
	}
	public void setFireButton(int key){
		playerState.setFireButton(key);
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
	
	public synchronized void manageInput(){
		this.state.manageInput();
	}
	public void executeInput() {
		this.state.executeInput();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			state = spectatorState;
		}
	}
	
}
