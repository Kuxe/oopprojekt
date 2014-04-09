/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.whathappensingandalf.howdoiflythisthing;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 *
 * @author Martin
 */
public class User implements KeyListener{

	public void keyPressed(int i, char c) {
		System.out.println("Pressed");
	}

	public void keyReleased(int i, char c) {
		System.out.println("Released");
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
