package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorldBorderTest {

	@Test
	public void testWorldBorder() {
		WorldBorder border=new WorldBorder(10,20);
		assertTrue(border.getWorldHeight()==20 && border.getWorldWidth()==10);
	}

}
