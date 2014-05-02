package com.whathappensingandalf.howdoiflythisthing;

import java.util.HashSet;
import java.util.Map;

public class IDrawableNetworkPacket {
	public HashSet<IDrawable> drawables;
	public IDrawableNetworkPacket(HashSet<IDrawable> drawables) {
		this.drawables = drawables;
	}
}
