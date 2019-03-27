package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.pawelinkball.game.utils.Assets;


/**
 * Created by Pawel on 18.09.2017.
 */
public class HowToPlayScreen extends AbstractGameScreen {

    private Stage stage;
    private Skin skinLevel;
    private BitmapFont bitmapFont;
    private Table table;
    private TextureRegion regHole, regDesBox, regBlock, regStartBall, regLogo;


    public HowToPlayScreen(Game game) {
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
        stage = new Stage(new StretchViewport(480, 800)); // być może co innego ScreenViewPort?
        Gdx.input.setInputProcessor(stage);
        skinLevel = new Skin(Gdx.files.internal("game.json"), new TextureAtlas("game.atlas"));
        initAssets();
        buildText();
    }

    private void initAssets(){
        bitmapFont = Assets.instance.fonts.bitmapFontNormal;
        regDesBox = Assets.instance.destroyableBox.redbox;
        regHole = Assets.instance.hole.yellow;
        regBlock = Assets.instance.destroyableBox.block;
        regStartBall = Assets.instance.startBall.startBall;
        regLogo = Assets.instance.startBall.logo;
    }

    private void buildText(){
        final Table scrollTable = new Table();
        //scrollTable.debug();
        scrollTable.setFillParent(true);
        scrollTable.top();
        scrollTable.padTop(220);
        scrollTable.add(new Label("How to play:", new Label.LabelStyle(bitmapFont, Color.BLACK)));
        scrollTable.row().right();
        scrollTable.add(new Label("The purpose of Line Genius\nis to make the ball hit the\nspot:   In order to do it\nyou have to draw " +
                "lines from\nwhich the ball reflects.\n\nAdditional explanations:\n   The ball will change its\ncolour after hitting a\ncolourful wall\n   The box can be" +
                " destroyed\nby collision with the\nmatching ball\n   The place where the ball\nwill start\n\nGame created by Pawel Nowak,\npawelnowakdev@gmail.com", new Label.LabelStyle(bitmapFont, Color.BLACK)));
        //table.row();
        //table.add(new Label("How to play:", new Label.LabelStyle(bitmapFont, Color.BLACK)));
        Image image = new Image(regHole);
        Image image1 = new Image(regDesBox);
        Image image2 = new Image(regBlock);
        Image image3 = new Image(regStartBall);
        Image image4 = new Image(regLogo);

        image4.setSize(180, 180);
        image4.setPosition(150, 620);

        image.setSize(35, 35);
        image.setPosition(97, 453);

        image2.setSize(35, 35);
        image2.setPosition(16, 304);

        image1.setSize(35, 35);
        image1.setPosition(16, 214);

        image3.setSize(35, 35);
        image3.setPosition(16, 124);
        Label lineGenius = new Label("Line Genius", new Label.LabelStyle(Assets.instance.fonts.bitmapFontBig, Color.BLACK));
        lineGenius.setPosition(90, 580);
        stage.addActor(image);
        stage.addActor(image1);
        stage.addActor(image2);
        stage.addActor(image3);
        stage.addActor(image4);
        stage.addActor(lineGenius);

        stage.addActor(scrollTable);
        /*stage.clear();
        Label line = new Label("Line", new Label.LabelStyle(Assets.instance.fonts.bitmapFontBig, Color.BLACK));
        Label genius = new Label("Genius", new Label.LabelStyle(Assets.instance.fonts.bitmapFontBig, Color.BLACK));
        line.setPosition(20, 600);
        stage.addActor(line);
        genius.setPosition(20, 300);
        stage.addActor(genius);*/
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
    }
}
