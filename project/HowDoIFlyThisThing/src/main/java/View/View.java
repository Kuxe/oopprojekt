package View;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.vecmath.Vector2f;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.whathappensingandalf.howdoiflythisthing.*;

public class View extends BasicGame implements ApplicationListener{
	
	private AppGameContainer container;
//	private GameWindow game;
	
	private Map<Object,IDrawable> renderObjects;
	private Image spaceship,shott,asteroid;
	Float f=0f;
	
	public View(String title){
		super(title);
		
		renderObjects=new HashMap<Object,IDrawable>();
		try{
			container=new AppGameContainer(this);
			container.setDisplayMode(640, 480, false);
			container.setTargetFrameRate(60);
		}catch(SlickException ex){
			Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public void start(){
		try {
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void setRenderObjects(Map<Object,IDrawable> list){
		renderObjects=Collections.synchronizedMap(list);
	}
	public void render(GameContainer arg0, Graphics g) throws SlickException {
//		g.translate(100, 100);
//		g.rotate(25, 25, f);
//		f++;
		for(IDrawable comp: renderObjects.values()){
			
//			System.out.println();
//			System.out.println("Name: "+comp.getType());
//			System.out.println("Pos X: "+comp.getPossition().x);
//			System.out.println("Pos Y: "+comp.getPossition().y);
//			System.out.println("Dir X: "+comp.getDirection().x);
//			System.out.println("Dir X: "+comp.getDirection().y);
			float tmpX,tmpY;
			Image tmpImg;
			if(comp.getType()=="SPACESHIP"){
				tmpImg=spaceship.copy();
			}else if(comp.getType()=="ASTEROID"){
				tmpImg=asteroid.copy();
			}else{
				tmpImg=shott.copy();
			}
//			System.out.println(comp.getWidth());
//			System.out.println(comp.getDirection().x);
			tmpX=comp.getPosition().x-comp.getWidth()/2;
			tmpY=comp.getPosition().y-comp.getHeight()/2;
			tmpImg.rotate(claculateRotation(comp.getDirection()));
			g.drawImage(tmpImg, tmpX, tmpY);
//			arg0.
		}
//		System.out.println("-------------------------------------------------");
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		System.out.println(this.claculateRotation(new Vector2f(0,1)));
		System.out.println(this.claculateRotation(new Vector2f(0,-1)));
		try {
			spaceship=new Image("resources/Spaceship.png");
			shott=new Image("resources/Shott.png");
			asteroid=new Image("resources/Asteroid.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		for(IDrawable comp: renderObjects.values()){
			
		}
		
	}
	
	private float claculateRotation(Vector2f vector){
		Vector2f ex=new Vector2f(1,0);
		Vector2f ey=new Vector2f(0,-1);
		float f1,f2;
		f2=ex.angle(vector);
		f1=ey.angle(vector);
		if(f2<Math.PI/2){
			return this.radinanToDegrees(f1);
		}else{
			return this.radinanToDegrees((float)(2*Math.PI-f1));
		}
//		return radinanToDegrees(vector.angle(ex));
	}
	
	private float radinanToDegrees(float angle){
		return angle*57.2957795f;
	}

}
