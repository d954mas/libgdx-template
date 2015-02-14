package com.d954mas.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.d954mas.game.utils.Constants;

public class WorldRenderer implements Disposable {
	private static final String TAG=WorldRenderer.class.getName();
	private World world;
	private OrthographicCamera camera,cameraHud;
	private Viewport viewport,viewportHud;
	private SpriteBatch batch;
	
	public WorldRenderer(World world){
		this.world=world;
		init();
	}
	private void init(){
		camera=new OrthographicCamera();
		cameraHud=new OrthographicCamera();
		viewport=new FitViewport(Constants.WORLD_CAMERA_WIDTH,Constants.WORLD_CAMERA_HEIGHT,camera);
		viewportHud=new FitViewport(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT,cameraHud);
		batch=new SpriteBatch();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
	}
	public void resize(int width,int height){
		viewport.update(width, height);
		viewportHud.update(width, height);
	}
	@Override
	public void dispose() {
		batch.dispose();
	}
}
