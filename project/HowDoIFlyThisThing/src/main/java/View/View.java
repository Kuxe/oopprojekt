package View;


import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
import org.newdawn.slick.TrueTypeFont;

import com.whathappensingandalf.howdoiflythisthing.DrawableData;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;
import com.whathappensingandalf.howdoiflythisthing.IGameObject;

public class View extends BasicGame implements ApplicationListener{
	
	private AppGameContainer container;
	
	private Set<DrawableData> renderObjects;
	private SpriteSheet spaceship,shott,missile,asteroid,healthPack,ammoPickup, missingImage;

	private SpriteSheet background_1,
						background_2,
						background_3;
	
	private SpriteSheet planet_1;
	
	//TODO
	private SpriteSheet hullImage, shieldImage;
						
						
	private Color colorFilter;
	private int backgroundWidth, backgroundHeight;
	private Float f=0f;
	
	private Point2f camera;
	private final int windowWidth = 640;
	private final int windowHeight = 480;
	
	private PropertyChangeSupport pcs;
	
	private final Object lock;
	private boolean isReady = false;
	
	private int nbrOfHull;
	private int nbrOfShield;
	private String countdownText = "Loading model...";

	
	public static enum message {
		VIEW_CLOSE
	}
	
	public View(String title, Object lock, boolean fullscreen){
		super(title);
		this.lock = lock;
		renderObjects = new HashSet<DrawableData>();
		try{
			container=new AppGameContainer(this);
			container.setDisplayMode(windowWidth, windowHeight, false);
			container.setTargetFrameRate(60);
			container.setForceExit(false);
			container.setFullscreen(fullscreen);
		}catch(SlickException ex){
			Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Camera default to coordiante (0, 0)
		camera = new Point2f(0, 0);
		
		pcs = new PropertyChangeSupport(this);		
	}
	
	public boolean isReady() {
		return isReady;
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
	public void stop(){
		container.exit();
	}
	
	public void setCountdown(long countdown){
		countdownText = String.valueOf(countdown);
	}
	
	public void setRenderObjects(Set<DrawableData> set){
		renderObjects=Collections.synchronizedSet(set);
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
	private void drawScrollingImage(GameContainer arg0, Graphics g, SpriteSheet image, float speed) {
		drawScrollingImageForceDimension(arg0, g, image, speed, image.getWidth(), image.getHeight());
	}
	
	private void drawScrollingImageForceDimension(GameContainer arg0, Graphics g, SpriteSheet image, float speed, int imageWidth, int imageHeight) {
		int modx = (int) ((camera.x*speed + windowWidth/2) / (imageWidth/2));
		int mody = (int) ((camera.y*speed + windowHeight/2) / (imageHeight/2));
		g.drawImage(image, 
					-windowWidth/2 + modx * imageWidth/2  - camera.x*speed,
					-windowHeight/2 + mody * imageHeight/2 - camera.y*speed);
	}
	
	private void drawRoundCountdown(GameContainer arg0, Graphics g)
	{
		if(!countdownText.equals("-1")) {
			g.drawString(countdownText, (windowWidth - g.getFont().getWidth(countdownText))/2, 30);
		}
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
	
	public void drawHull(Graphics g){
		
		int xPos= 10;
		int yPos= 10;
		for(int i= 0; i< nbrOfHull; i++){
			g.drawImage(hullImage, xPos, yPos);
			xPos= xPos + hullImage.getWidth();
		}
	}
	public void drawShield(Graphics g){
		
		int xPos= hullImage.getWidth() * nbrOfHull;
		int yPos= 10;
		for(int i= 0; i< nbrOfShield; i++){
			g.drawImage(shieldImage, xPos, yPos);
			xPos= xPos + shieldImage.getWidth();
		}
	}
	
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		drawScrollingImage(arg0, g, background_1, 0.05f);
		drawScrollingImage(arg0, g, background_2, 0.15f);
		drawRoundCountdown(arg0, g);

		for(DrawableData comp: renderObjects){
			
			float tmpX,tmpY;
			Image tmpImg;
			if(comp.getType().equals("SPACESHIP")){
				tmpImg=spaceship.copy();
			}else if(comp.getType().equals("ASTEROID")){
				tmpImg=asteroid.copy();
			}else if(comp.getType().equals("BULLET")){
				tmpImg=shott.copy();
			}else if(comp.getType().equals("MISSILE")){
				tmpImg=missile.copy();
			}else if(comp.getType().equals("HEALTH_PICKUP")){
				tmpImg=healthPack.copy();
			}else if(comp.getType().equals("WEAPON_PICKUP")){
				tmpImg=this.ammoPickup.copy();
			}else{
				tmpImg=missingImage.copy();
			}
			tmpX=comp.getPosition().x-comp.getWidth()/2;
			tmpY=comp.getPosition().y-comp.getHeight()/2;
			tmpImg.rotate(calculateRotation(comp.getDirection()));
			g.drawImage(tmpImg, tmpX - camera.x + windowWidth/2, tmpY - camera.y + windowHeight/2);
		}
		drawBorder(arg0, g);
		drawHull(g);
		drawShield(g);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		colorFilter=new Color(255,0,255);
		try {
			spaceship=new SpriteSheet("resources/Spaceship.png",50,50, colorFilter);
			shott=new SpriteSheet("resources/Shott.png",3,3, colorFilter);
			missile=new SpriteSheet("resources/Missile.png",5,10, colorFilter);
			asteroid=new SpriteSheet("resources/Asteroid.png",100,100, colorFilter);
			healthPack=new SpriteSheet("resources/HealthPack.png",25,25, colorFilter);
			ammoPickup=new SpriteSheet("resources/AmmoPickup.png",25,25, colorFilter);
			missingImage=new SpriteSheet("resources/MissingImage.png",25,25, colorFilter);
			background_1 = new SpriteSheet("resources/scrollingbackground_1st_layer.png", 1280, 960, colorFilter);
			background_2 = new SpriteSheet("resources/scrollingbackground_2nd_layer.png", 1280, 960, colorFilter);
			background_3 = new SpriteSheet("resources/scrollingbackground_3rd_layer.png", 1280, 960, colorFilter);
			
			planet_1 = new SpriteSheet("resources/planet_1.png", 100, 100, colorFilter);
			
			hullImage= new SpriteSheet("resources/hull.png", 15, 20, colorFilter);
			shieldImage= new SpriteSheet("resources/shield.png", 15, 20, colorFilter);
			System.out.println(hullImage);
			System.out.println(shott);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		synchronized(lock) {
			isReady = true;
			lock.notifyAll();
		}
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {		
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
	
	public void setNbrOfHull(int nbrOfHull){
		this.nbrOfHull= nbrOfHull;
	}
	
	public void setNbrOfShield(int nbrOfShield){
		this.nbrOfShield= nbrOfShield;
	}
}
