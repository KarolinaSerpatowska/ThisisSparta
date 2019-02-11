package com.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.Levels.World;

public class Boss extends Enemy {
    public Boss(Sprite sprite, World world, float x, float y, Player player) {
        super(sprite, world, x, y, player);
        dmg=100;
        hp=50;
        spritesheet=new Texture("_Spritesheet.png");
    }
}
