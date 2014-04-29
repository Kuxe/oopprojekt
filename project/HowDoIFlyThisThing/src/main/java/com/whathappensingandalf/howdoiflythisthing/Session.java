package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import org.lwjgl.input.Keyboard;


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
	
	/**
	 * Method used for simulating added user
	 */
	public void TEST_addUser2() {
		User user = new User();
		user.setFireButton(Keyboard.KEY_RCONTROL);
		user.setLeftButton(Keyboard.KEY_J);
		user.setRightButton(Keyboard.KEY_L);
		user.setMainButton(Keyboard.KEY_I);
		users.put(0000, user);
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
	
	public Point2f getSpaceshipPoint(int ip) {
		return users.get(ip).getSpaceshipPoint();
	}
}
