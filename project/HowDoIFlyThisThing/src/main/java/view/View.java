package view;


import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.newdawn.slick.Animation;
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

public class View extends BasicGame implements ApplicationListener{
	
	public static enum Message {
		VIEW_CLOSE
	}
	
	private AppGameContainer container;
	
	private Set<DrawableData> renderObjects;

	private Animation explosion,sparkle,asteroidExplosion;
	private List<AnimationWrapper> animations,removeAnimations;

	private SpriteSheet asteroid,
						asteroidBroken1,
						asteroidBroken2,
						asteroidBroken3;
	
	private SpriteSheet spaceship,
						shott,
						thrusterFire,
						missile,
						cookieCracker,
						healthPack,
						ammoPickup,
						missingImage;

	private SpriteSheet background_1,
						background_2,
						background_3;
	
	private SpriteSheet planet_1;
	
	private SpriteSheet hullImage, shieldImage;
						
						
	private Color colorFilter;
	
	private Point2f camera;
	private final int windowWidth = 640;
	private final int windowHeight = 480;
	private int worldWidth = windowWidth;	//Default to window size
	private int worldHeight = windowHeight;	//Default to window size
	
	private PropertyChangeSupport pcs;
	
	private final Object lock;
	private boolean isReady = false;
	
	private int hull;
	private int shield;
	
	private TrueTypeFont font;
	private String countdownText = "Loading model...";
	private String modelStatus = "";
	
	private boolean shouldUpdate;
	
	public View(String title, Object lock, boolean fullscreen){
		super(title);
		this.lock = lock;
		renderObjects = new HashSet<DrawableData>();
		System.setProperty("org.newdawn.slick.pngloader", "false");
		try{			
			container=new AppGameContainer(this);
			container.setDisplayMode(windowWidth, windowHeight, false);
			container.setTargetFrameRate(60);
			container.setForceExit(false);
			container.setFullscreen(fullscreen);
			container.setIcon("resources/LauncherIcon32x32.png");
		}catch(SlickException ex){
			Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Camera default to coordiante (0, 0)
		camera = new Point2f(0, 0);
		
		pcs = new PropertyChangeSupport(this);
		shouldUpdate = true;
	}
	
	/**
	 * returns if the view is ready to receive DrwableData and Animations or not.
	 */
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
		pcs.firePropertyChange(Message.VIEW_CLOSE.name(), false, true);
		stop();
		return true;
	}
	
	
	/**
	 * Slick2d inherited method to start the game. In this case the view.
	 * Starts Slick2ds background mechanics.
	 */
	public void start(){
		
		try {
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clear all the image data to allow the game to restart.
	 */
	public void stop(){
		container.getGraphics().clear();
		container.getGraphics().destroy();
		try {
			ammoPickup.destroy();
			spaceship.destroy();
			shott.destroy();
			thrusterFire.destroy();
			missile.destroy();
			cookieCracker.destroy();
			asteroid.destroy();
			asteroidBroken1.destroy();
			asteroidBroken2.destroy();
			asteroidBroken3.destroy();
			healthPack.destroy();
			ammoPickup.destroy();
			missingImage.destroy();
			background_1.destroy();
			background_2.destroy();
			background_3.destroy();
			planet_1.destroy();
			hullImage.destroy();
			shieldImage.destroy();
			
			for(int i = 0 ; i < explosion.getFrameCount(); i++) {
				explosion.getImage(i).destroy();
			}
			for(int i = 0 ; i < sparkle.getFrameCount(); i++) {
				sparkle.getImage(i).destroy();
			}
			for(int i = 0 ; i < asteroidExplosion.getFrameCount(); i++) {
				asteroidExplosion.getImage(i).destroy();
			}
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		container.exit();
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
		int modx = (int) ((camera.x*speed + windowWidth/2.0f) / (imageWidth/2.0f));
		int mody = (int) ((camera.y*speed + windowHeight/2.0f) / (imageHeight/2.0f));
		g.drawImage(image, 
					-windowWidth/2.0f + modx * imageWidth/2.0f  - camera.x*speed,
					-windowHeight/2.0f + mody * imageHeight/2.0f - camera.y*speed);
	}
	
	private void drawRoundCountdown(GameContainer arg0, Graphics g)
	{
		if(!countdownText.equals("-1")) {
			g.setFont(font);
			g.drawString(countdownText, (windowWidth - font.getWidth(countdownText))/2.0f, 40 + font.getHeight(modelStatus));
		}
	}
	
	/**
	 * Writes text on the window to inform the player about the games status.
	 */
	private void drawModelStatus(GameContainer arg0, Graphics g) {
		g.setFont(font);
		g.drawString(modelStatus, (windowWidth - font.getWidth(modelStatus))/2.0f, 30);
	}
	
	/**
	 * Displays the worlds border.
	 * @param arg0
	 * @param g
	 */
	private void drawBorder(GameContainer arg0, Graphics g) {
		g.drawRect(-camera.x + windowWidth/2, -camera.y + windowHeight/2, worldWidth, worldHeight);
	}
	
	/**
	 * Draw the players spaceships current Hull.
	 */
	public void drawHull(Graphics g){
		int xPos= 10;
		int yPos= 10;
		for(int i= 0; i< hull; i++){
			g.drawImage(hullImage, xPos, yPos);
			xPos= xPos + hullImage.getWidth();
		}
	}
	/**
	 * Draw the players spaceships current Shield.
	 */
	public void drawShield(Graphics g){
		
		int xPos= hullImage.getWidth() * hull;
		int yPos= 10;
		for(int i= 0; i< shield; i++){
			g.drawImage(shieldImage, xPos, yPos);
			xPos= xPos + shieldImage.getWidth();
		}
	}
	
	/**
	 * 
	 */
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		drawScrollingImage(arg0, g, background_1, 0.05f);
		drawScrollingImage(arg0, g, background_2, 0.15f);


		for(DrawableData comp: renderObjects){
			
			float tmpX,tmpY;
			Image tmpImg;
			
			switch(comp.getType()) {
			case "SPACESHIP":
				tmpImg = spaceship.copy();
				break;
			case "ASTEROID":
				tmpImg = asteroid.copy();
				break;
			case "ASTEROID_DMG1":
				tmpImg = asteroidBroken1.copy();
				break;
			case "ASTEROID_DMG2":
				tmpImg = asteroidBroken2.copy();
				break;
			case "ASTEROID_DMG3":
				tmpImg = asteroidBroken3.copy();
				break;
			case "BULLET":
				tmpImg = shott.copy();
				break;
			case "THRUSTER_FIRE":
				tmpImg = thrusterFire.copy();
				break;
			case "MISSILE":
				tmpImg = missile.copy();
				break;
			case "COOKIE_CRACKER":
				tmpImg = cookieCracker.copy();
				break;
			case "HEALTH_PICKUP":
				tmpImg = healthPack.copy();
				break;
			case "WEAPON_PICKUP":
				tmpImg = ammoPickup.copy();
				break;
			default:
				tmpImg = missingImage.copy();
			}
			tmpX=comp.getPosition().x-comp.getWidth()/2.0f;
			tmpY=comp.getPosition().y-comp.getHeight()/2.0f;
			tmpImg.rotate(calculateRotation(comp.getDirection()));
			g.drawImage(tmpImg, tmpX - camera.x + windowWidth/2, tmpY - camera.y + windowHeight/2);
		}
		
		slateAnimationsForRemoval();
		
		for(AnimationWrapper animRm: removeAnimations){
			animations.remove(animRm);
		}
		removeAnimations.clear();
		
		drawBorder(arg0, g);
		drawHull(g);
		drawShield(g);
		
		drawModelStatus(arg0, g);
		drawRoundCountdown(arg0, g);
	}
	
	private synchronized void slateAnimationsForRemoval(){
		for(AnimationWrapper animComp: animations){
			if(animComp.getAnimation().isStopped()){
				removeAnimations.add(animComp);
			}
			animComp.getAnimation().draw(animComp.getPosition().x - animComp.getWidth()/2.0f + windowWidth/2.0f - camera.x ,animComp.getPosition().y - animComp.getHeight()/2.0f + windowHeight/2.0f - camera.y);
		}
	}
	
	private synchronized void addExplosion(Point2f position){
		Animation tempExp=explosion.copy();
		tempExp.stopAt(19);
		animations.add(new AnimationWrapper(new Point2f(position.x,position.y),tempExp,320,240));
	}
	private synchronized void addSparkle(Point2f position){
		Animation tempSp=sparkle.copy();
		tempSp.stopAt(11);
		animations.add(new AnimationWrapper(new Point2f(position.x,position.y),tempSp, 10,10));
	}
	private void addAsteroidExplosion(Point2f position) {
		Animation tempAnim=asteroidExplosion.copy();
		tempAnim.stopAt(15);
		animations.add(new AnimationWrapper(new Point2f(position),tempAnim, 300,300));
	}

	/**
	 * Called once on start from Slick2d.
	 * 
	 */
	@Override
	public void init(GameContainer arg0) throws SlickException {
		colorFilter=new Color(255,0,255);
		animations = new ArrayList<AnimationWrapper>();
		removeAnimations = new ArrayList<AnimationWrapper>();
		try {
			spaceship=new SpriteSheet("resources/Spaceship.png",50,50, colorFilter);
			shott=new SpriteSheet("resources/Shott.png",3,3, colorFilter);
			thrusterFire = new SpriteSheet("resources/ThrusterFire.png",5,5, colorFilter); 
			missile=new SpriteSheet("resources/Missile.png",5,10, colorFilter);
			cookieCracker=new SpriteSheet("resources/CookieCracker.png",5,10, colorFilter);
			asteroid=new SpriteSheet("resources/Asteroid.png",100,100, colorFilter);
			asteroidBroken1=new SpriteSheet("resources/AsteroidBroken1.png",100,100, colorFilter);
			asteroidBroken2=new SpriteSheet("resources/AsteroidBroken2.png",100,100, colorFilter);
			asteroidBroken3=new SpriteSheet("resources/AsteroidBroken3.png",100,100, colorFilter);
			healthPack=new SpriteSheet("resources/HealthPack.png",25,25, colorFilter);
			ammoPickup=new SpriteSheet("resources/AmmoPickup.png",25,25, colorFilter);
			missingImage=new SpriteSheet("resources/MissingImage.png",25,25, colorFilter);
			background_1 = new SpriteSheet("resources/scrollingbackground_1st_layer.png", 1280, 960, colorFilter);
			background_2 = new SpriteSheet("resources/scrollingbackground_2nd_layer.png", 1280, 960, colorFilter);
			background_3 = new SpriteSheet("resources/scrollingbackground_3rd_layer.png", 1280, 960, colorFilter);
			
			explosion = new Animation(new SpriteSheet(new Image("resources/explosionanimation.png"),320,240),100);
			sparkle = new Animation(new SpriteSheet("resources/SparkleAnimation.png",10,10, colorFilter),20);
			asteroidExplosion = new Animation(new SpriteSheet("resources/AsteroidAnimation.png",300,300, colorFilter),75);
			
			planet_1 = new SpriteSheet("resources/planet_1.png", 100, 100, colorFilter);
			
			hullImage= new SpriteSheet("resources/hull.png", 15, 20, colorFilter);
			shieldImage= new SpriteSheet("resources/shield.png", 15, 20, colorFilter);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		font = new TrueTypeFont(new Font("Monospaced", Font.PLAIN, 18), false);
		container.getGraphics().setFont(font);
		container.setDefaultFont(font);
		
		synchronized(lock) {
			isReady = true;
			lock.notifyAll();
		}
	}

	/**
	 * inherited from Slick2d. Should idealy not do anything.
	 */
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		if(!shouldUpdate){//allows the game to restart
			this.stop();
		}
	}
	
	public void endUpdate(){
		this.shouldUpdate = false;
	}
	
	/**
	 * calculates the angle for drawing from the DrawableDatas direction vector.
	 * @return the angle from the vector in degrees
	 */
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
	
	public void setHull(int hull){
		this.hull= hull;
	}
	
	public void setShield(int shield){
		this.shield= shield;
	}
	
	public void setCountdown(long countdown){
		countdownText = String.valueOf(countdown);
	}
	
	public void setRenderObjects(Set<DrawableData> set){
		renderObjects=Collections.synchronizedSet(set);
	}
	
	public void setModelStatus(String status) {
		modelStatus = status;
	}
	
	public void setBorder(int worldWidth, int worldHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
	}
	
	public void createExplosion(Point2f position){
		this.addExplosion(position);
	}
	
	public void createSparkle(Point2f position){
		this.addSparkle(position);
	}
	
	public void createAsteroidExplosion(Point2f position){
		this.addAsteroidExplosion(position);
	}
}
