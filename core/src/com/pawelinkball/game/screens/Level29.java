package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.ColorBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 10.09.2017.
 */
public class Level29 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 1.5f, posY = 9.45f;
    private boolean start = false;
    private long time = 85;


    public Level29(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(1, radOfBall, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(2, radOfBall, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(3, radOfBall, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initStartTextures();

        initObstacles();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, posX, posY);
            startBall(1, ray, posX, posY - 1);
            startBall(2, ray, posX + 3, posY);
            startBall(3, ray, posX + 3, posY - 1);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        startTextures.add(new StartBallTexture(posX, posY - 1));
        startTextures.add(new StartBallTexture(posX + 3, posY));
        startTextures.add(new StartBallTexture(posX + 3, posY - 1));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.75f, 5.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(5.25f, 5.35f), 0.25f, InkBall.WHITE, world, "white.png"));
        //holes.add(new Hole(new Vector2(3.75f, 5.35f), 0.25f, InkBall.BLUE, world, "blue.png"));

    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(1.5f, 5), 0.25f, 3, world));
        //boxes.add(new Box(new Vector2(3, 5), 0.25f, 3, world));
        boxes.add(new Box(new Vector2(4.5f, 5), 0.25f, 3, world));

        boxes.add(new ColorBox(new Vector2(0.75f, 4.5f), 0.5f, 0.25f, world, "6", InkBall.WHITE, "whiteball.png", Color.WHITE));
        boxes.add(new ColorBox(new Vector2(5.25f, 4.5f), 0.5f, 0.25f, world, "7", InkBall.YELLOW, "yellowball.png", Color.YELLOW));
        //boxes.add(new ColorBox(new Vector2(3.75f, 4.5f), 0.5f, 0.25f, world, "9", InkBall.WHITE, "whiteball.png", Color.WHITE));
    }
}
