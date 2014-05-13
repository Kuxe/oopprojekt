package com.whathappensingandalf.howdoiflythisthing;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

public class Explosion implements IGameObject, IDrawable {

	Point2f position;
	
	public Explosion(Point2f position){
		this.position=position;
	}
	
	@Override
	public Point2f getPosition() {
		return new Point2f(position);
	}

	@Override
	public Vector2f getDirection() {
		return new Vector2f(0,0);
	}

	@Override
	public int getHeight() {
		return 240;
	}

	@Override
	public int getWidth() {
		return 360;
	}

	@Override
	public Collection<? extends DrawableData> getCollectionDrawables() {
		Set<DrawableData> temp = new HashSet<DrawableData>();
		temp.add(new DrawableData(
				this.getPosition(),
				this.getWidth(),
				this.getHeight(),
				this.getDirection(),
				this.getType()));
		return temp;
	}

	@Override
	public String getType() {
		return IGameObject.type.EXPLOSION.toString();
	}
	
	public Explosion clone(){
		return new Explosion(this.getPosition());
	}

}
