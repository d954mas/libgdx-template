package com.d954mas.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.d954mas.game.screens.LoadingScreen;
import com.d954mas.game.screens.Screens;
import com.d954mas.game.utils.Assets;
import com.d954mas.game.utils.NativeApi;
public class MyGame extends Game {
	
	public static final String TAG ="MyGDXGame";
	public NativeApi nativeAPI;
	public Screens screens;
	
	public MyGame(NativeApi nativeAPI) {
		this.nativeAPI=nativeAPI;
	}
	
	@Override
	public void create (){
		Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
		Gdx.app.log(TAG,"create");
		
		Assets.instance.init(new AssetManager(),this);
		setScreen(new LoadingScreen());
	}

	@Override
	public void dispose() {
		Gdx.app.log(TAG,"dispose");
		Assets.instance.dispose();
		screens.dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(TAG,"pause");
		if(Assets.instance!=null 
				&& Assets.instance.audioManager!=null)
			Assets.instance.audioManager.pauseAll();
		super.pause();
	}

	@Override
	public void resume() {
		Gdx.app.log(TAG,"resume");
		if(Assets.instance!=null 
				&& Assets.instance.audioManager!=null)
			Assets.instance.audioManager.resumeAll();
		super.resume();
	}

}

