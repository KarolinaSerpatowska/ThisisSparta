package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
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

    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

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
        gamecamera.zoom=0.5f;

        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;

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

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0, 30, 1920, 34);


        x=100;
       for (int i=0; i<enemies.size();i++){
           if(enemies.get(i).isIsdead()) enemies.remove(i);
           else {
               enemies.get(i).draw(game.batch);

               shapeRenderer.setColor(Color.RED);
               shapeRenderer.rect(enemies.get(i).getX(), enemies.get(i).getY(), 4, enemies.get(i).getHeight());
               shapeRenderer.setColor(Color.WHITE);
               shapeRenderer.rect(enemies.get(i).getX() + enemies.get(i).getWidth() - 4, enemies.get(i).getY(), 4, enemies.get(i).getHeight());
               shapeRenderer.setColor(Color.MAGENTA);
               shapeRenderer.rect(enemies.get(i).getX(), enemies.get(i).getY() + enemies.get(i).getHeight() - 4, enemies.get(i).getWidth(), 4);
               shapeRenderer.setColor(Color.BLUE);
               shapeRenderer.rect(enemies.get(i).getX(), enemies.get(i).getY(), enemies.get(i).getWidth(), 4);


           }
           x += 300;
       }
        game.batch.end();
        //prawo
        //return new Rectangle(x+35, y, width-20,height-12);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(player.getX()+player.getWidth()-20, player.getY()+4, 20,player.getHeight()-8);

        //lewo
        // return new Rectangle(x, y, width-20,height-12);
        shapeRenderer.setColor(Color.MAGENTA);
        shapeRenderer.rect(player.getX()+20, player.getY()+4, 4,player.getHeight()-8);

        //top
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(player.getX()+20, player.getY()+player.getHeight()-4, player.getWidth()-20,4);

        //bot
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(player.getX()+20, player.getY(), player.getWidth()-20,4);

//=====================================================================================
        shapeRenderer.setProjectionMatrix(game.batch.getProjectionMatrix());
        shapeRenderer.end();

//===================================================================================

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
