package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 09.07.2017.
 */
public class Level17 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 1.375f, posY = 1.375f;
    private boolean start = false;
    private long time = 50;

    public Level17(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initObstacles();
        initTime(time);
        initStartTextures();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, posX, posY);
            startBall(1, ray, posX + 1, posY + 1);
            startBall(2, ray, posX + 2, posY + 2);
        }
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(1.625f, 6), 0.5f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(4.375f, 6), 0.5f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(3, 9), 0.25f, InkBall.YELLOW, world, "yellow.png"));
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        startTextures.add(new StartBallTexture(posX + 1, posY + 1));
        startTextures.add(new StartBallTexture(posX + 2, posY + 2));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
    }

}