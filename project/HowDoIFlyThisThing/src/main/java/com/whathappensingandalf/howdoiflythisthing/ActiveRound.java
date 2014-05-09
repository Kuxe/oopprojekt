package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

public class ActiveRound implements Roundstate {

	private Round round;
	private Set<User> users;
	private PropertyChangeSupport pcs;
	
	public ActiveRound(Round ownerRound, Set<User> users) {
		round = ownerRound;
		this.users = users;
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public void removeUser(User user) {
		users.remove(user);
		if(user.getState().equals(IUserState.state.PLAYER_STATE)) {
			round.decreaseUsersAlive();
		}
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
