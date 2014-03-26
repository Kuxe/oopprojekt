package com.whathappensingandalf.howdoiflythisthing;

public interface IMovable {
	
	//getters
	public Vector getSpeed();
	public Vector getAcc();
	public Point getPos();
	
	//setters
	public void setSpeed();
	public void setAcc();
	public void setPos();
}