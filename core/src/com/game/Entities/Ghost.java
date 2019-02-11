package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ghost extends Sprite {

    TextureRegion currentFrame;
    private float stateTimer;
    private Animation<TextureRegion> idle;
    private Texture idleSheet;

    public Ghost(Sprite sprite) {
        super(sprite);
        this.setPosition(1772, 64);
        stateTimer=0;
        idleSheet=new Texture("wizard idle.png");
        TextureRegion[][] tmp = TextureRegion.split(idleSheet,idleSheet.getWidth() / 10,idleSheet.getHeight());
        TextureRegion[] idleFrames = new TextureRegion[10];
        int index = 0;
            for (int j = 0; j < 10; j++) {
                idleFrames[index++] = tmp[0][j];
            }

        idle = new Animation<TextureRegion>(0.1f, idleFrames);
    }

    @Override
    public void draw(Batch batch) {
        update();
        batch.draw(currentFrame, this.getX(),this.getY());
    }

    public void update() {
        currentFrame = idle.getKeyFrame(stateTimer, true);
        setRegion(currentFrame);
        stateTimer += Gdx.graphics.getDeltaTime();
    }


}
