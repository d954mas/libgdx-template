package com.d954mas.game.screens;

import com.badlogic.gdx.utils.Disposable;

public class Screens implements Disposable{
	public MainMenuScreen mainMenuScreen;
	public SettingsScreen settingsScreen;
	public Screens() {
		mainMenuScreen=new MainMenuScreen();
		settingsScreen=new SettingsScreen();
	}
	public void dispose() {
		settingsScreen.dispose();
		mainMenuScreen.dispose();
		
	}
}
