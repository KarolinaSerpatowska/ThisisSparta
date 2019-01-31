package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Mob extends Sprite {

    protected float gravity=0.5f;
    protected float x;
    protected float y;
    protected boolean falling=false;
    protected boolean canjump=true;

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
