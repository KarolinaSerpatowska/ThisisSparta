package com.game.Levels;

public interface State {

    public void init();
    public void enter();
    public void tick(StateManager stateManager);
    public void render(float delta);
    public void exit();
    public String getName();
}
