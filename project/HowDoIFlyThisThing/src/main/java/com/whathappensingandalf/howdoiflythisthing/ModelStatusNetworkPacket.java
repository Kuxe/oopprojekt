package com.whathappensingandalf.howdoiflythisthing;

public class ModelStatusNetworkPacket {
	public String status;
	public ModelStatusNetworkPacket(String status) {
		this.status = status;
	}
	public ModelStatusNetworkPacket() {
		//For kryonet
	}
}
