package com.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.game.ThisisSparta;

public class Hud {

    public Stage stage;
    private Viewport viewport;

    private int score;
    private int hp;

    Label countupLabel;
    Label scoreLabel;
    Label timeLabel;
    Label hpLabel;

    public Hud(SpriteBatch sb){
        score=300;
        hp=700;

        viewport =new FitViewport(ThisisSparta.WIDTH,ThisisSparta.HEIGHT, new OrthographicCamera());
        stage=new Stage(viewport,sb);

        Table table=new Table();
        table.top();
        table.setFillParent(true);

        countupLabel=new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        scoreLabel=new Label(String.format("%03d",hp),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        timeLabel=new Label("SCORE",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        hpLabel=new Label("HP ",new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        table.add(hpLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(countupLabel).expandX();

        stage.addActor(table);
    }

}
