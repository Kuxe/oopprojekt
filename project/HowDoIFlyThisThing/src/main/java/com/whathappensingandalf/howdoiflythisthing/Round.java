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
	private Set<Spaceship> spaceships;
		
	public Round() {
		world = new Gameworld();
		world.addPropertyChangeListener(this);
		
		spaceships = new HashSet();
		
		users = new HashSet();
		state = new InactiveRound(world, users);
		state.addListener(this);
	}
	
	public void addUser(User user) {
		state.addUser(user);
		user.addPropertyChangeListener(this);
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
	public synchronized void start() {
		System.out.println(Thread.currentThread().getName());
		end();
		world = new Gameworld();
		world.addPropertyChangeListener(this);
		for(User user : users) {
			Spaceship ss = SpaceshipFactory.create(
					new Point2f(
							(float)Math.random() * world.getBorder().getWorldWidth(),
							(float)Math.random() * world.getBorder().getWorldHeight()),
					new Vector2f((float)Math.random(), (float)Math.random()));
			
			System.out.println("setting spaceship");
			user.setSpaceship(ss);
			world.addSpaceship(ss);
			spaceships.add(ss);
		}
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
		
		//If any spaceship exploded
		else if (evt.getPropertyName().equals(Spaceship.Message.SPACESHIP_DIE.toString())) {
			spaceships.remove((Spaceship)evt.getSource());
			
			//If round is active with one ship, start new round
			if (state.getState().equals(Roundstate.state.ACTIVE) &&
				spaceships.size() == 1) {
				start();
			}			
		}
		
		//Is user lost his spaceship...
		else if (evt.getPropertyName().equals(User.message.LOST_SPACESHIP.toString())) {
			
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
				spaceships.add(ss);
			}
		}
	}
}