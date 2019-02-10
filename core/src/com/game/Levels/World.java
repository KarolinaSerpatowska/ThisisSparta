package com.game.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class World {

    private Texture background;
    private ArrayList<Sprite> ground;
    private int ground_size;
    private ArrayList<Sprite> tiles;

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

        tiles=new ArrayList<Sprite>();
        tiles.add(new Sprite(new Texture("ground.png")));
        tiles.get(0).setPosition(400,64);
        tiles.add(new Sprite(new Texture("ground.png")));
        tiles.get(1).setPosition(464,128);
        tiles.add(new Sprite(new Texture("ground.png")));
        tiles.get(2).setPosition(464,64);
        tiles.add(new Sprite(new Texture("ground.png")));
        tiles.get(3).setPosition(528,64);
        tiles.add(new Sprite(new Texture("ground.png")));
        tiles.get(4).setPosition(528,128);
        tiles.add(new Sprite(new Texture("ground.png")));
        tiles.get(5).setPosition(528,192);
        x=592;
        for(int i=6; i<15;i++){
            tiles.add(new Sprite(new Texture("ground.png")));
            tiles.get(i).setPosition(x,192);
            x+=64;
        }

    }

    public ArrayList<Sprite> getTiles() {
        return tiles;
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


    public Rectangle getTop(int i){
        return new Rectangle(tiles.get(i).getX()+20, tiles.get(i).getY()+tiles.get(i).getWidth()-4, tiles.get(i).getWidth()-20,4);
    }

    public Rectangle getBot(int i){
        return new Rectangle(tiles.get(i).getX(), tiles.get(i).getY(), tiles.get(i).getWidth(),4);
    }

    public Rectangle getLeft(int i){
        return new Rectangle(tiles.get(i).getX(), tiles.get(i).getY()+4, 4,tiles.get(i).getHeight()-8);
    }

    public Rectangle getRight(int i){
        return new Rectangle(tiles.get(i).getX()+tiles.get(i).getWidth(), tiles.get(i).getY()+4, 4,tiles.get(i).getHeight()-8);
    }

}
