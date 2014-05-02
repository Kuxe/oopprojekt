package com.whathappensingandalf.howdoiflythisthing;

import java.applet.Applet;
import java.applet.AudioClip;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class SoundEffects implements PropertyChangeListener{

	private Gameworld gameworld;
	private AudioClip projectileSound;
	private AudioClip backgroundMusic;

	public SoundEffects(Gameworld gameworld){
		this.gameworld= gameworld;
		createProjectileSound();
		createBackgroundMusic();
	}
	public void createProjectileSound(){
		File file= new File("sounds/Gun_Shot.wav");
		URI uri= file.toURI();
		URL url;
		try {
			url = uri.toURL();
			projectileSound= Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void createBackgroundMusic(){
		File file= new File("sounds/Dove.wav");
		URI uri= file.toURI();
		URL url;
		try {
			url = uri.toURL();
			backgroundMusic= Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void propertyChange(PropertyChangeEvent e) {
		
	}
}//end SoundEffects