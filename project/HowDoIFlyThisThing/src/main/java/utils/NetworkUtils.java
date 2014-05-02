package utils;

import com.esotericsoftware.kryo.Kryo;
import com.whathappensingandalf.howdoiflythisthing.HoldKeysNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.IDrawableNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.Spaceship;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Classed used for network utilities
 */
public class NetworkUtils {
	
	/**
	 * Registers classes in an uniform order for param <i>kryo</i>
	 * This encapsulates registering of classes to a single place
	 * instead of having it around client and server code, having
	 * to maintain them both.
	 * @param kryo
	 */
	public static void registerClasses(Kryo kryo) {
		kryo.register(HoldKeysNetworkPacket.class);
		kryo.register(IDrawableNetworkPacket.class);
		kryo.register(java.util.HashMap.class);
		kryo.register(Object.class);
		kryo.register(Spaceship.class);
	}
}
