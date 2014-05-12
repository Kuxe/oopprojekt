package com.whathappensingandalf.howdoiflythisthing;

public class Shield {
	
	private int shield;
	private int maxShield;
	
	public Shield(){
		this(3);
	}
	
	public Shield(int shield){
		this.shield=shield;
		this.maxShield=shield;
	}
	
	/**
	 * 
	 * @param damage
	 * @return the remaining damage after damageing shield
	 */
	public int hurt(int damage){
		if(damage<shield){
			shield=shield-damage;
			return 0;
		}else{
			damage=damage-shield;
			shield=0;
			return damage;	
		}
	}
	
	public void recharge(){
		if(shield<maxShield){
			shield++;
		}
	}
	
	public void setShield(int s){
		shield=s;
		if(shield>maxShield){
			shield=maxShield;
		}
	}

	public int getShield(){
		return shield;
	}
}