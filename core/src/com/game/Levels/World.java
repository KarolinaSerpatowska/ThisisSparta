package com.game.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class World {

    private Texture background;
    private ArrayList<Sprite> ground;

    public World() {
        background=new Texture("background.png");
        ground=new ArrayList<Sprite>();
        for(int i=0;i<20;i++){
            ground.add(new Sprite(new Texture("ground.png")));
        }
    }

    public Texture getBackground() {
        return background;
    }

    public ArrayList<Sprite> getGround() {
        return ground;
    }
}
