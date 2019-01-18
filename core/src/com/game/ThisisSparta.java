package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.Levels.GameState;
import com.game.Levels.StateManager;

public class ThisisSparta extends Game {

	public static  final int WIDTH=800;
	public static final int HEIGHT=WIDTH/16*9;
	public SpriteBatch batch;
	public StateManager stateManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager=new StateManager();
		//stateManager.addState(new GameState(this));
		setScreen(new GameState(this));
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
