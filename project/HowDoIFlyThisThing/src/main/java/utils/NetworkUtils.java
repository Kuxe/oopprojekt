package utils;

import com.esotericsoftware.kryo.Kryo;
import com.whathappensingandalf.howdoiflythisthing.CountdownNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.DrawableData;
import com.whathappensingandalf.howdoiflythisthing.DrawableDataNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.HoldKeysNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.HudNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.Keybindings;
import com.whathappensingandalf.howdoiflythisthing.KeybindingsNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.ModelStatusNetworkPacket;
import com.whathappensingandalf.howdoiflythisthing.SoundNetworkPacket;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Classed used for network utilities
 */
public class NetworkUtils {
	
	private static boolean firstTime = true;
	
	/**
	 * Registers classes in an uniform order for param <i>kryo</i>
	 * This encapsulates registering of classes to a single place
	 * instead of having it around client and server code, having
	 * to maintain them both.
	 * @param kryo
	 */
	public static void registerClasses(Kryo kryo) {
		if(firstTime) {
			kryo.register(HoldKeysNetworkPacket.class);
			kryo.register(DrawableDataNetworkPacket.class);
			kryo.register(DrawableData.class);
			kryo.register(java.util.HashSet.class);
			kryo.register(javax.vecmath.Vector2f.class);
			kryo.register(javax.vecmath.Point2f.class);
			kryo.register(SoundNetworkPacket.class);
			kryo.register(KeybindingsNetworkPacket.class);
			kryo.register(Keybindings.class);
			kryo.register(CountdownNetworkPacket.class);
			kryo.register(ModelStatusNetworkPacket.class);
			kryo.register(HudNetworkPacket.class);
			firstTime = false;
		}
	}
}
