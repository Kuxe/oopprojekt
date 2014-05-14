package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Mathias
 *
 */
public class WorldBorder {
	
	private int worldHeight;
	private int worldWidth;
	
	public WorldBorder(int width, int height){
		worldWidth=width;
		worldHeight=height;
	}
	
	public WorldBorder() {
		//Required by KryoNet
	}
	
	public int getWorldWidth(){
		return worldWidth;
	}
	
	public int getWorldHeight(){
		return worldHeight;
	}
}
