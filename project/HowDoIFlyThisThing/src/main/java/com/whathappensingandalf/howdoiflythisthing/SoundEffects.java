package com.whathappensingandalf.howdoiflythisthing;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class SoundEffects{

	private AudioClip projectileSound;
	private AudioClip backgroundMusic;

	public SoundEffects(){
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

	public void playProjectileSound(){
		projectileSound.play();
	}
	public void playBackgroundMusic(){
		backgroundMusic.loop();;
	}
	public void playAllSounds(){
		playBackgroundMusic();
		playProjectileSound();
	}
}//end SoundEffects