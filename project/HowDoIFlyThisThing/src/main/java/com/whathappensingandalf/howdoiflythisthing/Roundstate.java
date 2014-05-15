package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.util.Set;

public interface Roundstate {
	public enum state {
		ACTIVE,
		INACTIVE
	}
	public void addUser(User user, Set<User> users);
	public void removeUser(User user, Set<User> users, Round round);
	public state getState();
	public void addListener(PropertyChangeListener listener);
	public String getStatus();
}
