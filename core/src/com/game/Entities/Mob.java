package com.game.Entities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Mob implements ApplicationListener {

    protected Sprite sprite;
    protected float x;
    protected float y;
    protected Rectangle bounds;

    public Mob(Sprite sprite,float x, float y, float width, float height){
        this.sprite=sprite;
        this.x=x;
        this.y=y;

        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
