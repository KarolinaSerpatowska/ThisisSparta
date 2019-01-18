package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.Entities.Player;
import com.game.ThisisSparta;
import com.game.UI.Hud;

import java.util.ArrayList;
import java.util.Map;

public class GameState implements Screen {

    private ThisisSparta game;
    private OrthographicCamera gamecamera;
    private Viewport gamePort;
    private Hud hud;
    private Texture background;
    private ArrayList<Sprite> ground;
    private Player player;

    public GameState(ThisisSparta game){
        this.game=game;
        gamecamera=new OrthographicCamera();
        gamePort=new FitViewport(ThisisSparta.WIDTH,ThisisSparta.HEIGHT,gamecamera);
        hud=new Hud(game.batch);
        background=new Texture("background.png");
        ground=new ArrayList<Sprite>();
        for(int i=0;i<20;i++){
            ground.add(new Sprite(new Texture("ground.png")));
        }
        player=new Player(new Sprite(new Texture("spartanidleright.png")), this);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(background,0,0, 800, 600);
        float x= 0;
        float y=game.HEIGHT-64;
        for (int i=0; i<20;i++) {
            game.batch.draw(ground.get(i), x, 0);
            x+=60;
        }
        game.batch.draw(player.getSprite(), 300,300);
        player.render();

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
