package com.whathappensingandalf.howdoiflythisthing;

public class WorldBorderNetworkPacket {
	public int width, height;
	public WorldBorderNetworkPacket(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public WorldBorderNetworkPacket() {
		//Required by KryoNet
	}
}
