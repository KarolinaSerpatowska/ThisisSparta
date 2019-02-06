package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

public class Enemy extends Mob {

    public Enemy(Sprite sprite, World world,float x, float y) {
        super(sprite, world);
        this.x=x;
        this.y=y;
        this.setX(x);
        this.setY(y);
        collider=new Rectangle(x,y,64,64);
        width=sprite.getWidth();
        height=sprite.getHeight();
    }

    @Override
    public void update(float delta) {
        super.update(delta);





    }

    public Rectangle getTop(int i){
        return new Rectangle(x+20, y+61, width-45,height/16);
    }

    public Rectangle getLeft(int i){
        return new Rectangle(x, y, width/64,height-12);
    }

    public Rectangle getRight(int i){
        return new Rectangle(x+35, y, width/64,height-12);
    }
}
