package com.whathappensingandalf.howdoiflythisthing;

public class Hull {
	
	private int hull;
	private int maxHull;
	
	public Hull(){
		this(10);
	}
	
	public Hull(int hull){
		this.hull=hull;
		this.maxHull=hull;
	}
	
	public boolean hurt(int damage){
		hull -= damage;
        return hull < 1;
	}
	
	public void setHull(int h){
		hull=h;
		if(hull>maxHull){
			hull=maxHull;
		}
	}
	
	public void repair(int repair){
		this.setHull(this.hull+repair);
	}
	
	public int getHull(){
		return this.hull;
	}
	
}
