package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controller.User;

public class Session {

	private Round round;
	HashMap<Integer, User> users;
	
	public Session() {
		round = new Round();
		users = new HashMap();
	}
	
	public void addUser(int ip) {
		User user = new User();
		users.put(ip, user);
		round.addUser(user);
		if(users.size() == 2) {
			round.start();
		}
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		round.update();
		for(User user : users.values()) {
			user.executeInput(listOfHoldKeys);
		}
	}

	public Map<Object, IDrawable> getIDrawables() {
		return round.getIDrawables();
	}
	
}
