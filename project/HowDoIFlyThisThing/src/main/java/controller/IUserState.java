package controller;

/**
 *
 * @author Martin Nilsson
 */
public interface IUserState {
	
	public void mainHold(boolean held);
	
	public void leftHold(boolean held);
	
	public void rightHold(boolean held);
	
	public void fireHold(boolean held);
	
}
