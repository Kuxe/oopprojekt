package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */

public class SpectatorState implements IUserState{

	private final Point2f camera;
	private final Vector2f up;
	private final Vector2f down;
	private final Vector2f left;
	private final Vector2f right;

	public SpectatorState(){
		camera	=	new Point2f(1,1);
		up		=	new Vector2f(0,-0.01f);
		down	=	new Vector2f(0,0.01f);
		left	=	new Vector2f(-0.01f,0);
		right	=	new Vector2f(0.01f,0);
	}
	
	@Override
	public void mainHold(boolean held) {
		if(held){
			camera.add(up);
		}
	}

	@Override
	public void leftHold(boolean held) {
		if(held){
			camera.add(left);
		}
	}

	@Override
	public void rightHold(boolean held) {
		if(held){
			camera.add(right);
		}
	}

	@Override
	public void fireHold(boolean held) {
		if(held){
			camera.add(down);
		}
	}

	public void setCameraPoint(Point2f position){
		this.camera.set(position);
	}
	/**
	 * 
	 * @return A camera possition as the user does not have a spaceship.
	 */
	@Override
	public Point2f getSpaceshipPosition() {
		//System.out.println(camera);
		return camera;
		//return new Point2f(0,0);
	}

	@Override
	public Spaceship getSpaceship() {
		//Has no spaceship
		return null;
	}

	@Override
	public void suicide() {
		//Ship already dead
	}

	@Override
	public State getState() {
		return IUserState.State.SPECTATOR_STATE;
	}

	@Override
	public int getHull() {
		return 0;
	}

	@Override
	public int getShield() {
		return 0;
	}
}