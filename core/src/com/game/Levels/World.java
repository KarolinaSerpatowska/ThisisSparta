package com.game.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class World {

    private Texture background;
    private ArrayList<Sprite> ground;
    private  ArrayList<Rectangle> colliders;

    public World() {
        background=new Texture("background.png");
        ground=new ArrayList<Sprite>();
        colliders=new ArrayList<Rectangle>();
        float x=0;
        for(int i=0;i<20;i++){
            ground.add(new Sprite(new Texture("ground.png")));
            ground.get(i).setPosition(x,0);
            x+=60;
            colliders.add(new Rectangle(ground.get(i).getX(),ground.get(i).getY(), ground.get(i).getWidth(),ground.get(i).getHeight()));
        }
    }

    public Texture getBackground() {
        return background;
    }

    public ArrayList<Sprite> getGround() {
        return ground;
    }

    public ArrayList<Rectangle> getColliders() {
        return colliders;
    }
}
