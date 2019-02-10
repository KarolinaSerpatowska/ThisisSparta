package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Levels.World;

public class Enemy extends Mob {

    private Player player;
    private float timer;

    public Enemy(Sprite sprite, World world, float x, float y, Player player) {
        super(sprite, world);
        this.x = x;
        this.y = y;
        this.setX(x);
        this.setY(y);
        dmg = 100;
        hp=30;
        width = sprite.getWidth();
        height = sprite.getHeight();
        this.player = player;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (hasHorizontalCollisionwithPlayer()){
            timer+= Gdx.graphics.getDeltaTime();
            attack();
        }

        if (x - player.getX() <= 300) {
            if (player.getX()+64 < x) dx = -2;
            if (x - player.getX() <= 50) dx = 0;
        }
        else dx = 0;

    }

    public void attack() {
        if(timer>1) {
            timer = 0;
            player.setHp(player.getHp() - dmg);
        }
    }

    private boolean hasHorizontalCollisionwithPlayer() {
        if (this.getLeft().overlaps(player.getRight()) && dx <= 0) return true;
        if (this.getRight().overlaps(player.getLeft()) && dx >= 0) return true;
        return false;
    }
}