package com.pawelinkball.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.utils.Assets;


/**
 * Created by Pawel on 08.08.2017.
 */
public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;
    private Label timeLabel, scoreText, highScoreText, scoreLabel, highScoreLabel;
    private long worldTimer = 100;
    private long score = 0;
    private long highScore = 0;
    private Preferences preferences;
    private Label gameOver, gameWin, level;
    private Table table, tableBall, tableMessage;
    private BitmapFont bitmapFontSmall;
    private BitmapFont bitmapFontBig;
    private BitmapFont bitmapFontNormal;


    public Hud(SpriteBatch sb, boolean arcadeMode) {
        viewport = new StretchViewport(480, 800, new OrthographicCamera());
        stage = new Stage(viewport);

        preferences = Gdx.app.getPreferences("game");
        score = preferences.getLong("CurrentScore");
        highScore = preferences.getLong("HighScore");
        table = new Table();
        tableBall = new Table();
        tableBall.setFillParent(true);
        tableBall.top();
        table.top();
        table.setFillParent(true);
        tableMessage = new Table();
        tableMessage.setFillParent(true);
        //table.debug();
        bitmapFontSmall = Assets.instance.fonts.bitmapFontSmall;
        scoreText = new Label("SCORE:  ", new Label.LabelStyle(bitmapFontSmall, Color.BLACK));
        highScoreText = new Label("  BEST: ", new Label.LabelStyle(bitmapFontSmall, Color.BLACK));
        bitmapFontNormal = Assets.instance.fonts.bitmapFontNormal;
        scoreLabel = new Label(String.format("%1$4s", score), new Label.LabelStyle(bitmapFontSmall, Color.BLACK));
        highScoreLabel = new Label(String.format("%1$5s", highScore), new Label.LabelStyle(bitmapFontSmall, Color.BLACK));
        timeLabel = new Label(String.format("%1$2s", worldTimer), new Label.LabelStyle(bitmapFontSmall, Color.BLACK));
        bitmapFontBig = Assets.instance.fonts.bitmapFontBig;
        gameOver = new Label("YOU LOSE!", new Label.LabelStyle(bitmapFontBig, Color.BLACK));
        gameWin = new Label("YOU WIN!", new Label.LabelStyle(bitmapFontBig, Color.BLACK));
        table.add(timeLabel).expandX().left().padLeft(20);
        if(arcadeMode){
            table.add(scoreText).expandX().padLeft(40);
            table.add(scoreLabel).expandX().padRight(10);
            table.add(highScoreText).expandX();
            table.add(highScoreLabel).expandX().padRight(190);
        } else {
            level = new Label("Level: " + InkBall.arcadeManager.currentLevel, new Label.LabelStyle(bitmapFontSmall, Color.BLACK));
            table.add(level).expandX().padRight(300);
        }

        stage.addActor(tableBall);
        stage.addActor(table);
        stage.addActor(tableMessage);
    }


    public void clear(){
        stage.clear();
    }
    public void update(long k){
        worldTimer = k;
        timeLabel.setText(String.format("%1$2s", worldTimer));
        //timeLabel.setText(String.format("%1$2s", Gdx.graphics.getFramesPerSecond()));
    }

    public void win(){
        tableMessage.add(gameWin);
    }
    public void gameOver(){
        tableMessage.add(gameOver);
    }

    public void allLevelsCompleted(){
        tableMessage.clear();
        Label congratulations = new Label("Congratulations you completed", new Label.LabelStyle(bitmapFontNormal, Color.BLACK));
        Label congratulations0 = new Label("Level Mode!", new Label.LabelStyle(bitmapFontNormal, Color.BLACK));
        Label congratulations1 = new Label("Remember you can always beat", new Label.LabelStyle(bitmapFontNormal, Color.BLACK));
        Label congratulations2 = new Label("your record in Arcade Mode!", new Label.LabelStyle(bitmapFontNormal, Color.BLACK));
        Label congratulations3 = new Label("Thanks for playing Line Genius", new Label.LabelStyle(bitmapFontNormal, Color.BLACK));
        tableMessage.add(congratulations).row();//.expandX().fillX().align(Align.center).row();
        tableMessage.add(congratulations0).row();
        tableMessage.add(congratulations1).row();//expandX().fillX().center().row();
        tableMessage.add(congratulations2).row();//expandX().fillX().center().align(Align.center).row();
        tableMessage.add(congratulations3);//expandX().fillX().center().row();
    }
    public void renderBallIcon(Color color, float ray, int i, ShapeRenderer shapeRenderer, int amount){
        shapeRenderer.setColor(color);
        if(ray == 0)
            shapeRenderer.circle(5.625f - (amount - i - 1) * 0.3f, 9.875f, 0.125f, 100);
    }

    public void addScore(long k){
        score = score + k;
        scoreLabel.setText(String.format("%1$4s", score));
        preferences.putLong("CurrentScore", score);

        if(score > highScore){
            highScore = score;
            highScoreLabel.setText(String.format("%1$5s", highScore));
            preferences.putLong("HighScore", highScore);
        }

    }

    @Override
    public void dispose() {
        //generator.dispose(); // don't forget to dispose to avoid memory leaks!
        stage.dispose();
        bitmapFontSmall.dispose();
        bitmapFontBig.dispose();
        bitmapFontNormal.dispose();
    }
}
