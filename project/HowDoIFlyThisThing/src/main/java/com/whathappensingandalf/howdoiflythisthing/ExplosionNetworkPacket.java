package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;

public class ExplosionNetworkPacket {
	public Point2f position;
	
	public ExplosionNetworkPacket(Point2f position){
		this.position=position;
	}
	
	public ExplosionNetworkPacket(){
		//For kryonet
	}

}
