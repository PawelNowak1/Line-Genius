package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 09.09.2017.
 */
public class Level27 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 5;
    private boolean start = false;
    private long time = 80;

    public Level27(Game game) {
        super(game);
        initWalls();
        initB(0, Math.PI / 2, multiplier, angularVelocity, InkBall.GREEN, "greenball.png");
        initB(1, Math.PI / 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(2, Math.PI / 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(3, Math.PI / 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(4, Math.PI / 2, multiplier, angularVelocity, InkBall.WHITE, "whiteball.png");
        initB(5, Math.PI / 2, multiplier, angularVelocity, InkBall.WHITE, "whiteball.png");

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
            startBall(0, ray, 1.25f, posY);
            startBall(1, ray, 2.45f, posY);
            startBall(2, ray, 3.65f, posY);
            startBall(3, ray, 4.85f, posY);
            startBall(4, ray, 1.25f, posY - 4);
            startBall(5, ray, 4.85f, posY - 4);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(1.25f, posY));
        startTextures.add(new StartBallTexture(2.45f, posY));
        startTextures.add(new StartBallTexture(3.65f, posY));
        startTextures.add(new StartBallTexture(4.85f, posY));
        startTextures.add(new StartBallTexture(4.85f, posY - 4));
        startTextures.add(new StartBallTexture(1.25f, posY - 4));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.65f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(1.85f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(3.05f, 9.35f), 0.25f, InkBall.WHITE, world, "white.png"));
        holes.add(new Hole(new Vector2(4.25f, 9.35f), 0.25f, InkBall.RED, world, "red.png"));
        holes.add(new Hole(new Vector2(5.45f, 9.35f), 0.25f, InkBall.GREEN, world, "green.png"));
        //holes.add(new Hole(new Vector2(6.45f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
    }
}
