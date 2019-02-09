package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

import java.util.ArrayList;

public class Player extends Mob {

    private ArrayList<Enemy> enemies;
    private Enemy curEnemy;
    private float timer;

    public Player(Sprite sprite, World world) {
        super(sprite,world);
        this.setX(0);
        this.setY(64);
        x=0;
        y=64;
        hp=700;
        dmg=10;
        width=sprite.getWidth();
        height=sprite.getHeight();
    }

    @Override
    public void update(float delta) {

        super.update(delta);

        if(hasHorizontalCollisionwithEnemy()) canHit=true;
        else canHit=false;

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx=2;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx=-2;
        }
        else dx=0;

        if(Gdx.input.isKeyPressed(Input.Keys.W)) jump(10);

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            timer+= Gdx.graphics.getDeltaTime();
            if(timer>0.1){
            attack=true;
            attack();
            timer=0;
            }
        }
        else attack=false;

    }

    @Override
    public void move() {
        if(!hasHorizontalCollisionwithEnemy()){
           x+=dx;
           setX(x);
        }
        if(!hasVerticalCollisionwithGround() && !hasVerticalCollisionwithEnemy()) {
            y+=dy;
            setY(y);
        }
    }

    private void attack(){
        if(canHit){
            curEnemy.setHp(curEnemy.getHp()-dmg);
            System.out.println(curEnemy.getHp());
        }
    }


    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    private boolean hasHorizontalCollisionwithEnemy(){
        for(int i=0; i<enemies.size();i++) {
            if(this.getLeft().overlaps(enemies.get(i).getRight()) && dx<=0) {
                curEnemy=enemies.get(i);
                return true;
            }
            if(this.getRight().overlaps(enemies.get(i).getLeft()) && dx>=0) {
                curEnemy=enemies.get(i);
                return true;
            }
        }
        return false;
    }

    protected boolean hasVerticalCollisionwithEnemy(){
        for(int i=0; i<enemies.size();i++) {
            if((this.getBot().overlaps(enemies.get(i).getTop()) && dy<0)) {
                dy = 0;
                enemies.get(i).setDx(0);
                canjump=true;
                falling=false;
                return true;
            } else falling=true;
        }
        return false;
    }

    @Override
    public Rectangle getLeft(){
        return new Rectangle(x+20, y+4, 4,height-8);
    }

    @Override
    public Rectangle getRight(){
        return new Rectangle(x+width-20, y+4, 20,height-8);
    }

    @Override
    public Rectangle getTop(){
        return new Rectangle(x+20, y+width-4, width-20,4);
    }

    @Override
    public Rectangle getBot(){
        return new Rectangle(x+20, y, width-20,4);
    }

}



