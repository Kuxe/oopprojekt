package utils;

import javax.vecmath.*;

public class VecmathUtils {
	public static void rotateVector(Vector2f vector, double degree) {
		GVector temp = new GVector(vector);
		temp.mul(new GMatrix(2, 2, new double[]{Math.cos(degree), -Math.sin(degree), Math.sin(degree), Math.cos(degree)}), temp);
		vector.set((float)temp.getElement(0), (float)temp.getElement(1));
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
		if(length != 0.0f) {
			vector.normalize();
			vector.set((float)Math.cos(angle+Math.acos(vector.x)), (float)Math.sin(angle+Math.acos(vector.x)));
			vector.scale(length);
		}
	}
	
	/**
	 * Rotates vector to have same angle as direction
	 * @param vec1
	 * @param vec2
	 */
	public static void setAngleFromVector(Vector2f vector, Vector2f direction) {
		double length = Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
		Vector2f temp = new Vector2f(direction.x, direction.y);
		temp.normalize();
		vector.x = (float) (temp.x * length);
		vector.y = (float) (temp.y * length);
	}
	
	/**
	 * Scales the vector to have param <i>length</i> length 
	 * @param vector
	 * @param length
	 */
	public static void setLength(Vector2f vector, float length) {
		if(vector.length() != 0.0f && length != 0.0f) {
			float scaleFactor = length / vector.length();
			vector.scale(scaleFactor);
		}
	}
}
