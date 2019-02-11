package com.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.game.Levels.World;

import java.util.ArrayList;

public class Player extends Mob {

    private enum State{IDLE_RIGHT, IDLE_LEFT, RUN_LEFT, RUN_RIGHT, ATTACK_RIGHT, ATTACK_LEFT}
    private State currentState;
    private State previousState;
    private float stateTimer;
    private ArrayList<Enemy> enemies;
    private Enemy curEnemy;
    private float timer;
    private int score=0;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> idleRight;
    private Animation<TextureRegion> idleLeft;
    private Animation<TextureRegion> runRight;
    private Animation<TextureRegion> runLeft;
    private Animation<TextureRegion> attackRight;
    private Animation<TextureRegion> attackLeft;
    private Texture runRightSheet;
    private TextureRegion idleRightSheet;
    private TextureRegion idleLeftSheet;
    private Texture runLeftSheet;
    private Texture attackRightSheet;
    private Texture attackLeftSheet;

    public Player(Sprite sprite, World world) {
        super(sprite,world);
        this.setX(0);
        this.setY(64);
        x=0;
        y=64;
        hp=700;
        dmg=10;
        width=sprite.getWidth();
        height=sprite.getHeight();
        currentState=State.IDLE_RIGHT;
        previousState=State.IDLE_RIGHT;
        stateTimer=0;
        runRightSheet=new Texture("spartan run right.png");
        idleRightSheet=new TextureRegion(new Texture("spartanidleright.png"));
        runLeftSheet=new Texture("spartan run left.png");
        idleLeftSheet=new TextureRegion(new Texture("spartanidleleft.png"));
        attackRightSheet=new Texture("spartanattackright.png");
        attackLeftSheet=new Texture("spartanattackleft.png");

        TextureRegion[][] tmp = TextureRegion.split(runRightSheet,runRightSheet.getWidth() / 6,runRightSheet.getHeight());
        TextureRegion[] walkrightFrames = new TextureRegion[6];
        int index = 0;
        for (int j = 0; j < 6; j++) {
            walkrightFrames[index++] = tmp[0][j];
        }
        tmp = TextureRegion.split(runLeftSheet,runLeftSheet.getWidth() / 6,runLeftSheet.getHeight());
        TextureRegion[] walkleftFrames = new TextureRegion[6];
        index = 0;
        for (int j = 0; j < 6; j++) {
            walkleftFrames[index++] = tmp[0][j];
        }
        tmp = TextureRegion.split(attackRightSheet,attackRightSheet.getWidth() / 2,attackRightSheet.getHeight());
        TextureRegion[] attackrightframes = new TextureRegion[2];
        index = 0;
        for (int j = 0; j < 2; j++) {
            attackrightframes[index++] = tmp[0][j];
        }
        tmp = TextureRegion.split(attackLeftSheet,attackLeftSheet.getWidth() / 2,attackLeftSheet.getHeight());
        TextureRegion[] attackleftframes = new TextureRegion[2];
        index = 0;
        for (int j = 0; j < 2; j++) {
            attackleftframes[index++] = tmp[0][j];
        }

        runRight = new Animation<TextureRegion>(0.08f, walkrightFrames);
        runLeft=new Animation<TextureRegion>(0.08f, walkleftFrames);
        idleRight=new Animation<TextureRegion>(0.1f,idleRightSheet);
        idleLeft=new Animation<TextureRegion>(0.1f,idleLeftSheet);
        attackRight=new Animation<TextureRegion>(0.08f, attackrightframes);
        attackLeft=new Animation<TextureRegion>(0.08f, attackleftframes);
    }

    @Override
    public void update(float delta) {

        super.update(delta);
        getFrame();
        setRegion(currentFrame);
        stateTimer += Gdx.graphics.getDeltaTime();

        if(hasHorizontalCollisionwithEnemy()) canHit=true;
        else canHit=false;

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveright=true;
            dx=2;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && x>(-32)) {
            moveleft=true;
            dx=-2;
        }
        else {
            moveright=false;
            moveleft=false;
            dx=0;
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            timer+= Gdx.graphics.getDeltaTime();
            dx=0;
            if(timer>0.1){
            attack=true;
            attack();
            timer=0;
            }
        }
        else attack=false;

    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        batch.draw(currentFrame, x,y);
    }

    public void getFrame(){
        previousState=currentState;
        currentState=getState();
        switch (currentState){
            case RUN_RIGHT:
                currentFrame = runRight.getKeyFrame(stateTimer, true);
                break;
            case RUN_LEFT:
                currentFrame=runLeft.getKeyFrame(stateTimer,true);
                break;
            case IDLE_RIGHT:
                currentFrame=idleRight.getKeyFrame(stateTimer,true);
                break;
            case IDLE_LEFT:
                currentFrame=idleLeft.getKeyFrame(stateTimer,true);
                break;
            case ATTACK_LEFT:
                currentFrame=attackLeft.getKeyFrame(stateTimer,true);
                break;
            case ATTACK_RIGHT:
                    currentFrame=attackRight.getKeyFrame(stateTimer,true);
                break;
        }
    }

    public State getState(){
        if (attack && (previousState==State.RUN_RIGHT || previousState==State.ATTACK_RIGHT || previousState==State.IDLE_RIGHT)) return State.ATTACK_RIGHT;
        else if(attack && (previousState==State.RUN_LEFT || previousState==State.ATTACK_LEFT || previousState==State.IDLE_LEFT)) return State.ATTACK_LEFT;
        else if(dx==0 && (previousState==State.RUN_RIGHT || previousState==State.ATTACK_RIGHT || previousState==State.IDLE_RIGHT)) return State.IDLE_RIGHT;
        else if(dx==0 && (previousState==State.RUN_LEFT || previousState==State.ATTACK_LEFT || previousState==State.IDLE_LEFT)) return State.IDLE_LEFT;
        else if(moveright) return State.RUN_RIGHT;
        else if(moveleft) return State.RUN_LEFT;
        else return State.IDLE_RIGHT;
    }

    @Override
    public void move() {
        if(!hasHorizontalCollisionwithEnemy()){
           x+=dx;
           setX(x);
        }
        if(!hasVerticalCollisionwithGround()) {
            y+=dy;
            setY(y);
        }
    }

    private void attack(){
        if(canHit){
            curEnemy.setHp(curEnemy.getHp()-dmg);
        }
    }

    private boolean hasHorizontalCollisionwithEnemy(){
        for(int i=0; i<enemies.size();i++) {
            if(this.getLeft().overlaps(enemies.get(i).getRight()) && dx<=0 && !enemies.get(i).isdead) {
                curEnemy=enemies.get(i);
                return true;
            }
            if(this.getRight().overlaps(enemies.get(i).getLeft()) && dx>=0 && !enemies.get(i).isdead) {
                curEnemy=enemies.get(i);
                return true;
            }
        }
        return false;
    }

    public void setScore(boolean isboss){
        if(isboss) score+=50;
        else score+=10;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getScore() {
        return score;
    }

    @Override
    public Rectangle getLeft(){
        return new Rectangle(x+20, y+4, 4,height-8);
    }

    @Override
    public Rectangle getRight(){
        return new Rectangle(x+width-20, y+4, 20,height-8);
    }

    @Override
    public Rectangle getBot(){
        return new Rectangle(x+20, y, width-20,4);
    }

}



