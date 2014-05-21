package network;

import javax.vecmath.Point2f;

import network.NetworkPacket.Type;

public class AsteroidDieNetworkPacket implements NetworkPacket{
	public Point2f position;
	
	public AsteroidDieNetworkPacket(Point2f position){
		this.position=position;
	}
	
	public AsteroidDieNetworkPacket(){
		//For kryonet
	}

	@Override
	public Type getType() {
		return NetworkPacket.Type.ASTEROID_EXP;
	}
}
