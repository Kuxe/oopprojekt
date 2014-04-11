package controller;

import com.whathappensingandalf.howdoiflythisthing.Spaceship;
import java.util.HashSet;
import java.util.Set;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 *
 * @author Martin Nilsson
 */
public class User implements KeyListener{
	
	private IUserState state;
	private Spaceship spaceship;
	private Set<Integer> listOfPressedKeys;
	private Set<Integer> listOfReleasedKeys;
	boolean aHold, wHold, dHold, spaceHold;
	
	public User(){
		listOfPressedKeys = new HashSet();
		listOfReleasedKeys = new HashSet();
	}
	
	public void setSpaceship(Spaceship spaceship){
		this.spaceship=spaceship;
	}

	public void keyPressed(int key, char c) {
		listOfPressedKeys.add(key);
	}

	public void keyReleased(int key, char c) {
		listOfReleasedKeys.add(key);
	}

	public void setInput(Input input) {
		// TODO Auto-generated method stub
	}

	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	public void inputEnded() {
		// TODO Auto-generated method stub
	}

	public void inputStarted() {
		// TODO Auto-generated method stub
	}
	
}
