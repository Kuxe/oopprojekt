package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author Martin Nilsson
 */
public class User implements PropertyChangeListener{
	
	public enum Message {
		LOST_SPACESHIP,
		REQUEST_SPACESHIP
	}
	
	private IUserState state;
	private final SpectatorState spectatorState;
	private final PlayerState playerState;
	//private int left, main, right, fire;
	private Keybindings keybindings;
	private Set<Integer> listOfHoldKeys;
	private PropertyChangeSupport pcs;
	
	
	public User(){
		spectatorState = new SpectatorState();
		playerState = new PlayerState(new Spaceship(new Point2f(10, 10), new Vector2f(1,1), 70, 50));
		state = spectatorState;
		keybindings = new Keybindings();
		keybindings.setLeft(Keyboard.KEY_A);
		keybindings.setMain(Keyboard.KEY_W);
		keybindings.setRight(Keyboard.KEY_D);
		keybindings.setFire(Keyboard.KEY_SPACE);
		pcs = new PropertyChangeSupport(this);
		listOfHoldKeys = new HashSet<Integer>();
	}
	
	public Set<Integer> getListOfHoldKeys() {
		return listOfHoldKeys;
	}
	
	public void setListOfHoldKeys(Set<Integer> list) {
		this.listOfHoldKeys = list;
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
	public void setKeybindings(Keybindings keybindings){
		this.keybindings = new Keybindings(keybindings);
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
	
	public void suicide() {
		state.suicide();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public IUserState.State getState() {
		return state.getState();
	}
	
	public void requestSpaceship() {
		pcs.firePropertyChange(Message.REQUEST_SPACESHIP.toString(), false, true);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			spectatorState.setCameraPoint(playerState.getSpaceshipPosition());
			state = spectatorState;
			pcs.firePropertyChange(Message.LOST_SPACESHIP.toString(), false, true);
		}
	}
	
	public int getHull(){
		return state.getHull();
	}
	
	public int getShield(){
		return state.getShield();
	}
}