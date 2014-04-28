package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

import controller.User;

public class Round {

	Gameworld world;
	
	Roundstate state;
	
	public void addUser(User user) {
		Spaceship ss = SpaceshipFactory.create(new Point2f(0, 0), new Vector2f(1, 1));
		world.addSpaceship(ss);
		user.setSpaceship(ss);
	}

	public void start() {
		state = new ActiveRound();
	}
	
	public void end() {
		state = new InactiveRound();
	}

}
