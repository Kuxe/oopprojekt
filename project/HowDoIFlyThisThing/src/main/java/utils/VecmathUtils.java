package utils;

import javax.vecmath.*;

public class VecmathUtils {
	public static Vector2f rotateVector(Vector2f vector, double degree) {
		GVector temp = new GVector(vector);
		temp.mul(new GMatrix(2, 2, new double[]{Math.cos(degree), -Math.sin(degree), Math.sin(degree), Math.cos(degree)}), temp);
		return new Vector2f((float)temp.getElement(0), (float)temp.getElement(1));
	}
	
	public static void setVectorAngle(Vector2f vector, float angle) {
		float length = vector.length();
		if(length != 0.0f) {
			vector.set((float)Math.cos(angle), (float)Math.sin(angle));
			vector.scale(length);
		}
	}
	public static void rotateVectorV2(Vector2f vector, float angle){
		float length = vector.length();
		vector.normalize();
		vector.set((float)Math.cos(angle+Math.acos(vector.x)), (float)Math.sin(angle+Math.acos(vector.x)));
		vector.scale(length);
	}
	
	/**
	 * Rotates vector to have same angle as direction
	 * @param vec1
	 * @param vec2
	 */
	public static void setAngleFromVector(Vector2f vector, Vector2f direction) {
		rotateVectorV2(vector, vector.angle(direction));
	}
}
