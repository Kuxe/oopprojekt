package services;

import java.applet.Applet;
import java.applet.AudioClip;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.whathappensingandalf.howdoiflythisthing.Gameworld;

public class SoundEffects{

	private AudioClip spaceshipFireSound;
	private AudioClip projectileDieSound;
	private AudioClip spaceshipDieSound;
	private AudioClip pickupDieSound;

	public enum Sound{
		SPACESHIP_DIE, SPACESHIP_FIRE, PROJECTILE_FIRE, PICKUP_DIE
	}

	public SoundEffects(){
		createSpaceshipFireSound();
		createProjectileDieSound();
		createSpaceshipDieSound();
		createPickupDieSound();
	}

	public void createSpaceshipFireSound(){
		File file= new File("sounds/Gun_Shot.wav");
		URI uri= file.toURI();
		URL url;
		try {
			url = uri.toURL();
			spaceshipFireSound= Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void createProjectileDieSound(){
		File file= new File("sounds/Gun_Shot.wav");
		URI uri= file.toURI();
		URL url;
		try {
			url = uri.toURL();
			projectileDieSound= Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void createSpaceshipDieSound(){
		File file= new File("sounds/Explosion.wav");
		URI uri= file.toURI();
		URL url;
		try {
			url = uri.toURL();
			spaceshipDieSound= Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void createPickupDieSound(){
		File file= new File("sounds/Dive.wav");
		URI uri= file.toURI();
		URL url;
		try{
			url= uri.toURL();
			pickupDieSound= Applet.newAudioClip(url);
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
	}
	public void playSound(Set<String> listOfSounds){
		if(listOfSounds== null){

		}else{
			for(String s: listOfSounds){
				if (Sound.SPACESHIP_FIRE.toString().equals(s)) {
					spaceshipFireSound.play();
				}else if (Sound.PROJECTILE_FIRE.toString().equals(s)) {
					projectileDieSound.play();
				}else if (Sound.SPACESHIP_DIE.toString().equals(s)) {
					spaceshipDieSound.play();
				}else if(Sound.PICKUP_DIE.toString().equals(s)){
					pickupDieSound.play();
				}
			}
		}
	}
}//end SoundEffects