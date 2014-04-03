package View;

import java.util.Collections;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.whathappensingandalf.howdoiflythisthing.IDrawable;

public class GameWindow extends BasicGame{
	
	
	private List<IDrawable> renderObjects;
	
	public GameWindow(String title) {
		super(title);
	}
	
	public synchronized void setRenderObjects(List<IDrawable> list){
		renderObjects=Collections.synchronizedList(list);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		// System.out.println("render");
		for(int i=0;i<renderObjects.size();i++){
			System.out.println("Name: "+renderObjects.get(i).getIdentifyer());
			System.out.println("Pos X: "+renderObjects.get(i).getPossition().x);
			System.out.println("Pos Y: "+renderObjects.get(i).getPossition().y);
			System.out.println("Dir X: "+renderObjects.get(i).getDirection().x);
			System.out.println("Dir X: "+renderObjects.get(i).getDirection().y);
		}
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
