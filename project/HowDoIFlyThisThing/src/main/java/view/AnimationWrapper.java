package view;

import javax.vecmath.Point2f;

import org.newdawn.slick.Animation;
/**
 * Wrapperclass for all information needed to paint an animation.
 * @author Mathias
 *
 */
public class AnimationWrapper{
	
	private Point2f position;
	private Animation animation;
	private int width,height;
	
	public AnimationWrapper(Point2f position,Animation animation, int width, int height){
		this.position=position;
		this.animation=animation;
		this.width=width;
		this.height=height;
	}
	
	public Point2f getPosition(){
		return new Point2f(position);
	}
	
	public Animation getAnimation(){
		return this.animation;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}

}
