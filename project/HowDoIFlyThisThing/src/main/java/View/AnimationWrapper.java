package View;

import javax.vecmath.Point2f;

import org.newdawn.slick.Animation;

public class AnimationWrapper{
	
	private Point2f position;
	private Animation animation;
	
	public AnimationWrapper(Point2f position,Animation animation){
		this.position=position;
		this.animation=animation;
	}
	
	public Point2f getPosition(){
		return new Point2f(position);
	}
	
	public Animation getAnimation(){
		return this.animation;
	}

}
