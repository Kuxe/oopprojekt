package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;

public interface Roundstate {
	public enum state {
		ACTIVE,
		INACTIVE
	}
	public void addUser(User user);
	public void removeUser(User user);
	public state getState();
	public void addListener(PropertyChangeListener listener);
}
