package network;

public class WorldBorderNetworkPacket implements NetworkPacket {
	public int width, height;
	public WorldBorderNetworkPacket(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public WorldBorderNetworkPacket() {
		//Required by KryoNet
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.WORLD_BORDER;
	}
}
