package networkpackets;

import javax.vecmath.Point2f;

public class ExplosionNetworkPacket implements NetworkPacket {
	public Point2f position;
	
	public ExplosionNetworkPacket(Point2f position){
		this.position=position;
	}
	
	public ExplosionNetworkPacket(){
		//For kryonet
	}

	@Override
	public Type getType() {
		return NetworkPacket.Type.EXPLOSION;
	}

}
