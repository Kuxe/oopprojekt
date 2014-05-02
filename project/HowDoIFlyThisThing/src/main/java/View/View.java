package View;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import com.whathappensingandalf.howdoiflythisthing.IDrawable;

public class View extends BasicGame implements ApplicationListener{
	
	private AppGameContainer container;
//	private GameWindow game;
	
	private Map<Object,IDrawable> renderObjects;
	private SpriteSheet spaceship,shott,asteroid;
	private Image background;
	private Color colorFilter;
	private int backgroundWidth, backgroundHeight;
	private Float f=0f;
	
	private Point2f camera;
	private final int windowWidth = 800;
	private final int windowHeight = 600;
	
	private PropertyChangeSupport pcs;
	
	public static enum message {
		VIEW_CLOSE
	}
	
	public View(String title){
		super(title);
		renderObjects=new HashMap<Object,IDrawable>();
		try{
			container=new AppGameContainer(this);
			container.setDisplayMode(windowWidth, windowHeight, false);
			container.setTargetFrameRate(60);
			container.setForceExit(false);
		}catch(SlickException ex){
			Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Camera default to coordiante (0, 0)
		camera = new Point2f(0, 0);
		
		pcs = new PropertyChangeSupport(this);
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	/**
	 * Called upon crossing the window or somehow terminating it
	 * returning true means that Slick2D will terminate the window
	 */
	@Override
	public boolean closeRequested() {
		System.out.println("Window sending VIEW_CLOSE event");
		pcs.firePropertyChange(message.VIEW_CLOSE.name(), false, true);
		return true;
	}
	
	public void start(){
		try {
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRenderObjects(Map<Object,IDrawable> list){
		renderObjects=Collections.synchronizedMap(list);
	}
	
	/**
	 * Draws a scrolling image, that is an Image that composed of
	 * 4 subpieces that all look exactly the same which "follows"
	 * the camera seamlessly. Used for scrolling background.
	 * Note that param <i>image</i> must have dimensions equal to
	 * or greater than that of the window, else the black
	 * standard background will appear between scrolls.
	 * 
	 * @param arg0
	 * @param g
	 * @param image which scrolls with camera
	 * @param speed that determines how fast the background moves
	 * relative to the player.
	 * Higher means faster (feels like object is closer to camera).
	 * Lower means slower (feels like object is farther from camera).
	 */
	private void drawScrollingImage(GameContainer arg0, Graphics g, Image image, float speed) {
		int modx = (int) ((camera.x*speed + windowWidth/2) / (image.getWidth()/2));
		int mody = (int) ((camera.y*speed + windowHeight/2) / (image.getHeight()/2));
		g.drawImage(background, 
					-windowWidth/2 + modx * image.getWidth()/2  - camera.x*speed,
					-windowHeight/2 + mody * image.getHeight()/2 - camera.y*speed);
	}
	
	/**
	 * For debugging purposes. Displays the worlds border.
	 * @param arg0
	 * @param g
	 */
	private void drawBorder(GameContainer arg0, Graphics g) {
		//HARDCODED. If world border changes,this row must manually be changed to display
		//correct world border
		g.drawRect(-camera.x + windowWidth/2, -camera.y + windowHeight/2, 1080, 540);
	}
	
	public void render(GameContainer arg0, Graphics g) throws SlickException {
//		g.translate(100, 100);
//		g.rotate(25, 25, f);
//		f++;
		drawScrollingImage(arg0, g, background, 1.0f);
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
			tmpImg.rotate(calculateRotation(comp.getDirection()));
			g.drawImage(tmpImg, tmpX - camera.x + windowWidth/2, tmpY - camera.y + windowHeight/2);
		}
//		System.out.println("-------------------------------------------------");
		drawBorder(arg0, g);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		colorFilter=new Color(255,0,255);
		try {
			spaceship=new SpriteSheet("resources/Spaceship.png",50,50, colorFilter);
			shott=new SpriteSheet("resources/Shott.png",3,3, colorFilter);
			asteroid=new SpriteSheet("resources/Asteroid.png",100,100, colorFilter);
			background = new Image("resources/scrollingbackgroundLarge.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		if(background.getWidth()/2 < windowWidth) {
			System.out.println("WARNING: backgroundWidth/2 is less than windowWidth. Scrolling will be broke!");
		}
		if(background.getHeight()/2 < windowHeight) {
			System.out.println("WARNING: backgroundHeight/2 is less than windowHeight. Scrolling will be broke!");
		}
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
//		for(IDrawable comp: renderObjects.values()){
//			
//		}
		
	}
	
	private float calculateRotation(Vector2f vector){
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
	
	public AppGameContainer getContainer(){
		return this.container;
	}
	
	public void setCamera(Point2f camera) {
		this.camera = camera;
	}

}
