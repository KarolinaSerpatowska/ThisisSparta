package com.game.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class World {

    private Texture background;
    private ArrayList<Sprite> ground;
    private int ground_size;

    public World() {
        background=new Texture("background.png");
        ground=new ArrayList<Sprite>();
        float x=0;
        for(int i=0;i<30;i++){
            ground.add(new Sprite(new Texture("ground.png")));
            ground.get(i).setPosition(x,0);
            x+=64;
        }
        ground_size=ground.size();
    }

    public Texture getBackground() {
        return background;
    }

    public ArrayList<Sprite> getGround() {
        return ground;
    }

    public int getGround_size() {
        return ground_size;
    }

    public  Rectangle getColliderGround(){ //ziemia
        return new Rectangle(0, 30, 1920,34);
    }

}
