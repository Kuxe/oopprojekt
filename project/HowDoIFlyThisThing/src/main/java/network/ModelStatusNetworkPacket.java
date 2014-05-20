package network;

public class ModelStatusNetworkPacket implements NetworkPacket {
	public String status;
	public ModelStatusNetworkPacket(String status) {
		this.status = status;
	}
	public ModelStatusNetworkPacket() {
		//For kryonet
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.MODEL_STATUS;
	}
}
