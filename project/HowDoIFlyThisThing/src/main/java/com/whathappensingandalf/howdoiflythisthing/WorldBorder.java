package com.whathappensingandalf.howdoiflythisthing;

/**
 * 
 * @author Mathias
 *
 */
public class WorldBorder {
	
	private int worldHeight;
	private int worldWidth;
	private int maxWorldHeight;
	private int maxWorldWidth;
	
	public WorldBorder(int height, int width){
		worldHeight=height;
		worldWidth=width;
		maxWorldHeight=worldHeight;
		maxWorldWidth=worldWidth;
	}
	
	public int getWorldHeight(){
		return worldHeight;
	}
	
	public int getWorldWidth(){
		return this.worldWidth;
	}
}
