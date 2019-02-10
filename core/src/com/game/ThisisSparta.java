package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Levels.ControlsScreen;
import com.game.Levels.GameState;
import com.game.Levels.MainMenuState;
import com.game.Levels.WinScreen;

public class ThisisSparta extends Game {

	public static  final int WIDTH=1920;
	public static final int HEIGHT=WIDTH/16*9;
	public SpriteBatch batch;
	public GameState gameState;
	public MainMenuState mainMenu;
	public ControlsScreen controlsScreen;
	public WinScreen winScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenu=new MainMenuState(this);
		controlsScreen=new ControlsScreen(this);
		winScreen=new WinScreen(this);

		setScreen(mainMenu);
	}

	public void changeScreen(String name){
		if(name=="mainmenu") setScreen(mainMenu);
		else if(name=="game") {
			gameState=new GameState(this);
			setScreen(gameState);
		}
		else if(name=="controls") setScreen(controlsScreen);
		else if(name=="win") setScreen(winScreen);
		else if(name=="exit") Gdx.app.exit();
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
