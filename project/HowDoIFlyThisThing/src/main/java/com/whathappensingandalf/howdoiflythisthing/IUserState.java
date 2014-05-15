package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;

/**
 *
 * @author Martin Nilsson
 */
public interface IUserState {
	
	public enum State {
		PLAYER_STATE,
		SPECTATOR_STATE
	}
	
	public State getState();
	
	public void mainHold(boolean held);
	
	public void leftHold(boolean held);
	
	public void rightHold(boolean held);
	
	public void fireHold(boolean held);
	
	public Point2f getSpaceshipPosition();
	
	public Spaceship getSpaceship();
	
	public void suicide();
	
	public int getHull();
	
	public int getShield();
}