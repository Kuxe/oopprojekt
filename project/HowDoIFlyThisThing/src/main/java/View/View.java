package View;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import com.whathappensingandalf.howdoiflythisthing.*;

public class View implements ApplicationListener{
	
	private AppGameContainer container;
	private GameWindow game;
	
	public View(){
		game=new GameWindow("How do i fly this thing?");
		try{
			container=new AppGameContainer(game);
			container.setDisplayMode(640, 480, false);
			container.setTargetFrameRate(60);
			container.start();
		}catch(SlickException ex){
			Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public synchronized void setRenderObjects(List<IDrawable> list){
		game.setRenderObjects(list);
	}
	
}
