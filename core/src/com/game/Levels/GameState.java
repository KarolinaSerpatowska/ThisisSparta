package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.Entities.Enemy;
import com.game.Entities.Player;
import com.game.ThisisSparta;
import com.game.UI.Hud;

import java.util.ArrayList;

public class GameState implements Screen {

    private ThisisSparta game;
    private OrthographicCamera gamecamera;
    private Viewport gamePort;
    private Hud hud;
    private World world;
    private Player player;
    private ArrayList<Enemy> enemies;

    public GameState(ThisisSparta game){
        this.game=game;
        gamecamera=new OrthographicCamera();
        gamePort=new FitViewport(ThisisSparta.WIDTH,ThisisSparta.HEIGHT,gamecamera);
        hud=new Hud(game.batch);
        world=new World();
        enemies=new ArrayList<Enemy>();
        player=new Player(new Sprite(new Texture("spartanidleright.png")),world);
        float x=100;
        float y=64;
        for(int i=0; i<3; i++){
            enemies.add(new Enemy(new Sprite(new Texture("ground.png")),world, x,y, player ));
            enemies.get(i).setPosition(x,y);
            x+=300;
        }

        player.setEnemies(enemies);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix (gamecamera.combined);

        game.batch.begin();

        game.batch.draw(world.getBackground(),0,0, 1920, 1080);
        float x= 0;
        for (int i=0; i<30;i++) {
            game.batch.draw(world.getGround().get(i), x, 0);
            x+=64;
        }
        gamecamera.position.set(player.getX(), player.getY(), 0);
        gamecamera.update();
        player.draw(game.batch);

        x=100;
       for (int i=0; i<3;i++){
            enemies.get(i).draw(game.batch);
            x+=300;

       }

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
