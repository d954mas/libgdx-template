package com.d954mas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.d954mas.game.utils.Assets;
import com.d954mas.game.utils.Constants;

public class MainMenuScreen implements Screen {
	public static final String TAG ="MainMenuScreen";
	private Stage stage;
	
	public MainMenuScreen() {
		stage=new Stage(new FitViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		Gdx.app.log(TAG, "show");
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		TextButton playBtn=new TextButton("Play",Assets.instance.uiSkin);
		playBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
			}
		});
		playBtn.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT/2);
		playBtn.setSize(300,200);
		
		TextButton settingsBtn=new TextButton("Settings",Assets.instance.uiSkin);
		settingsBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.instance.game.setScreen(Assets.instance.game.screens.settingsScreen);
			}	
		});
		settingsBtn.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT/2-300);
		settingsBtn.setSize(300,200);
		
		//add to stage
		stage.addActor(playBtn);
		stage.addActor(settingsBtn);
	
	}

	@Override
	public void hide() {
		Gdx.app.log(TAG,"hide");
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		stage.dispose();
		Gdx.input.setInputProcessor(null);
	}

}
