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
		hull=hull-damage;
		if(hull<1){
			return true;
		}else{
			return false;
		}
	}
	
	public void setHull(int h){
		hull=h;
		if(hull<maxHull){
			hull=maxHull;
		}
	}
	
}
