package com.whathappensingandalf.howdoiflythisthing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	private Set<User> usersRequestingShips; //Lazy add of replacement spaceships of those lost during inactive round
	private int usersAlive = 0;
	private boolean newRoundCommencing = false;
	
	private PropertyChangeSupport pcs;
	
	//For countdown
	private long countdownStart = 0;
	private long countdownLimit = 5000000000L; //5000000000ns counter, 5seconds
	private boolean permitStart = false;
		
	public Round() {
		world = new Gameworld();
		world.addPropertyChangeListener(this);
		
		pcs = new PropertyChangeSupport(this);
		
		users = new HashSet();
		usersRequestingShips = new HashSet();
		
		state = new InactiveRound(this, users);
		state.addListener(this);
	}
	
	public synchronized void addUser(User user) {
		user.addPropertyChangeListener(this);
		state.addUser(user);
	}
	
	public synchronized void removeUser(User user) {
		state.removeUser(user);
		user.removePropertyChangeListener(this);
		user.suicide();
		
		//If only one user in a round (that is, host), end round
		if(users.size() == 1) {
			end();
			for(User lastUserOnline : users) {
				
				//If host is dead, add him to round
				if(lastUserOnline.getState().equals(IUserState.state.SPECTATOR_STATE)) {
					System.out.println("adding dead host");
					lastUserOnline.requestSpaceship();
				}
			}
		}
		
		//If a user disconnected and there's currently 2 or more users connected and only one alive, start a new round
		else if(usersAlive == 1 && users.size() >= 2) {
			start();
		}
	}

	/**
	 * On starting a new round a collection of users are given
	 * These users will play the round
	 * This method calls end() beforehand, effectively forcing
	 * a restart of round if not already ended.
	 * 
	 * Note that this is a lazy start. It doesn't start directly away,
	 * but sets a state in Round to start round on next game-loop tick
	 * @param users
	 */
	public synchronized void start() {
		if(!newRoundCommencing) {
			countdownStart = System.nanoTime();
		}
		newRoundCommencing = true;
	}
	
	public void end() {
		System.out.println("END_ROUND");
		state = new InactiveRound(this, users);
		state.addListener(this);
	}

	public synchronized void update() {
		if(newRoundCommencing && System.nanoTime() - countdownStart > countdownLimit) {
			processStart();
		}
		processRequestedShips();
		world.update();
	}

	public Set<DrawableData> getDrawableData() {
		return world.getDrawableData();
	}
	public Set<String> getListOfSounds(){
		return world.getListOfSounds();
	}
	
	/**
	 * Should never be called directly, only via start() which tells Round to
	 * call this method on a specific point in program flow which prevents
	 * lots of issues with spawning players
	 */
	private void processStart() {
		//Only allow start if there's more than 2 users in server and if timer is done
		if(users.size() >= 2) {
			
			//Reset start-conditions
			newRoundCommencing = false;
			
			System.out.println("START_ROUND");
			usersAlive = 0;
			world = new Gameworld();
			world.addPropertyChangeListener(this);
			for(User user : users) {
				user.requestSpaceship();
			}
			state = new ActiveRound(this, users);
			state.addListener(this);
		}
	}
	
	/**
	 * Gives a spaceship to each user inside usersRequestingShips this tick
	 * and clears this set of users afterwards. Spaceships should never be
	 * added directly to gameWorld, use this instead.
	 */
	private void processRequestedShips() {
		for(User user : usersRequestingShips) {
			System.out.println("CREATING SPACESHIP FOR " + user);
			Spaceship ss = SpaceshipFactory.create(
					new Point2f(
							(float)Math.random() * world.getBorder().getWorldWidth(),
							(float)Math.random() * world.getBorder().getWorldHeight()),
					new Vector2f((float)Math.random(), (float)Math.random()));
			
			user.setSpaceship(ss);
			world.addSpaceship(ss);
			increaseUsersAlive();
		}
		usersRequestingShips.clear();
	}
	
	public Gameworld getWorld() {
		return world;
	}
	
	public void increaseUsersAlive() {
		usersAlive += 1;
	}
	
	public void decreaseUsersAlive() {
		usersAlive -= 1;
	}
	
	public int getUsersAlive() {
		return usersAlive;
	}
	
	public int getSizeOfUsers() {
		return users.size();
	}
	
	public Roundstate.state getState() {
		return state.getState();
	}
	
	/**
	 * 
	 * @return countdown, if 5seconds left for round to begin, returns 5. If 4s left, return 4 etc.
	 */
	public long getCountdown() {
		if(newRoundCommencing) {
			return countdownLimit - (System.nanoTime() - countdownStart);
		}
		return -1;
	}
	
	public String getModelStatus() {
		if(newRoundCommencing) {
			return "Round commencing in: ";
		}
		return state.getStatus();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName());
		if(evt.getPropertyName().equals(User.message.REQUEST_SPACESHIP.toString())) {
			usersRequestingShips.add((User)evt.getSource());
		}
		else if(evt.getPropertyName().equals(InactiveRound.message.START_ROUND.toString())) {
			start();
		}
		
		//Is user lost his spaceship...
		else if (evt.getPropertyName().equals(User.message.LOST_SPACESHIP.toString())) {
			
			//One user died...
			decreaseUsersAlive();
			//If last man standing and round is active, start new round
			if(usersAlive == 1 && state.getState().equals(Roundstate.state.ACTIVE)) {
				start();
			}
			
			//If round is inactive, give a new ship to user
			if (state.getState().equals(Roundstate.state.INACTIVE)) {
				((User)evt.getSource()).requestSpaceship();
			}
		}
		else if(evt.getPropertyName().equals(Gameworld.Message.EXPLOSION.toString())){
			System.out.println("**RoundExlosion**");
			pcs.firePropertyChange(evt);
		}
	}
}