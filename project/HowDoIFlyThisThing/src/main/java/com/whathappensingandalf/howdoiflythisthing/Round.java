package com.whathappensingandalf.howdoiflythisthing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Timer;
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
	private int usersAlive = 0;
		
	public Round() {
		world = new Gameworld();
		world.addPropertyChangeListener(this);
		
		users = new HashSet();
		state = new InactiveRound(world, users);
		state.addListener(this);
	}
	
	public synchronized void addUser(User user) {
		state.addUser(user);
		user.addPropertyChangeListener(this);
	}
	
	public synchronized void removeUser(User user) {
		user.suicide();
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
	public synchronized void start() {
		end();
		world = new Gameworld();
		world.addPropertyChangeListener(this);
		for(User user : users) {
			Spaceship ss = SpaceshipFactory.create(
					new Point2f(
							(float)Math.random() * world.getBorder().getWorldWidth(),
							(float)Math.random() * world.getBorder().getWorldHeight()),
					new Vector2f((float)Math.random(), (float)Math.random()));
			
			user.setSpaceship(ss);
			world.addSpaceship(ss);
		}
		usersAlive = users.size();
		state = new ActiveRound(world, users);
		state.addListener(this);
	}
	
	public void end() {
		state = new InactiveRound(world, users);
		state.addListener(this);
	}

	public synchronized void update() {
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
		System.out.println(evt.getPropertyName());
		if(evt.getPropertyName().equals(InactiveRound.message.START_ROUND.toString())) {
			start();
		}
		
		//Is user lost his spaceship...
		else if (evt.getPropertyName().equals(User.message.LOST_SPACESHIP.toString())) {
			
			//One user died...
			usersAlive -= 1;
			
			//If last man standing and round is active, start new round
			if(usersAlive == 1 && state.getState().equals(Roundstate.state.ACTIVE)) {
				start();
			}
			
			//If round is inactive, give a new ship to user
			if (state.getState().equals(Roundstate.state.INACTIVE)) {
				User user = (User)evt.getSource();
				Spaceship ss = SpaceshipFactory.create(
						new Point2f(
								(float)Math.random() * world.getBorder().getWorldWidth(),
								(float)Math.random() * world.getBorder().getWorldHeight()),
						new Vector2f((float)Math.random(), (float)Math.random()));
				
				user.setSpaceship(ss);
				world.addSpaceship(ss);
			}
		}
	}
}