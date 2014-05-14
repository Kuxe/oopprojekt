package networkpackets;


public class HudNetworkPacket implements NetworkPacket {
	
public int hull;
public int shield;
	
	public HudNetworkPacket(int hull, int shield) {
		this.hull= hull;
		this.shield= shield;
	}
	
	public HudNetworkPacket() {
		//required for kryonet
	}

	@Override
	public Type getType() {
		return NetworkPacket.Type.HUD;
	}
}