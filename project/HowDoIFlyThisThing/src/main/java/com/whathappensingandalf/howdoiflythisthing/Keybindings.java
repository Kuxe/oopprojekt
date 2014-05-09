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
		this.left	=	Keyboard.KEY_A;
		this.main	=	Keyboard.KEY_W;
		this.right	=	Keyboard.KEY_D;
		this.fire	=	Keyboard.KEY_SPACE;
	}
	public Keybindings(int left, int main, int right, int fire){
		this.left	=	left;
		this.main	=	main;
		this.right	=	right;
		this.fire	=	fire;
	}
	public Keybindings(Keybindings keybindings){
		this.left	=	keybindings.left;
		this.main	=	keybindings.main;
		this.right	=	keybindings.right;
		this.fire	=	keybindings.fire;
	}
	public void reset(){
		this.left	=	Keyboard.KEY_A;
		this.main	=	Keyboard.KEY_W;
		this.right	=	Keyboard.KEY_D;
		this.fire	=	Keyboard.KEY_SPACE;
	}
	public void setLeft(int key){
		this.left	=	key;
	}
	public void setMain(int key){
		this.main	=	key;
	}
	public void setRight(int key){
		this.right	=	key;
	}
	public void setFire(int key){
		this.fire	=	key;
	}
	public int getLeft(){
		return this.left;
	}
	public int getMain(){
		return this.main;
	}
	public int getRight(){
		return this.right;
	}
	public int getFire(){
		return this.fire;
	}
}
