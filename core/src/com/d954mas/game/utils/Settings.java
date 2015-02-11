package com.d954mas.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings 
{	
	private static final String PREF_NAME="com.d954mas.game.GGJ.settings";
	private static final String TAG="Settings";
	
	private AudioManager audioManager;
	private Preferences prefs;
	
	public Settings(AudioManager audioManager){
		this.audioManager = audioManager;
		prefs=Gdx.app.getPreferences(PREF_NAME);
		loadingSettings();
	}
	private void loadingSettings(){
		audioManager.setMusicVolume(prefs.getFloat("MusicVolume",1));
		audioManager.setSoundVolume(prefs.getFloat("SoundVolume",1));
	}
	public void saveSettings(){
		prefs.putFloat("MusicVolume", audioManager.getMusicVolume());
		prefs.putFloat("SoundVolume", audioManager.getSoundVolume());
		prefs.flush();
	}
	
	public void logSettings(){
		Gdx.app.log(TAG, "Music volume:"+audioManager.getMusicVolume());
		Gdx.app.log(TAG, "Sound volume:"+audioManager.getSoundVolume());
	}
	
	public void setMusicVolume(float value){
		audioManager.setMusicVolume(value);
	}
	public void setSoundVolume(float value){
		audioManager.setSoundVolume(value);
	}
	public float getSoundVolume(){
		return audioManager.getSoundVolume();
	}
	public float getMusicVolume(){
		return audioManager.getMusicVolume();
	}
}
