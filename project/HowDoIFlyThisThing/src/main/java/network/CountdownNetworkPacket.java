package network;

public class CountdownNetworkPacket implements NetworkPacket {
	public long countdown;
	public CountdownNetworkPacket(long countdown) {
		this.countdown = countdown;
	}
	public CountdownNetworkPacket() {
		//For kryonet
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.COUNTDOWN;
	}
}
