package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Ball;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.utils.Assets;

/**
 * Created by Pawel on 09.07.2017.
 */
public class StartLevel extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 1, posY = 1;
    private boolean start = false;
    private long time = 40000;

    public static final float VIEWPORT_MENU_HEIGHT = 800.0f;
    public static final float VIEWPORT_MENU_WIDTH = 480.0f;

    private Stage stage;
    private Skin skinMenu;

    private TextButton btnPlay;
    private TextButton btnOptions;
    private TextButton btnAuthor;
    //TextButton textButton;
    public StartLevel(Game game) {
        super(game);
        hud.clear();
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.WHITE, "whiteball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(3, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.GREEN, "greenball.png");
        initB(4, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(5, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(6, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(7, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(8, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(9, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(10, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(11, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.GREEN, "greenball.png");
        initB(12, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(13, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(14, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(15, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.GREEN, "greenball.png");
        initB(16, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(17, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.WHITE, "whiteball.png");
        initTime(time);

        stage = new Stage(new StretchViewport(VIEWPORT_MENU_WIDTH, VIEWPORT_MENU_HEIGHT));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);
        rebuildStage();

    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(!start){
            start = true;
            startTime();
            startBall(0, ray, posX, posY + 1);
            startBall(1, ray, posX, posY + 2);
            startBall(2, ray, posX, posY + 3);
            startBall(3, ray, posX, posY + 4);
            startBall(4, ray, posX, posY + 5);
            startBall(5, ray, posX, posY + 6);
            startBall(6, ray, posX, posY + 7);
            startBall(7, ray, posX + 1, posY);
            startBall(8, ray, posX + 2, posY);
            startBall(9, ray, posX + 3, posY);
            startBall(10, ray, 4.5f, posX + 1);
            startBall(11, ray, 4.5f, posX + 2);
            startBall(12, ray, 4.5f, posX + 3);
            startBall(13, ray, 4.5f, posX + 4);
            startBall(14, ray, 4.5f, posX + 5);
            startBall(15, ray, 4.5f, posX + 6);
            startBall(16, ray, 4.5f, posX + 7);
            startBall(17, ray, 3, 8.5f);
        }
        //Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    protected void handleBackButton() {
        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.BACK) || Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE))
            Gdx.app.exit();
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(3, 7.96875f), 1.3875f, 0.46875f, world));
        boxes.add(new Box(new Vector2(3, 4.21875f), 1.3875f, 0.46875f, world));
        boxes.add(new Box(new Vector2(3, 6.09375f), 1.3875f, 0.46875f, world));
    }

    void rebuildStage(){
        skinMenu = new Skin(Gdx.files.internal("game.json"), new TextureAtlas("game.atlas"));
        buildMenuLayer();
    }

    void buildMenuLayer(){
        TextButton.TextButtonStyle textButtonStyle = skinMenu.get("big", TextButton.TextButtonStyle.class);
        textButtonStyle.font = Assets.instance.fonts.bitmapFontNormal;

        Table table = new Table();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(VIEWPORT_MENU_WIDTH, VIEWPORT_MENU_HEIGHT);
        stack.add(table);
        btnPlay = new TextButton("Level Mode", textButtonStyle);
        btnPlay.setColor(0.5f, 0.5f, 0.5f, 1);
        table.setFillParent(true);
        table.top().pad(125);
        table.add(btnPlay);
        btnPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onPlayClicked();
            }
        });
        btnOptions = new TextButton("Arcade Mode", textButtonStyle);
        btnOptions.setColor(0.5f, 0.5f, 0.5f, 1);
        table.row().pad(75);
        table.add(btnOptions);
        btnOptions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                arcadeModeOn();
            }
        });
        btnAuthor = new TextButton("How to play", textButtonStyle);
        btnAuthor.setColor(0.5f, 0.5f, 0.5f, 1);
        btnAuthor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HowToPlayScreen(game));
            }
        });
        table.row();
        //TextButtonStyle buttonStyle = skin.get("bigButton", TextButtonStyle.class);
        //TextButton button = new TextButton("Click me!", buttonStyle);

        //textButton = new TextButton("TEXTBUTTON 0", textButtonStyle);
        //table.add(textButton);
        //table.row();
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
