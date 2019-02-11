package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.Levels.GameState;
import com.game.Levels.World;

import java.util.ArrayList;

public class Enemy extends Mob {

    private Player player;
    private float timer;

    protected enum State{IDLE,RUN, ATTACK, DEAD}
    public State currentState;
    public State previousState;
    TextureRegion currentFrame;
    protected Animation<TextureRegion> idle;
    protected Animation<TextureRegion> run;
    protected Animation<TextureRegion> attackanim;
    protected Animation<TextureRegion> deadanim;
    private float stateTimer;
    protected Texture spritesheet;
    protected boolean settoDestroy=false;
    protected GameState gameState;
    private float deadtimer;

    public Enemy(Sprite sprite, World world, float x, float y, Player player, boolean boss, GameState gameState) {
        super(sprite, world);
        this.x = x;
        this.y = y;
        this.setX(x);
        this.setY(y);
        this.gameState=gameState;
        dmg = 50;
        hp=30;
        width = sprite.getWidth();
        height = sprite.getHeight();
        this.player = player;
        if(boss) {
            dmg=100;
            hp=50;
        }

        currentState= State.IDLE;
        previousState= State.IDLE;
        stateTimer=0;
        if(boss) spritesheet=new Texture("HeavyBandit_Spritesheet.png");
        else spritesheet=new Texture("LightBandit_Spritesheet.png");

        TextureRegion[][] tmp = TextureRegion.split(spritesheet,spritesheet.getWidth() / 8,spritesheet.getHeight() /7);
        TextureRegion[] walkFrames = new TextureRegion[8];
        int index = 0;
            for (int j = 0; j < 8; j++) {
                walkFrames[index++] = tmp[1][j];
            }

        tmp = TextureRegion.split(spritesheet,spritesheet.getWidth() / 8,spritesheet.getHeight() /7);
        TextureRegion[] idleFrames = new TextureRegion[4];
        index = 0;
        for (int j = 0; j < 4; j++) {
            idleFrames[index++] = tmp[0][j];
        }
        tmp = TextureRegion.split(spritesheet,spritesheet.getWidth() / 8,spritesheet.getHeight() /7);
        TextureRegion[] attackFrames = new TextureRegion[8];
        index = 0;
        for (int j = 0; j < 8; j++) {
            attackFrames[index++] = tmp[2][j];
        }
        tmp = TextureRegion.split(spritesheet,spritesheet.getWidth() / 8,spritesheet.getHeight() /7);
        TextureRegion[] deadFrames = new TextureRegion[8];
        index = 0;
        for (int j = 7; j >=0; j--) {
            deadFrames[index++] = tmp[3][j];
        }

        run = new Animation<TextureRegion>(0.08f, walkFrames);
        idle=new Animation<TextureRegion>(0.08f, idleFrames);
        attackanim=new Animation<TextureRegion>(0.08f, attackFrames);
        deadanim=new Animation<TextureRegion>(0.01f, deadFrames);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        getFrame();
        setRegion(currentFrame);
        stateTimer += Gdx.graphics.getDeltaTime();

        if (hasHorizontalCollisionwithPlayer()){
            timer+= Gdx.graphics.getDeltaTime();
            if(!isdead) {
                attack = true;
                attack();
            }
        }
        else attack=false;

        if (java.lang.Math.abs(x - player.getX()) <= 250 && !isdead) {
            if (player.getX()+64 < x && java.lang.Math.abs(y-player.getY())<=5) dx = -2;
            if (java.lang.Math.abs(x - player.getX()) <= 50) dx = 0;
        }
        else dx = 0;

        if(isdead) settoDestroy=true;
        else settoDestroy=false;

        if(settoDestroy){
            deadtimer+= Gdx.graphics.getDeltaTime();
            if(deadtimer>0.5) {
                player.setScore();
                gameState.removeEnemy(this);
            }
        }

    }

    public void getFrame(){
        previousState=currentState;
        currentState=getState();
        switch (currentState){
            case RUN:
                currentFrame = run.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
                currentFrame=idle.getKeyFrame(stateTimer,true);
                break;
            case ATTACK:
                currentFrame=attackanim.getKeyFrame(stateTimer,true);
                break;
            case DEAD:
                currentFrame=deadanim.getKeyFrame(stateTimer,false);
                break;
        }
    }

    public State getState(){
        if(isdead) return State.DEAD;
        else if(moveleft) return State.RUN;
        else if(attack) return State.ATTACK;
        else return State.IDLE;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        batch.draw(currentFrame,x,y);
    }

    public void attack() {
        if(timer>1) {
            timer = 0;
            player.setHp(player.getHp() - dmg);
        }
    }

    public boolean isdeadanimend(){
        if(deadanim.isAnimationFinished(stateTimer)) return true;
        else return false;
    }

    private boolean hasHorizontalCollisionwithPlayer() {
        if (this.getLeft().overlaps(player.getRight()) && dx <= 0) return true;
        if (this.getRight().overlaps(player.getLeft()) && dx >= 0) return true;
        return false;
    }
}