package com.d954mas.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.d954mas.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Settings settings=new Settings();
		settings.fast=true;
		settings.duplicatePadding=true;
		settings.maxHeight=2048;
		settings.maxWidth=2048;
		//settings.filterMag=TextureFilter.Linear;
		//settings.filterMin=TextureFilter.Linear;
		TexturePacker.process(settings, "../Assets/game", "../android/assets/data", "game");
		TexturePacker.process(settings, "../Assets/ui", "../android/assets/ui", "uiskin");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGame(new DesktopNativeApi()), config);
	
		
	}
}
