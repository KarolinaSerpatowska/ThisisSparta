package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Levels.GameState;

public class Player extends Mob {

    public Player(Sprite sprite, GameState game) {
        super(sprite, game);
    }

    public void render(){

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx+=2;
            sprite.setX(sprite.getX()+dx);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            dx+=-2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            dy+=-2;
        }
    }

}
