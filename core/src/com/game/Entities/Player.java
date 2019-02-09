package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

import java.util.ArrayList;

public class Player extends Mob {

    private ArrayList<Enemy> enemies;

    public Player(Sprite sprite, World world) {
        super(sprite,world);
        this.setX(0);
        this.setY(64);
        x=0;
        y=64;
        hp=700;
        dmg=10;
        collider=new Rectangle(x,y,64,64);
        width=sprite.getWidth();
        height=sprite.getHeight();
    }

    @Override
    public void update(float delta) {

        super.update(delta);

        System.out.println(x);
        System.out.println(y);

        //System.out.println(hp);

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx=2;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx=-2;
        }
        else dx=0;

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            jump(10);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            //atak???
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F)) {
            //atak2???
        }


    }

    @Override
    public void move() {
        if(!hasHorizontalCollision() && !hasHorizontalCollisionwithEnemy()){
            x+=dx;
            setX(x);
        }
        if(!hasVerticalCollision() && !hasVerticalCollisionwithEnemy()) {
            y+=dy;
            setY(y);
        }
    }



    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    private boolean hasHorizontalCollisionwithEnemy(){
        for(int i=0; i<enemies.size();i++) {
            if(collider.overlaps(enemies.get(i).getRight()) && dx<0) {
                dx = 0;
                return true;
            }
            if(collider.overlaps(enemies.get(i).getLeft()) && dx>0) {
                dx = 0;
                return true;
            }
        }
        return false;
    }

    protected boolean hasVerticalCollisionwithEnemy(){
        for(int i=0; i<enemies.size();i++) {
            if((collider.overlaps(enemies.get(i).getTop()) && dy<0)) {
                dy = 0;
                canjump=true;
                falling=false;
                return true;
            } else falling=true;
        }
        return false;
    }

    public Rectangle getLeft(){
        return new Rectangle(x, y, width/64,height-12);
    }

    public Rectangle getRight(){
        return new Rectangle(x+35, y, width/64,height-12);
    }


    }



