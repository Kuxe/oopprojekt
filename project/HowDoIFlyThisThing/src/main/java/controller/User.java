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

	public void keyPressed(int i, char c) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void keyReleased(int i, char c) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void setInput(Input input) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public boolean isAcceptingInput() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void inputEnded() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void inputStarted() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
