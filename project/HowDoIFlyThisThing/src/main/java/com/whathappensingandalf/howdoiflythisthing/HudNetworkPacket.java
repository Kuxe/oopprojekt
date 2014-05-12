package com.whathappensingandalf.howdoiflythisthing;


public class HudNetworkPacket {
	
public int hull;
public int shield;
	
	public HudNetworkPacket(int hull, int shield) {
		this.hull= hull;
		this.shield= shield;
	}
	
	public HudNetworkPacket() {
		//required for kryonet
	}
}