package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActiveRoundTest {
	
	ActiveRound ar= new ActiveRound();
	User user= new User();
	Set<User> userSet= new HashSet<User>();
	Round round= new Round();
	
	@Before
	public void setUp() throws Exception{
		userSet.clear();
	}

	@Test
	public void testAddUser() {
		ar.addUser(user, userSet);
		assertTrue(userSet.contains(user));
	}

	@Test
	public void testRemoveUser() {
		User user2= new User();
		userSet.add(user);
		userSet.add(user2);
		
		ar.removeUser(user, userSet, round);
		round.increaseUsersAlive();
		round.increaseUsersAlive();
		int nbrOfUsersWhenSpectator= round.getUsersAlive();	//Shoulden't have been changed
		user2.setSpaceship(new Spaceship(new Point2f(0, 0), new Vector2f(0, 0), 0, 0));	//needed to change to playerState
		ar.removeUser(user2, userSet, round);
		int nbrOfUsersWhenPlayer= round.getUsersAlive();	//Should be only one left

		assertTrue(userSet.isEmpty());
		assertTrue(nbrOfUsersWhenSpectator == 2);
		assertTrue(nbrOfUsersWhenPlayer == 1);
	}

	@Test
	public void testGetState() {
		assertTrue(ar.getState().toString().equals("ACTIVE"));
	}

	@Test
	public void testAddListener() {
//		Can't be tested
	}

	@Test
	public void testGetStatus() {
		assertTrue(ar.getStatus().equals("Fight!"));
	}
}