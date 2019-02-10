package com.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.ThisisSparta;

public class MainMenuState implements Screen {

    private ThisisSparta game;
    private Stage stage;
    private Skin skin;
    private TextButton PLAYbutton;
    private TextButton EXITbutton;
    private TextButton ControlsButton;
    private Table menuTable;
    private Label title;

    public MainMenuState(ThisisSparta game) {

        this.game=game;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        menuTable=new Table();
        menuTable.top();
        menuTable.setFillParent(true);

        PLAYbutton = new TextButton("Play", skin);
        PLAYbutton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               game.changeScreen("game");
            }
        });
        EXITbutton=new TextButton("Exit", skin);
        EXITbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.changeScreen("exit");
            }
        });
        ControlsButton=new TextButton("Controls", skin);
        ControlsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.changeScreen("controls");
            }
        });

        title=new Label("This is Sparta",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        title.setFontScale(10);
        menuTable.add(title).padTop(10);
        menuTable.row();

        menuTable.add(PLAYbutton).padTop(50);
        menuTable.row();

        menuTable.add(ControlsButton).padTop(50);
        menuTable.row();

        menuTable.add(EXITbutton).padTop(50);
        menuTable.row();


        stage.addActor(menuTable);

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
