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

	public enum sound{
		SPACESHIP_DIE, SPACESHIP_FIRE, PROJECTILE_DIE
	}

	public SoundEffects(){
		createSpaceshipFireSound();
		createProjectileDieSound();
		createSpaceshipDieSound();
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
		File file= new File("sounds/Dove.wav");
		URI uri= file.toURI();
		URL url;
		try {
			url = uri.toURL();
			spaceshipDieSound= Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void playSound(Set<String> listOfSounds){
		if(listOfSounds== null){

		}
		for(String s: listOfSounds){
			if (sound.SPACESHIP_FIRE.toString().equals(s)) {
				spaceshipFireSound.play();
			} else if (sound.PROJECTILE_DIE.toString().equals(s)) {
				projectileDieSound.play();
			} else if (sound.SPACESHIP_DIE.toString().equals(s)) {
				spaceshipDieSound.play();
			}
		}
	}
}//end SoundEffects