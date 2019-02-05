package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

import java.util.ArrayList;

public abstract class Mob extends Sprite {

    protected float gravity=0.5f;
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    protected float width;
    protected float height;
    protected World world;
    protected Rectangle collider;
    protected boolean canjump=true;
    protected boolean canMoveLeft=true;
    protected boolean canMoveRight=true;

    public Mob(Sprite sprite,World world){
        super(sprite);
        width=sprite.getWidth();
        height=sprite.getHeight();
        this.world=world;
        collider=new Rectangle(x,y,64,64);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {

        collider.setPosition(x,y);
        move();
        if(!checkCollisionGround()) falling();
        if(checkCollisionBot()) canjump=false;
        else if(!checkCollisionBot()) canjump=true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(){
        if(!hasHorizontalCollision()) {
            x += dx;
            setX(x);
        }
    }

    protected void falling(){
        y-=gravity;
        setY(y);
    }

    protected boolean checkCollisionGround() {
        for(int i=0; i<world.getGround_size();i++){
            if(collider.overlaps(world.getTop(i))){
                return true;
            }
        }
        return false;
    }

    protected boolean checkCollisionBot() {
        for(int i=0; i<world.getGround_size();i++){
            if(collider.overlaps(world.getBot(i))){
                return true;
            }
        }
        return false;
    }

    protected boolean hasHorizontalCollision(){
        for(int i=0; i<world.getGround_size();i++) {
            if(collider.overlaps(world.getRight(i)) && dx<0) {
                dx = 0;
                return true;
            }
            if(collider.overlaps(world.getLeft(i)) && dx>0) {
                dx = 0;
                return true;
            }
        }
        return false;
    }

}
