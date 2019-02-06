package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

public class Enemy extends Mob {

    private Player player;

    public Enemy(Sprite sprite, World world,float x, float y, Player player) {
        super(sprite, world);
        this.x=x;
        this.y=y;
        this.setX(x);
        this.setY(y);
        collider=new Rectangle(x,y,64,64);
        width=sprite.getWidth();
        height=sprite.getHeight();
        this.player=player;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(x-player.getX()<=300){
            dx=-2;
            if(x-player.getX()<=50) dx=0;
        }
        else dx=0;


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
