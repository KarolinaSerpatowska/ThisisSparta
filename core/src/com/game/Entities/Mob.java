package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

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
    protected boolean falling=true;

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
        falling();

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
        if(!hasVerticalCollision()){
            y+=dy;
            setY(y);
        }
    }

    protected void jump(double jumpHeight){
        if(canjump){
            dy+=jumpHeight;
            canjump=false;
        }
    }

    protected void falling(){
        if(falling)  dy-=gravity;
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

    protected boolean hasVerticalCollision(){
        for(int i=0; i<world.getGround_size();i++) {
            if(collider.overlaps(world.getTop(i)) && dy<0) {
                dy = 0;
                canjump=true;
                falling=false;
                return true;
            } else falling=true;
            if(collider.overlaps(world.getBot(i)) && dy>0) {
                dy = 0;
                return true;
            }
        }
        return false;
    }

}
