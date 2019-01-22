package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Mob extends Sprite {

    protected int width;
    protected int height;

    protected float x;
    protected float y;

    public Mob(Sprite sprite){
        super(sprite);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
