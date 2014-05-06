package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

public class ActiveRound implements Roundstate {

	private Gameworld world;
	private Set<User> users;
	private PropertyChangeSupport pcs;
	
	public ActiveRound(Gameworld world, Set<User> users) {
		System.out.println("Starting round");
		this.world = world;
		this.users = users;
		
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addUser(User user) {
		//Do nothing - round is already running!
	}

	@Override
	public void removeUser(User user) {
		world.slateObjectForRemoval(user.getSpaceship());
		users.remove(user);
	}
	
	@Override
	public state getState() {
		return Roundstate.state.ACTIVE;
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}
