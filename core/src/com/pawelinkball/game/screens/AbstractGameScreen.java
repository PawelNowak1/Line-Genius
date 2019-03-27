package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Pawel on 07.07.2017.
 */
public abstract class AbstractGameScreen implements Screen{
    protected Game game;
    protected static boolean arcadeModeOn = false;
    protected  SpriteBatch batch;

    //protected static int currentLevelPlayed = 1;

    public AbstractGameScreen(Game game){
        batch = new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
        this.game = game;
    }

    public abstract void render(float deltaTime);
    public abstract void resize(int width, int height);
    public abstract void show();
    public abstract void hide();
    public abstract void pause();

    public void resume(){}
    public abstract void dispose();
}
