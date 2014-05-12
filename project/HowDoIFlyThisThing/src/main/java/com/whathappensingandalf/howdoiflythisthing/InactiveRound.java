package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

public class InactiveRound implements Roundstate {
	
	public enum message {
		START_ROUND
	}
	
	private Round round;
	private Gameworld world;
	private Set<User> users;
	private PropertyChangeSupport pcs;
	
	public InactiveRound(Round round, Set<User> users) {
		this.users = users;
		this.round = round;
		world = round.getWorld();
		
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addUser(User user) {
		users.add(user);
		user.requestSpaceship();
		if(users.size() == 2) {
			pcs.firePropertyChange(message.START_ROUND.toString(), false, true);
		}
	}

	@Override
	public void removeUser(User user) {
		if(user.getState().equals(IUserState.state.PLAYER_STATE)) {
			round.decreaseUsersAlive();
		}
		users.remove(user);
	}

	@Override
	public state getState() {
		return Roundstate.state.INACTIVE;
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
