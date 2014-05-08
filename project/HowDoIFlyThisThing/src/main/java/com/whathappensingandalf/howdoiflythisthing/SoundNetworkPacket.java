package com.whathappensingandalf.howdoiflythisthing;

import java.util.Set;

public class SoundNetworkPacket {
	public Set<String> sounds;
	public SoundNetworkPacket(Set<String> sounds) {
		this.sounds = sounds;
		if(sounds.size() > 0)
		System.out.println("IUGFSDUGHSDFUH: " + sounds.size());
	}
	public SoundNetworkPacket() {
		//required for kryonet
	}
}