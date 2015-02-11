package com.d954mas.game.screens;

import com.badlogic.gdx.utils.Disposable;
import com.d954mas.game.MyGame;

public class Screens implements Disposable{
	private MyGame game;
	public MainMenuScreen mainMenuScreen;
	public SettingsScreen settingsScreen;
	public Screens(MyGame game) {
		this.game=game;
		mainMenuScreen=new MainMenuScreen(game);
		settingsScreen=new SettingsScreen(game);
	}
	public void dispose() {
		settingsScreen.dispose();
		mainMenuScreen.dispose();
		
	}
}
