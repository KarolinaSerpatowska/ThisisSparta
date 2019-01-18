package com.game.Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Mob {

    public Player(Sprite sprite, float x, float y, float width, float height) {
        super(sprite, x, y, width, height);
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        bounds.x = x - bounds.width / 2;
        bounds.y = y - bounds.height / 2;

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            x+=2;
            sprite.setX(x);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            x+=-2;
            sprite.setX(x);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            y+=2;
            sprite.setY(y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            //atak???
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F)) {
            //atak2???
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }


}
