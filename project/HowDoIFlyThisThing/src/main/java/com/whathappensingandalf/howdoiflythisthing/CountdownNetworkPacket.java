package com.whathappensingandalf.howdoiflythisthing;

public class CountdownNetworkPacket {
	public long countdown;
	public CountdownNetworkPacket(long countdown) {
		this.countdown = countdown;
	}
	public CountdownNetworkPacket() {
		//For kryonet
	}
}
