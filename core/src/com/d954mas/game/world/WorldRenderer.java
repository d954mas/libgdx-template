package com.d954mas.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.d954mas.game.utils.Assets;
import com.d954mas.game.utils.Constants;

public class WorldRenderer implements Disposable {
	private static final String TAG=WorldRenderer.class.getName();
	private World world;
	private OrthographicCamera camera,cameraHud;
	private Viewport viewport,viewportHud;
	private SpriteBatch batch;
	private ShapeRenderer debugRenderer;
	
	public WorldRenderer(World world){
		this.world=world;
		init();
	}
	private void init(){
		camera=new OrthographicCamera();
		cameraHud=new OrthographicCamera();
		viewport=new FitViewport(Constants.WORLD_CAMERA_WIDTH,Constants.WORLD_CAMERA_HEIGHT,camera);
		viewport.getCamera().position.x=+Constants.WORLD_CAMERA_WIDTH/2;
		viewport.getCamera().position.y=+Constants.WORLD_CAMERA_HEIGHT/2;
		viewport.getCamera().update();
		viewportHud=new FitViewport(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT,cameraHud);
		viewportHud.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		batch=new SpriteBatch();
		debugRenderer=new ShapeRenderer();
		debugRenderer.setAutoShapeType(true);
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//draw world
		viewportHud.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		//draw gui
		drawDebugGui(batch);
		batch.end();
		drawDebug();
	}
	public void drawDebug(){
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		debugRenderer.setColor(1, 0, 0, 1);
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin();
		float widthDelta=camera.viewportWidth/10;
		float heightDelta=camera.viewportHeight/10;
		for(int x=0;x<10;x++){
			for(int y=0;y<10;y++){
				debugRenderer.rect(widthDelta*x, heightDelta*y,widthDelta,heightDelta);
			}
		}
		debugRenderer.end();
	}
	public void drawDebugGui(Batch batch){
		BitmapFont small=Assets.instance.uiSkin.getFont("middle");
		Color oldColor=small.getColor();//save old color check later mb not needed
		int fps=Gdx.graphics.getFramesPerSecond();
		if(fps<30)small.setColor(1,0,0,1);
		else if(fps<45)small.setColor(1,0,1,1);
		else small.setColor(0,1,0,1);
		small.draw(batch, "Fps:"+fps, Constants.VIEWPORT_WIDTH-100,Constants.VIEWPORT_HEIGHT-100);
		small.setColor(oldColor);
	}
	public void resize(int width,int height){
		viewport.update(width, height);
		viewportHud.update(width, height,true);
	}
	@Override
	public void dispose() {
		batch.dispose();
	}
}
