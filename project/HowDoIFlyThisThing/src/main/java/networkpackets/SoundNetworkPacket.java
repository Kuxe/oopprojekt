package networkpackets;

import java.util.Set;

public class SoundNetworkPacket implements NetworkPacket {
	
	public Set<String> sounds;
	
	public SoundNetworkPacket(Set<String> sounds) {
		this.sounds = sounds;
	}
	
	public SoundNetworkPacket() {
		//required for kryonet
	}

	@Override
	public Type getType() {
		return NetworkPacket.Type.SOUND;
	}
}