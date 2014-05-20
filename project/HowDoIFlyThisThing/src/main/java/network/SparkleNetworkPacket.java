package network;

import javax.vecmath.Point2f;

public class SparkleNetworkPacket implements NetworkPacket {
	public Point2f position;
	
	public  SparkleNetworkPacket(Point2f position){
		this.position=position;
	}
	
	public  SparkleNetworkPacket(){
		//For kryonet
	}

	@Override
	public Type getType() {
		return NetworkPacket.Type.SPARKLE;
	}
}
