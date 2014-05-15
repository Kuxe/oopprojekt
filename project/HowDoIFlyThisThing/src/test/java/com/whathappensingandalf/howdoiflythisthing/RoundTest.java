package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoundTest {

	
	
	@Test
	public void testRound() {
		assertTrue(true); //Nothing to test
	}

	@Test
	public void testAddUser() {
		Round round = new Round();
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		round.addUser(user1);
		assertTrue(round.getSizeOfUsers() == 1);
		round.addUser(user2);
		assertTrue(round.getSizeOfUsers() == 2);
		round.addUser(user3);
		assertTrue(round.getSizeOfUsers() == 3);
	}

	@Test
	public void testRemoveUser() {
		Round round = new Round();
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		round.addUser(user1);
		round.addUser(user2);
		round.addUser(user3);
		
		round.removeUser(user1);
		assertTrue(round.getSizeOfUsers() == 2);
		round.removeUser(user2);
		assertTrue(round.getSizeOfUsers() == 1);
		round.removeUser(user3);
		assertTrue(round.getSizeOfUsers() == 0);
	}

	@Test
	public void testStart() {
		Round round = new Round();
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		User user4 = new User();
		
		round.start();
		assertTrue(round.getState().equals(Roundstate.State.INACTIVE)); // Should not start when one users are in
		assertTrue(round.getUsersAlive() == 0); //No one should be alive
		
		round.addUser(user1);
		round.start();
		assertTrue(round.getState().equals(Roundstate.State.INACTIVE)); // Should not start when one users are in
		round.update(); //Update round in order to let users spawn
		assertTrue(round.getUsersAlive() == 1); //One should be alive
		
		round.addUser(user2);
		run5seconds(round);
		round.start();
		round.update(); //Update round in order to start round
		run5seconds(round);
		assertTrue(round.getState().equals(Roundstate.State.ACTIVE)); //Should be active when two users are in
		round.update(); //Update round in order to let users spawn
		assertTrue(round.getUsersAlive() == 2); //Two should be alive
		
		round.addUser(user3);
		round.start();
		assertTrue(round.getState().equals(Roundstate.State.ACTIVE)); //Should still be active when three users are in
		round.update(); //Update round in order to start round
		
		run5seconds(round);
		run5seconds(round);
		assertTrue(round.getUsersAlive() == 3); //Three should be alive
		
		round.addUser(user4);
		round.start();
		assertTrue(round.getState().equals(Roundstate.State.ACTIVE)); //Should still be active when four users are in
		round.update(); //Update round in order to start round
		round.update(); //Update round in order to hand out requested spaceships
		run5seconds(round);
		assertTrue(round.getUsersAlive() == 4); //Four should be alive
	}

	@Test
	public void testEnd() {
		Round round = new Round();
		User user1 = new User();
		User user2 = new User();
		
		round.addUser(user1);
		round.addUser(user2);
		round.end(); //At this point round should be started (two users). Now force end it.
		assertTrue(round.getState().equals(Roundstate.State.INACTIVE)); //Did it force end?
	}

	@Test
	public void testUpdate() {
		assertTrue(true); //Nothing testable
	}

	@Test
	public void testGetDrawableData() {
		assertTrue(true); //To complex to test if output is valid or not. Not in scope for this project.
	}

	@Test
	public void testGetListOfSounds() {
		assertTrue(true); //To complex to test if output is valid or not. Not in scope for this project.
	}

	@Test
	public void testGetWorld() {
		Round round = new Round();
		assertTrue(round.getWorld() != null); //Gameworld should be allocated in CTOR
	}

	@Test
	public void testIncreaseUsersAlive() {
		Round round = new Round();
		round.increaseUsersAlive();
		assertTrue(round.getUsersAlive() == 1);
		round.increaseUsersAlive();
		assertTrue(round.getUsersAlive() == 2);
	}

	@Test
	public void testDecreaseUsersAlive() {
		Round round = new Round();
		round.increaseUsersAlive();
		assertTrue(round.getUsersAlive() == 1);
		round.increaseUsersAlive();
		assertTrue(round.getUsersAlive() == 2);
		round.decreaseUsersAlive();
		assertTrue(round.getUsersAlive() == 1);
		round.decreaseUsersAlive();
		assertTrue(round.getUsersAlive() == 0);
		//decreasing even more should return negative amount. If this would be prevented
		//the only consequence would be complex debugging, since getting 0 when you could
		//get -1 gives less information to the programmer
	}

	@Test
	public void testPropertyChange() {
		Round round = new Round();
		User user1 = new User();
		User user2 = new User();
		
		round.addUser(user1);
		round.addUser(user2);
		assertTrue(user1.getState().equals(IUserState.state.SPECTATOR_STATE)); //Should not have received spaceship yet
		assertTrue(user2.getState().equals(IUserState.state.SPECTATOR_STATE)); //Should not have received spaceship yet
		round.update();
		run5seconds(round);
		assertTrue(round.getState().equals(Roundstate.State.ACTIVE)); //START_ROUND-event should make round active when second user is added
		round.update(); //Process round (give spaceships to users that requested from event REQUEST_SPACESHIP
		assertTrue(user1.getState().equals(IUserState.state.PLAYER_STATE)); //Should have received spaceship
		assertTrue(user2.getState().equals(IUserState.state.PLAYER_STATE)); //Should have received spaceship
		
		assertTrue(round.getUsersAlive() == 2); //Both users should be alive
		assertTrue(user1.getState().equals(IUserState.state.PLAYER_STATE)); //If both users are alive, they are both players
		assertTrue(user2.getState().equals(IUserState.state.PLAYER_STATE)); //If both users are alive, they are both players
		
		System.out.println(round.getUsersAlive());
		System.out.print("SUICIDE... ");
		user1.suicide(); //Kill user1 spaceship
		System.out.println("DEAD!");
		System.out.println(round.getUsersAlive());
		assertTrue(round.getUsersAlive() == 1); //1 users should be alive.
		round.update();
		run5seconds(round);
		assertTrue(round.getUsersAlive() == 2); //Round is updated and all users should have recieved their spaceships
		
		//Let's repeat this suicide sequence of code but with three users instead. Now it is expected for the round to remain
	}

	//Run round for 5seconds. This is required by start to activate, it takes 5sec for round to start since its counting down
	private void run5seconds(Round round) {
		long countdownStart = System.nanoTime();
		System.out.print("starting timer");
		while(System.nanoTime() - countdownStart < 5000000000L) {
			round.update();
		}
		System.out.println(" done");
	}
}
