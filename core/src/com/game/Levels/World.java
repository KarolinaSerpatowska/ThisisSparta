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

    public Rectangle getTop(int i){
        return new Rectangle(ground.get(i).getX()+20, ground.get(i).getWidth()-6, ground.get(i).getWidth()-45,ground.get(i).getHeight()/16);
    }

    public Rectangle getBot(int i){
        return new Rectangle(ground.get(i).getX()+20, ground.get(i).getY(), ground.get(i).getWidth()-45,ground.get(i).getHeight()/16);
    }

    public Rectangle getLeft(int i){
        return new Rectangle(ground.get(i).getX(), ground.get(i).getY(), ground.get(i).getWidth()/64,ground.get(i).getHeight()-12);
    }

    public Rectangle getRight(int i){
        return new Rectangle(ground.get(i).getX()+35, ground.get(i).getY(), ground.get(i).getWidth()/64,ground.get(i).getHeight()-12);
    }
}
