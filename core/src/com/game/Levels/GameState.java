package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.Entities.Enemy;
import com.game.Entities.Ghost;
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
    private float timer;
    private Ghost ghost;

    public GameState(ThisisSparta game){
        this.game=game;
        gamecamera=new OrthographicCamera();
        gamePort=new FitViewport(ThisisSparta.WIDTH,ThisisSparta.HEIGHT,gamecamera);
        hud=new Hud(game.batch);
        world=new World();
        enemies=new ArrayList<Enemy>();
        player=new Player(new Sprite(new Texture("spartanidleright.png")),world);
        float y=64;
        float x=120;
        for(int i=0; i<10; i++){
            enemies.add(new Enemy(new Sprite(new Texture("ground.png")),world, x,y, player,false ));
            enemies.get(i).setPosition(x,y);
            x+=128;
        }
        enemies.add(new Enemy(new Sprite(new Texture("ground.png")),world, x,y, player,true ));
        enemies.get(10).setPosition(x,y);

        ghost=new Ghost(new Sprite(new Texture("wizard idle.png")));

        timer=0;
        player.setEnemies(enemies);

        gamecamera.zoom=0.2f;

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
        for (int i=0; i<world.getGround_size();i++) {
            game.batch.draw(world.getGround().get(i), x, 0);
            x+=64;
        }


        gamecamera.position.set(player.getX(), player.getY(), 0);
        gamecamera.update();

        float camX = gamecamera.position.x;
        float camY = gamecamera.position.y;
        Vector2 camMin = new Vector2(gamecamera.viewportWidth, gamecamera.viewportHeight);
        camMin.scl(gamecamera.zoom/2); //bring to center and scale by the zoom level
        Vector2 camMax = new Vector2(1920, 1080);
        camMax.sub(camMin); //bring to center
        //keep camera within borders
        camX = Math.min(camMax.x, Math.max(camX, camMin.x));
        camY = Math.min(camMax.y, Math.max(camY, camMin.y));
        gamecamera.position.set(camX, camY, gamecamera.position.z);
        gamecamera.update();

        player.draw(game.batch);
        ghost.draw(game.batch);

        if(player.getHp()<=0){
            timer+= Gdx.graphics.getDeltaTime();
            if(timer>0.5){
                timer=0;
                game.changeScreen("lose");
            }
        }

        if(ghost.getX()-player.getX()<=15){
                game.changeScreen("win");
        }

        if(!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isIsdead()) {
                    player.setScore();
                    enemies.remove(i);
                }
                enemies.get(i).draw(game.batch);
            }
        }
        game.batch.end();

        hud.setHp(player.getHp());
        hud.setScore(player.getScore());
        hud.update(Gdx.graphics.getDeltaTime());

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
