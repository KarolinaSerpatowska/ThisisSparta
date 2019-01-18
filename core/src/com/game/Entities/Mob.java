package com.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Levels.GameState;

public abstract class Mob {

    protected Sprite sprite;
    protected GameState game;
    protected float dx,dy;
    protected double maxDY;
    protected double gravity;
    protected boolean falling;
    protected boolean canjump;
    protected boolean moving;
    protected boolean attack;
    protected boolean specialAttack;
    protected int hp;
    protected boolean isHit;
    protected int damage;
    protected int specialdmg;
    protected boolean canHit;


    public Mob(Sprite sprite, GameState game){
        this.sprite=sprite;
        this.game=game;
        falling=true;
        gravity=0.5;
        maxDY=6;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
