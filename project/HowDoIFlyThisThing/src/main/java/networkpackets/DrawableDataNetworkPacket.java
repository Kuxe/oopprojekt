package networkpackets;

import java.util.HashSet;
import java.util.Set;

import com.whathappensingandalf.howdoiflythisthing.DrawableData;

public class DrawableDataNetworkPacket implements NetworkPacket{
	public DrawableData drawable;
	public DrawableDataNetworkPacket(DrawableData drawable) {
		this.drawable = drawable;
	}
	public DrawableDataNetworkPacket() {
		
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.DRAWABLE_DATA;
	}
}
