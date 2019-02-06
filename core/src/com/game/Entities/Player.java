package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

public class Player extends Mob {

    public Player(Sprite sprite, World world) {
        super(sprite,world);
        this.setX(0);
        this.setY(64);
        x=0;
        y=64;
        collider=new Rectangle(x,y,64,64);
        width=sprite.getWidth();
        height=sprite.getHeight();
    }

    @Override
    public void update(float delta) {

        collider.setPosition(x,y);

        move();
        falling();

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx=2;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx=-2;
        }
        else dx=0;

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            jump(10);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            //atak???
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F)) {
            //atak2???
        }


    }






    }



