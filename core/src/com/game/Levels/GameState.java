package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.ThisisSparta;
import com.game.UI.Hud;

public class GameState implements Screen {

    private ThisisSparta game;
    private OrthographicCamera gamecamera;
    private Viewport gamePort;
    private Hud hud;
    private Texture background;

    public GameState(ThisisSparta game){
        this.game=game;
        gamecamera=new OrthographicCamera();
        gamePort=new FitViewport(ThisisSparta.WIDTH,ThisisSparta.HEIGHT,gamecamera);
        hud=new Hud(game.batch);
        background=new Texture("background.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background,0,0, game.WIDTH, game.HEIGHT);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
