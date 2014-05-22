package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

public class InactiveRound implements IRoundstate {
	
	public enum Message {
		START_ROUND
	}
	
	private PropertyChangeSupport pcs;
	
	public InactiveRound() {
		
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addUser(User user, Set<User> users) {
		users.add(user);
		user.requestSpaceship();
		if(users.size() == 2) {
			pcs.firePropertyChange(Message.START_ROUND.toString(), false, true);
		}
	}

	@Override
	public void removeUser(User user, Set<User> users, Round round) {
		if(user.getState().equals(IUserState.State.PLAYER_STATE)) {
			round.decreaseUsersAlive();
		}
		users.remove(user);
	}

	@Override
	public State getState() {
		return IRoundstate.State.INACTIVE;
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);		
	}

	@Override
	public String getStatus() {
		return "Waiting for more players...";
	}
}
