package controller;

import javax.vecmath.Point2f;

/**
 *
 * @author Martin Nilsson
 */
public interface IUserState {
	
	public void mainHold(boolean held);
	
	public void leftHold(boolean held);
	
	public void rightHold(boolean held);
	
	public void fireHold(boolean held);
	
	public Point2f getSpaceshipPosition();
}
