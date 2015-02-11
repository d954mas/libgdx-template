package com.d954mas.game.utils;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class AudioManager {
	
	private final ObjectMap<String, Sound> sound;
	private final ObjectMap<String, Music> music;
	
	private float soundVolume=1,musicVolume=1;
	
	public AudioManager(){
		sound=new ObjectMap<String, Sound>();
		music=new ObjectMap<String, Music>();
	}
	
	public AudioManager(ObjectMap<String, Sound> sound,ObjectMap<String, Music> music){
		this.sound=sound;
		this.music=music;
	}
	
	public void pauseAll(){	//pause all sounds
		for(Entry<String,Sound> entry:sound.entries()){
			entry.value.pause();
		}
		for(Entry<String,Music> entry:music.entries()){
			entry.value.pause();
		}
	}
	public void resumeAll(){
		resumeSounds();
		resumeMusic();	
	}
	public void resumeSounds(){
		for(Entry<String,Sound> entry:sound.entries())
		{
			entry.value.resume();
		}
	}
	public void resumeMusic(){
		for(Entry<String,Music> entry:music.entries())
		{
			if(entry.value.getPosition()>0)entry.value.play();
		}
	}
	public void playSound(String name){
		sound.get(name).play(soundVolume);
	}
	
	public void playMusic(String name)
	{	
		music.get(name).setVolume(musicVolume);
		music.get(name).play();
	}
	public void stopMusic(String name)
	{
		music.get(name).stop();
	}
	public float getSoundVolume() {
		return soundVolume;
	}
	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
	 public float getMusicVolume() {
		return musicVolume;
	}
	 public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}
	
}
