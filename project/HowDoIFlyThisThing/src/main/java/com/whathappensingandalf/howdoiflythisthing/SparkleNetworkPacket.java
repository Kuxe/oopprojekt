package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;

public class SparkleNetworkPacket {
	public Point2f position;
	
	public  SparkleNetworkPacket(Point2f position){
		this.position=position;
	}
	
	public  SparkleNetworkPacket(){
		//For kryonet
	}
}
