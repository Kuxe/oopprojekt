package com.whathappensingandalf.howdoiflythisthing;

import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Connection;

import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Main model class. This is the class that is exposed to the controller
 * and all functionality regarding the model is called from here.
 */
public class HowDoIFlyThisThing {

	private Keybindings keybindings;
	private ModelNetworkState state;
	
	public HowDoIFlyThisThing(Keybindings keybindings) {
		this.keybindings = new Keybindings(keybindings);
		state = new NoneState();
	}
	
	/**
	 *  
	 * @param spaceship
	 */
	public void addUser(Connection connection) {
		state.addUser(connection.getID());
	}
	
	public void host() throws IOException {
		state = new HostState(keybindings);
	}
	
	public void join(String ip) throws IOException {
		state = new ClientState(ip, keybindings);
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		state.update(listOfHoldKeys);
	}
	
	public Set<DrawableData> getDrawableData() {
		return state.getDrawableData();
	}
	
	public Point2f getSpaceshipPoint() {
		return state.getSpaceshipPoint();
	}
	
	public long getCountdown()
	{
		return state.getCountdown();
	}
	
	/**
	 * 
	 * @return string description of model state, ie "Waiting for players...", "Round commencing!", "Round finished"
	 */
	public String getModelStatus() {
		return state.getModelStatus();
	}
	
	/**
	 * Should always be called when program is exiting
	 * it stops clients and servers, does all the 
	 * cleaning work
	 */
	public void cleanup() {
		state.cleanup();
	}
	public Set<String> getListOfSounds(){
		return state.getListOfSounds();
	}
	
	public int getHull(){
		return state.getHull();
	}
	
	public int getShield(){
		return state.getShield();
	}
	
	public ModelNetworkState getState(){
		return state;
	}
	
	public WorldBorder getWorldBorder() {
		return state.getWorldBorder();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		state.addPropertyChangeListener(listener);
	}
}
