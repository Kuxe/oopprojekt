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
	
	private Gameworld world;
	private Set<User> users;
	private PropertyChangeSupport pcs;
	
	public InactiveRound(Gameworld world, Set<User> users) {
		this.world = world;
		this.users = users;
		
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addUser(User user) {
		users.add(user);
		
		Spaceship ss = SpaceshipFactory.create(
				new Point2f(
						(float)Math.random() * world.getBorder().getWorldWidth(),
						(float)Math.random() * world.getBorder().getWorldHeight()),
				new Vector2f((float)Math.random(), (float)Math.random()));
		
		world.addSpaceship(ss);
		user.setSpaceship(ss);
		if(users.size() == 2) {
			pcs.firePropertyChange(message.START_ROUND.toString(), false, true);
		}
	}

	@Override
	public void removeUser(User user) {
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
}
