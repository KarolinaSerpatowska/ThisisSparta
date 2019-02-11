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
    protected int hp;
    protected int dmg;
    protected World world;
    protected boolean falling=true;
    protected boolean isdead=false;
    protected boolean canHit=false;
    protected boolean attack=false;
    protected boolean moveleft=false;
    protected boolean moveright=false;

    public Mob(Sprite sprite,World world){
        super(sprite);
        width=sprite.getWidth();
        height=sprite.getHeight();
        this.world=world;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {
        if(dx>0) moveright=true;
        else if(dx<0) moveleft=true;
        else {
            moveright=false;
            moveleft=false;
        }
        move();
        falling();

        if(hp<=0) {
            isdead = true;
        }

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(){
        x+=dx;
        setX(x);

        if(!hasVerticalCollisionwithGround()){
            y+=dy;
            setY(y);
        }
    }

    protected void falling(){
        if(falling)  dy-=gravity;
    }


    protected boolean hasVerticalCollisionwithGround(){
            if(this.getBot().overlaps(world.getColliderGround()) && dy<0) {
                dy = 0;
                falling=false;
                return true;
            } else falling=true;
        return false;
    }

    public Rectangle getLeft(){
        return new Rectangle(x, y, 4,height);
    }

    public Rectangle getRight(){
        return new Rectangle(x+width-4, y, 4,height);
    }

    public Rectangle getBot(){
        return new Rectangle(x, y, width-20,4);
    }

    public boolean isIsdead() {
        return isdead;
    }
}
