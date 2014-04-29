package com.whathappensingandalf.howdoiflythisthing;

import org.lwjgl.input.Keyboard;
/**
 *
 * @author Martin Nilsson
 */
public class Keybindings {
	
	private int left; 
	private int main; 
	private int right; 
	private int fire;
	
	public Keybindings(){
		this.left=Keyboard.KEY_A;
		this.main=Keyboard.KEY_W;
		this.right=Keyboard.KEY_D;
		this.fire=Keyboard.KEY_SPACE;
	}
	public Keybindings(int left, int main, int right, int fire){
		this.left=Keyboard.KEY_A;
		this.main=Keyboard.KEY_W;
		this.right=Keyboard.KEY_D;
		this.fire=Keyboard.KEY_SPACE;
	}
	public void reset(){
		this.left=Keyboard.KEY_A;
		this.main=Keyboard.KEY_W;
		this.right=Keyboard.KEY_D;
		this.fire=Keyboard.KEY_SPACE;
	}
	public void setLeft(int key){
		this.left=key;
	}
	public void setMain(int key){
		this.main=key;
	}
	public void setRight(int key){
		this.right=key;
	}
	public void setFire(int key){
		this.fire=key;
	}
	
}
