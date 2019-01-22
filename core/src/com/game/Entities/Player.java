package com.game.Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Mob {

    public Player(Sprite sprite) {
        super(sprite);
        this.setX(0);
        this.setY(64);
        x=0;
        y=64;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            x+=2;
            setX(x);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            x+=-2;
            setX(x);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            y+=2;
            setY(y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            //atak???
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F)) {
            //atak2???
        }
    }


}
