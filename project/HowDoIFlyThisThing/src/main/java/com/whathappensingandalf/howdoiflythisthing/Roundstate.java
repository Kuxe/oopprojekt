package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.util.Set;

public interface Roundstate {
	public enum State {
		ACTIVE,
		INACTIVE
	}
	public void addUser(User user, Set<User> users);
	public void removeUser(User user, Set<User> users, Round round);
	public State getState();
	public void addListener(PropertyChangeListener listener);
	public String getStatus();
}
