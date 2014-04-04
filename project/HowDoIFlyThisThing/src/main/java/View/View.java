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
	private Image spaceship;
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

	@Override
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
//			g.translate(comp.getPossition().x, comp.getDirection().y);
//			g.drawImage(spaceship, 100, 150, Color.blue);
			g.drawImage(spaceship, comp.getPossition().x, comp.getPossition().y);
		}
//		System.out.println("-------------------------------------------------");
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		try {
			spaceship=new Image("resources/Spaceship.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
