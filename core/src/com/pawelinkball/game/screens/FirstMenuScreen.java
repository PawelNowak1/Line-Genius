package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.pawelinkball.game.InkBall;

/**
 * Created by Pawel on 07.07.2017.
 */
public class FirstMenuScreen extends AbstractGameScreen {

    public static final float VIEWPORT_MENU_HEIGHT = 800.0f;
    public static final float VIEWPORT_MENU_WIDTH = 480.0f;

    private Stage stage;
    private Skin skinMenu;

    private Button btnPlay;
    private Button btnOptions;
    private Button btnAuthor;

    public FirstMenuScreen(Game game){
        super(game);

    }

    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.BACK))
            Gdx.app.exit();
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(VIEWPORT_MENU_WIDTH, VIEWPORT_MENU_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    void rebuildStage(){
        skinMenu = new Skin(Gdx.files.internal("images/snake.json"), new TextureAtlas("images/snake.atlas"));
        buildMenuLayer();
    }

    void buildMenuLayer(){
        Table table = new Table();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(VIEWPORT_MENU_WIDTH, VIEWPORT_MENU_HEIGHT);
        stack.add(table);
        btnPlay = new Button(skinMenu, "play");
        table.setFillParent(true);
        table.top().pad(100);
        table.add(btnPlay);
        btnPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                arcadeModeOn();
            }
        });
        btnOptions = new Button(skinMenu, "options");
        table.row().pad(50);
        table.add(btnOptions);
        btnOptions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onPlayClicked();
            }
        });
        btnAuthor = new Button(skinMenu, "author");
        table.row();
        table.add(btnAuthor);
        stage.addActor(table);
    }

    private void onPlayClicked(){
        game.setScreen(new MainMenuScreen(game));
    }

    private void arcadeModeOn(){
        arcadeModeOn = true;
        Preferences preferences = Gdx.app.getPreferences("game");
        game.setScreen(InkBall.arcadeManager.getLevelClass(game, preferences.getInteger("ArcadeLevel")));
    }
    @Override
    public void dispose() {
        stage.dispose();
        skinMenu.dispose();
    }
}
