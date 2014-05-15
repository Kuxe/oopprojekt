package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 *
 * @author Martin Nilsson
 */

public class SpectatorState implements IUserState{

	private Point2f camera;
	private Vector2f up;
	private Vector2f down;
	private Vector2f left;
	private Vector2f right;

	public SpectatorState(){
		camera	=	new Point2f(1,1);
		up		=	new Vector2f(0,-0.001f);
		down	=	new Vector2f(0,0.001f);
		left	=	new Vector2f(-0.001f,0);
		right	=	new Vector2f(0.001f,0);
	}
	
	public void mainHold(boolean held) {
		if(held){
			camera.add(up);
		}
	}

	public void leftHold(boolean held) {
		if(held){
			camera.add(left);
		}
	}

	public void rightHold(boolean held) {
		if(held){
			camera.add(right);
		}
	}

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
	public Point2f getSpaceshipPosition() {
		//System.out.println(camera);
		return camera;
		//return new Point2f(0,0);
	}

	public Spaceship getSpaceship() {
		//Has no spaceship
		return null;
	}

	@Override
	public void suicide() {
		//Ship already dead
	}

	@Override
	public state getState() {
		return IUserState.state.SPECTATOR_STATE;
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