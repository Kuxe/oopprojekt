package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashMap;

import controller.User;

public class Session {

	private Round round;
	HashMap<Integer, User> users;
	
	public void addUser(int ip) {
		User user = new User();
		users.put(ip, user);
		round.addUser(user);
		if(users.size() == 2) {
			round.start();
		}
	}
	
}
