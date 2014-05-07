package com.whathappensingandalf.howdoiflythisthing;

import java.util.Set;

public class SoundNetworkPacket {
	public Set<String> sounds;
	public SoundNetworkPacket(Set<String> sounds) {
		this.sounds = sounds;
//		System.out.println("AND THERE WERE: " + sounds);
	}
	public SoundNetworkPacket() {
		//required for kryonet
	}
}
