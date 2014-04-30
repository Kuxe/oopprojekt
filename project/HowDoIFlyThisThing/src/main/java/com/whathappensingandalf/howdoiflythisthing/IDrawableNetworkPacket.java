package com.whathappensingandalf.howdoiflythisthing;

import java.util.Map;

public class IDrawableNetworkPacket {
	public Map<Object, IDrawable> drawables;
	public IDrawableNetworkPacket(Map<Object, IDrawable> drawables) {
		this.drawables = drawables;
	}
}
