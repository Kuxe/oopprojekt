package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.whathappensingandalf.howdoiflythisthing.factorys.SpaceshipFactory;

/**
 *     
 * @author Joakim "Kuxe" Thorén
 * 
 * Class used for maintaining logic regarding rounds in-game.
 * Players are added to rounds, and this class responsibility is to
 * decide from how many players are added (and removed) when to
 * start new rounds. When using this class, only calling "addUser"
 * and "removeUser" should be enough.
 */
public class Round implements PropertyChangeListener{
	
	private Gameworld world;
	private Roundstate state;
	private Set<User> users;
	
	public Round() {
		world = new Gameworld();
		users = new HashSet();
		state = new InactiveRound(world, users);
		state.addListener(this);
	}
	
	public void addUser(User user) {
		state.addUser(user);
	}
	
	public void removeUser(User user) {
		state.removeUser(user);
		if(users.size() == 1) {
			end();
		}
	}

	/**
	 * On starting a new round a collection of users are given
	 * These users will play the round
	 * This method calls end() beforehand, effectively forcing
	 * a restart of round if not already ended.
	 * @param users
	 */
	public void start() {
		end();
		world.reset();
		for(User user : users) {
			Spaceship ss = SpaceshipFactory.create(
					new Point2f(
							(float)Math.random() * world.getBorder().getWorldWidth(),
							(float)Math.random() * world.getBorder().getWorldHeight()),
					new Vector2f((float)Math.random(), (float)Math.random()));
			
			world.addSpaceship(ss);
			user.setSpaceship(ss);
		}
		state = new ActiveRound(world, users);
		state.addListener(this);
	}
	
	public void end() {
		state = new InactiveRound(world, users);
		state.addListener(this);
	}

	public void update() {
		world.update();
	}

	public Set<DrawableData> getDrawableData() {
		return world.getDrawableData();
	}
	public Set<String> getListOfSounds(){
		return world.getListOfSounds();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(InactiveRound.message.START_ROUND.toString())) {
			start();
		}
	}
}