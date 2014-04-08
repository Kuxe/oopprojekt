package View;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Vector2f;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.whathappensingandalf.howdoiflythisthing.IDrawable;

public class GameWindow extends BasicGame{
	
	
	private List<IDrawable> renderObjects;
	private Image spaceship;
	
	public GameWindow(String title){
		super(title);
	}
	
	public synchronized void setRenderObjects(List<IDrawable> list){
		renderObjects=Collections.synchronizedList(list);
	}
	public void render(GameContainer arg0, Graphics g) throws SlickException {
//		g.drawImage(spaceship, 100, 100);
//		System.out.println("render");
//		for(int i=0;i<renderObjects.size();i++){
//			System.out.println();
//			System.out.println("Name: "+renderObjects.get(i).getIdentifyer());
//			System.out.println("Pos X: "+renderObjects.get(i).getPossition().x);
//			System.out.println("Pos Y: "+renderObjects.get(i).getPossition().y);
//			System.out.println("Dir X: "+renderObjects.get(i).getDirection().x);
//			System.out.println("Dir X: "+renderObjects.get(i).getDirection().y);
//			renderObjects.get(i).getDirection().angle(new Vector2f(0,1));
//		}
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
//		try {
//			spaceship=new Image("C:/Users/Mathias/Pictures/HDFTT/Spaceship.png");
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
