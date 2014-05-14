package networkpackets;

import java.util.HashSet;
import java.util.Set;

import com.whathappensingandalf.howdoiflythisthing.DrawableData;

public class DrawableDataNetworkPacket implements NetworkPacket{
	public Set<DrawableData> drawables;
	public DrawableDataNetworkPacket(Set<DrawableData> drawables) {
		this.drawables = drawables;
	}
	public DrawableDataNetworkPacket() {
		
	}
	@Override
	public Type getType() {
		return NetworkPacket.Type.DRAWABLE_DATA;
	}
}
