package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.ThisisSparta;

public class ControlsScreen implements Screen {

    private ThisisSparta game;
    private Stage stage;
    private Table table;
    private TextButton button;
    private Skin skin;
    private Label first;
    private Label second;

    public ControlsScreen(ThisisSparta game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        table=new Table();
        table.top();
        table.setFillParent(true);
        button = new TextButton("Back", skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.changeScreen("mainmenu");
            }
        });

        first=new Label("Movement: A - left D - right W - jump ",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        second=new Label("Attack: left mouse button ",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        first.setFontScale(5);
        second.setFontScale(5);
        table.add(first).padTop(10);
        table.row();
        table.add(second).padTop(10);
        table.row();

        table.add(button).padTop(10);
        table.row();


        stage.addActor(table);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
