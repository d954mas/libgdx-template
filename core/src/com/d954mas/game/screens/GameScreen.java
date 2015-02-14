package com.d954mas.game.screens;

import com.badlogic.gdx.Screen;
import com.d954mas.game.world.World;
import com.d954mas.game.world.WorldController;
import com.d954mas.game.world.WorldRenderer;

public class GameScreen implements Screen{
	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	@Override
	public void show() {
		world=new World();
		renderer=new WorldRenderer(world);
		controller=new WorldController(world, renderer);
		
	}

	@Override
	public void render(float delta) {
		controller.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
