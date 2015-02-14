package com.d954mas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.d954mas.game.utils.Assets;
import com.d954mas.game.utils.Constants;

public class LoadingScreen implements Screen{
	private static final String TAG ="LoadingScreen";
	
	private Stage stage;
	private ProgressBar bar;
	public  LoadingScreen() {
		stage=new Stage(new FitViewport(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT));
	}
	
	@Override
	public void show() {
		//setup stage
		Gdx.app.log(TAG,"show");
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		//setup ui
		bar=new ProgressBar(0, 1, 0.01f, false, Assets.instance.uiSkin,"loading");
		bar.setAnimateInterpolation(Interpolation.linear);
		bar.setAnimateDuration(0.2f);
		bar.setPosition(Constants.VIEWPORT_WIDTH/2-250, Constants.VIEWPORT_HEIGHT/2-100);
		bar.setSize(500,200);
		//add to stage
		stage.addActor(bar);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		
		Assets.instance.getAssetManager().update();
		bar.setValue(Assets.instance.getAssetManager().getProgress());
		//Gdx.app.log(TAG,"Asset loading:"+Assets.instance.getAssetManager().getProgress()
			//	+" Bar loading:"+bar.getVisualValue());
		if(bar.getVisualValue()==1)
		{
			//end loading
			Assets.instance.initAssets();
			Assets.instance.game.screens=new Screens();
			Assets.instance.game.setScreen(Assets.instance.game.screens.mainMenuScreen);
		}
			
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
	@Override
	public void hide() {
		Gdx.app.log(TAG,"hide");
		Gdx.input.setInputProcessor(null);
		dispose();
		}

	@Override
	public void pause() {Gdx.app.log(TAG,"pause");}

	@Override
	public void resume() {Gdx.app.log(TAG,"resume");}

	@Override
	public void dispose() {
		stage.dispose();
		Gdx.input.setInputProcessor(null);

	}

}
