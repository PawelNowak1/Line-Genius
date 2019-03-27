package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Ball;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

import java.util.Random;

/**
 * Created by Pawel on 09.07.2017.
 */
public class Level3 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.15f;
    private double radOfBall = Math.PI * 5 / 4.0;
    private float angularVelocity = 4;
    private float posX = 4, posY = 4;
    private boolean start = false;
    private long time = 45;
    private boolean start1 = false, start2 = false;
    private Random random;

    public Level3(Game game) {
        super(game);
        random = new Random();
        initWalls();
        initB(0, random.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, random.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, random.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
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
            startBall(0, ray, 3, 3);
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 4) && !start1 && startTime != 0){
            start1 = true;
            startBall(1, ray, 2, 3);
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 8) && !start2 && startTime != 0){
            start2 = true;
            startBall(2, ray, 4, 3);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(3, 3));
        startTextures.add(new StartBallTexture(2, 3));
        startTextures.add(new StartBallTexture(4, 3));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(3, 9), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(1.75f, 9), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(4.25f, 9), 0.25f, InkBall.BLACK, world, "black.png"));

    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

    }
}
