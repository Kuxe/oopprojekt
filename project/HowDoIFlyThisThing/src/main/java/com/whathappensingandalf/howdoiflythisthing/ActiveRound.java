package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

public class ActiveRound implements Roundstate {
	private PropertyChangeSupport pcs;
	
	public ActiveRound() {
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addUser(User user, Set<User> users) {
		users.add(user);
	}

	@Override
	public void removeUser(User user, Set<User> users, Round round) {
		users.remove(user);
		if(user.getState().equals(IUserState.State.PLAYER_STATE)) {
			round.decreaseUsersAlive();
		}
	}
	
	@Override
	public State getState() {
		return Roundstate.State.ACTIVE;
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public String getStatus() {
		return "Fight!";
	}
}
