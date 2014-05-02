package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * Data required data for rendering an gameobject
 */
public class DrawableData {
	
	private Point2f position;
	private int height;
	private int width;
	private Vector2f direction;
	private String type;
	
	public DrawableData(Point2f position,
						int width,
						int height,
						Vector2f direction,
						String type) {
		this.position = new Point2f(position);
		this.height = height;
		this.width = width;
		this.direction = new Vector2f(direction);
		this.type = type;
	}
	
	public DrawableData() {
		
	}
	
	public Point2f getPosition() {
		return position;
	}
	
	public Vector2f getDirection() {
		return direction;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getType() {
		return type;
	}
}
