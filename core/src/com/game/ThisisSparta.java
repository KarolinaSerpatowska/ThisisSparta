package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Levels.*;

public class ThisisSparta extends Game {

	public static  final int WIDTH=1920;
	public static final int HEIGHT=WIDTH/16*9;
	public SpriteBatch batch;
	public GameState gameState;
	public MainMenuState mainMenu;
	public ControlsScreen controlsScreen;
	public WinScreen winScreen;
	public LoseScreen loseScreen;
	private Music menuMusic;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenu=new MainMenuState(this);
		controlsScreen=new ControlsScreen(this);
		winScreen=new WinScreen(this);
		loseScreen=new LoseScreen(this);
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Rise of spirit.mp3"));
		menuMusic.setLooping(true);
		menuMusic.play();

		setScreen(mainMenu);
	}

	public void changeScreen(String name){
		if(name=="mainmenu") setScreen(mainMenu);
		else if(name=="game") {
			menuMusic.stop();
			gameState=new GameState(this);
			setScreen(gameState);
			Gdx.input.setInputProcessor(null);
		}
		else if(name=="controls") setScreen(controlsScreen);
		else if(name=="win") setScreen(winScreen);
		else if(name=="lose") setScreen(loseScreen);
		else if(name=="exit") Gdx.app.exit();
		if(name=="mainmenu" || name=="controls") menuMusic.play();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
