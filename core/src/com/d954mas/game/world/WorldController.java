package com.d954mas.game.world;

public class WorldController {
	private static final String TAG=WorldController.class.getName();
	private World world;
	private WorldRenderer worldRenderer;
	public WorldController(World world,WorldRenderer worldRenderer){
		this.world=world;
		this.worldRenderer=worldRenderer;
		init();
	}
	
	private void init(){}
	public void update(float delta){}
}
