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
    protected float width;
    protected float height;
    protected World world;
    protected Rectangle collider;
//    protected boolean canfall=true;

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
        if(!checkCollision()) falling();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    protected void falling(){
        y-=gravity;
        setY(y);
    }

    protected boolean checkCollision() {
        for(int i=0; i<world.getColliders().size();i++){
            if(collider.overlaps(world.getColliders().get(i))){
                return true;
            }
        }
        return false;
    }

}
