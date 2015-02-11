package com.d954mas.game.utils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.d954mas.game.MyGame;

public class Assets implements Disposable, AssetErrorListener{	
	
	private static final String TAG="Assets";
	public static final Assets instance=new Assets();
	
	private AssetManager assetManager;
	public Skin uiSkin; 
	public TextureAtlas atlas;// main atlas
	public AudioManager audioManager;
	public Settings settings;
	public MyGame game;
	private Assets(){}

	@SuppressWarnings("rawtypes")
	@Override
	public void error( AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error("Assets", "Could not load asset "+asset.fileName+" ",throwable);
	}
	
	public void init(AssetManager assetManager,MyGame game){	
		this.assetManager=assetManager;
		assetManager.setErrorListener(this);
		this.game=game;
		
		//loading
	    assetManager.load("ui/uiskin.json", Skin.class);
	    assetManager.finishLoading();//loading ui
	    uiSkin=assetManager.get("ui/uiskin.json");
	    
	   //LOADING FILE FROM FOLDERS
	    loadAllFromFolder("music/", Music.class);
	    loadAllFromFolder("sound/", Sound.class);
	    
	    //LOADING OTHERS
	    assetManager.load("data/game.atlas", TextureAtlas.class);

	}
	private <T> void loadAllFromFolder(String name,Class<T> type){
		//DESKTOP NOT WORK ON DEPLOY
		FileHandle dirHandle;
		if (Gdx.app.getType() == ApplicationType.Desktop) {
		    dirHandle = new FileHandle("./bin/"+name);
		       
		} else {
		    dirHandle = Gdx.files.internal(name);
		}
		    
		for (FileHandle entry: dirHandle.list()) {
		    assetManager.load(entry.path(),type);
		}
	}
	public void initAssets(){	
		Assets.instance.logAssetsInfo();
		ObjectMap<String, Music>  music=new ObjectMap<String, Music>(); 
		ObjectMap<String, Sound>  sound=new ObjectMap<String, Sound>(); 
		atlas=assetManager.get("data/game.atlas");
		//GET MUSIC AND SOUNDS TO CREATE SoundManager
		//MUSIC
		Array<Music> musicArray=new Array<Music>();
		assetManager.getAll(Music.class, musicArray);
		//put music in map, key is music name
		for(Music m:musicArray){	
			music.put(assetManager.getAssetFileName(m).replaceAll("^.*/", "").replaceAll("\\..*", ""), m);
		}
		for(Entry<String, Music> entry: music.entries()){
			Gdx.app.log(TAG, "Music: "+entry.key);
		}
		//SOUNDS
		Array<Sound> soundArray=new Array<Sound>();
		assetManager.getAll(Sound.class, soundArray);
		for(Sound s:soundArray)	{	
			sound.put(assetManager.getAssetFileName(s).replaceAll("^.*/", "").replaceAll("\\..*", ""), s);
		}
		for(Entry<String, Sound> entry: sound.entries()){
			Gdx.app.log(TAG, "Sound: "+entry.key);
		}
		audioManager=new AudioManager(sound, music);
		settings=new Settings(audioManager);	
		
		settings.logSettings();
	}
	
	public void logAssetsInfo(){
		Gdx.app.log(TAG, "Assets loaded: "
				+ assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())Gdx.app.log(TAG, "asset: " + a);
	}
	public AssetManager getAssetManager(){return assetManager;}
	@Override
	public void dispose() {
		assetManager.dispose();
		settings.saveSettings();
	}
}
