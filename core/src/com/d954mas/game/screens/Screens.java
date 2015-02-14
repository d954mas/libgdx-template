package com.d954mas.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;

public class Screens implements Disposable{
	public Screen mainMenuScreen;
	public Screen settingsScreen;
	public Screen gameScreen;
	public Screens() {
		mainMenuScreen=new MainMenuScreen();
		settingsScreen=new SettingsScreen();
		gameScreen=new GameScreen();
	}
	public void dispose() {
		settingsScreen.dispose();
		mainMenuScreen.dispose();
		
	}
}
