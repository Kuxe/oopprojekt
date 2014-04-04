package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.*;

public class VecmathUtils {
	public static Vector2f rotateVector(Vector2f vector, double degree) {
		GVector temp = new GVector(vector);
		temp.mul(new GMatrix(2, 2, new double[]{Math.cos(degree), -Math.sin(degree), Math.sin(degree), Math.cos(degree)}), temp);
		return new Vector2f((float)temp.getElement(0), (float)temp.getElement(1));
	}
	
	public static void rotateVectorV2(Vector2f vector, float angle) {
		float length = vector.length();
		vector.set((float)Math.cos(angle), (float)Math.sin(angle));
		vector.scale(length);
	}
}
