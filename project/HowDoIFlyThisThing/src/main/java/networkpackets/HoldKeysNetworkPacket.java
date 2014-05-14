package networkpackets;

import java.util.Set;

public class HoldKeysNetworkPacket implements NetworkPacket{
	public Set<Integer> listOfHoldKeys;
	public HoldKeysNetworkPacket(Set<Integer> listOfHoldKeys) {
		this.listOfHoldKeys = listOfHoldKeys;
	}
	public HoldKeysNetworkPacket() {
		
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.HOLD_KEYS;
	}
}
