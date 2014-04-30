package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;


public class Round {

	private Gameworld world;
	private Roundstate state;
	
	public Round() {
		world = new Gameworld();
		state = new InactiveRound();
	}
	
	public void addUser(User user) {
		
		Spaceship ss = SpaceshipFactory.create(
				new Point2f(
						(float)Math.random() * world.getBorder().getWorldWidth(),
						(float)Math.random() * world.getBorder().getWorldHeight()),
				new Vector2f((float)Math.random(), (float)Math.random()));
				
		Asteroid ss2 = new Asteroid(new Point2f(500, 500), 100, 100);
		world.addSpaceship(ss);
		user.setSpaceship(ss);
		world.addAsteroid(ss2);
	}

	public void start() {
		state = new ActiveRound();
	}
	
	public void end() {
		state = new InactiveRound();
	}

	public void update() {
		world.update();
	}

	public Map<Object, IDrawable> getIDrawables() {
		return world.getIDrawables();
	}

}
