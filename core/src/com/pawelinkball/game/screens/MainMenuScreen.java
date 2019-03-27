package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.utils.Assets;

/**
 * Created by Pawel on 07.07.2017.
 */
public class MainMenuScreen extends AbstractGameScreen {

    public static final float VIEWPORT_MENU_HEIGHT = 800.0f;
    public static final float VIEWPORT_MENU_WIDTH = 480.0f;
    private Skin skinLevel;
    private Stage stage;

    private Array<TextButton> buttons;
    Preferences pref;

    public MainMenuScreen(Game game){
        super(game);
    }

    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            game.setScreen(new StartLevel(game));
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
        //image = new Image(new Texture("check.png"));
        stage = new Stage(new StretchViewport(VIEWPORT_MENU_WIDTH, VIEWPORT_MENU_HEIGHT)); // być może co innego ScreenViewPort?
        Gdx.input.setInputProcessor(stage);
        buttons = new Array<TextButton>();
        AbstractLevelClass.arcadeModeOn = false;
        skinLevel = new Skin(Gdx.files.internal("game.json"), new TextureAtlas("game.atlas"));
        pref = Gdx.app.getPreferences("game");
        buildStage();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    private void buildStage(){
        final Table scrollTable = new Table();
        TextButton.TextButtonStyle textButtonStyle = skinLevel.get("normal", TextButton.TextButtonStyle.class);
        textButtonStyle.font = Assets.instance.fonts.bitmapFontNormal;

        TextButton.TextButtonStyle textButtonStyle2 = skinLevel.get("locked", TextButton.TextButtonStyle.class);
        textButtonStyle2.font = Assets.instance.fonts.bitmapFontNormal;


        for(int i = 1; i < 34; i++){
            final int m = i;
            if(pref.getInteger("LevelScore") >= m)
                buttons.add(new TextButton(Integer.toString(i), textButtonStyle));
            else
                buttons.add(new TextButton(Integer.toString(i), textButtonStyle2));
            buttons.get(i - 1).setColor(0.5f, 0.5f, 0.5f, 1);
            buttons.get(i - 1).addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (pref.getInteger("LevelScore") >= m) {
                        initLevel(m);
                    }
                }
            });
            scrollTable.add(buttons.get(i - 1)).expandX();
            if(i % 3 == 0)
                scrollTable.row().padTop(20);
        }

        final ScrollPane scroller = new ScrollPane(scrollTable);

        final Table table = new Table();
        table.setFillParent(true);
        table.add(scroller).fill().expand().padTop(220);
        TextureRegion regLogo = Assets.instance.startBall.logo;
        Image image = new Image(regLogo);
        image.setSize(180, 180);
        image.setPosition(150, 620);
        Label lineGenius = new Label("Line Genius", new Label.LabelStyle(Assets.instance.fonts.bitmapFontBig, Color.BLACK));
        lineGenius.setPosition(90, 580);
        stage.addActor(image);
        stage.addActor(lineGenius);
        stage.addActor(table);
    }


    private void initLevel(int n){
        InkBall.arcadeManager.currentLevel = n;
        Gdx.app.log("ds", Integer.toString(InkBall.arcadeManager.currentLevel));
        stage.dispose();
        game.setScreen(InkBall.arcadeManager.getLevelClass(game, n));
    }

    @Override
    public void dispose() {
        stage.dispose();
        skinLevel.dispose();
    }
}
