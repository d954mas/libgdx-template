package com.d954mas.game.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.d954mas.game.utils.Assets;
import com.d954mas.game.utils.Constants;
public class SettingsScreen implements Screen {
	public static final String TAG ="SettingsScreen";
	private Stage stage;
	
	public SettingsScreen(){
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
		
		TextButton btn=new TextButton("Back",Assets.instance.uiSkin);
		btn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.instance.game.setScreen(Assets.instance.game.screens.mainMenuScreen);
			}	
		});
		btn.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT-600);
		btn.setSize(300,200);
		
		final Slider musicSlider=new Slider(0f, 1f, 0.1f, false, Assets.instance.uiSkin);
		musicSlider.setValue(Assets.instance.audioManager.getMusicVolume());
		musicSlider.setSize(300,150);
		musicSlider.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT-200);
		musicSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Assets.instance.audioManager.setMusicVolume(musicSlider.getValue());
			}
		});
		
		final Slider soundSlider=new Slider(0f, 1f, 0.1f, false, Assets.instance.uiSkin);
		soundSlider.setValue(Assets.instance.audioManager.getSoundVolume());
		soundSlider.setSize(300,150);
		soundSlider.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT-400);
		soundSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Assets.instance.audioManager.setSoundVolume(soundSlider.getValue());
			}
		});
		 
		Label musicLabel=new Label("Music", Assets.instance.uiSkin);
		musicLabel.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT-100);
		Label soundLabel=new Label("Sound", Assets.instance.uiSkin);
		soundLabel.setPosition(Constants.WORLD_WIDTH/2-150, Constants.WORLD_HEIGHT-300);
		
		//add to stage
		stage.addActor(btn);
		stage.addActor(musicSlider);
		stage.addActor(soundSlider);
		stage.addActor(soundLabel);
		stage.addActor(musicLabel);
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
