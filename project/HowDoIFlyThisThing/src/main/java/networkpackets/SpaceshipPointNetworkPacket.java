package networkpackets;

import javax.vecmath.Point2f;

public class SpaceshipPointNetworkPacket implements NetworkPacket {
	public Point2f point;
	public SpaceshipPointNetworkPacket(Point2f point) {
		this.point = point;
	}
	SpaceshipPointNetworkPacket() {
		//For kryonet
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.SPACESHIP_POINT;
	}
}
