package com.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.game.ThisisSparta;

public class Hud {

    public Stage stage;
    private Viewport viewport;

    private int score;
    private int hp;

    Label pointsLabel;
    Label hpPoints;
    Label scoreLabel;
    Label hpLabel;

    public Hud(SpriteBatch sb){
        score=0;
        hp=700;
        viewport =new FitViewport(ThisisSparta.WIDTH,ThisisSparta.HEIGHT, new OrthographicCamera());
        stage=new Stage(viewport,sb);

        Table table=new Table();
        table.top();
        table.setFillParent(true);

        pointsLabel=new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        hpPoints=new Label(String.format("%03d",hp),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        scoreLabel=new Label("SCORE",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        hpLabel=new Label("HP ",new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        table.add(hpLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.row();
        table.add(hpPoints).expandX();
        table.add(pointsLabel).expandX();

        pointsLabel.setFontScale(4);
        hpPoints.setFontScale(4);
        scoreLabel.setFontScale(4);
        hpLabel.setFontScale(4);

        stage.addActor(table);
    }

    public void update(float delta){
       hpPoints.setText(hp);
       pointsLabel.setText(score);
    }

    public void setHp(int hp) {
        if(hp<0) hp=0;
        this.hp = hp;

    }

    public void setScore(int score) {
        this.score = score;
    }
}
