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

/**
 * Created by Pawel on 09.07.2017.
 */
public class Level19 extends AbstractLevelClass {

    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 2, posY = 3;
    private boolean start = false;
    private long time = 70;


    public Level19(Game game) {
        super(game);
        initWalls();
        initB(0, 0, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(1, 0, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, 0, multiplier, angularVelocity, InkBall.WHITE, "whiteball.png");
        initB(3, 0, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(4, 0, multiplier, angularVelocity, InkBall.RED, "redball.png");
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
            startBall(0, ray, 3, 9.3f);
            startBall(1, ray, 3, 8.3f);
            startBall(2, ray, 3, 7.3f);
            startBall(3, ray, 3, 6.3f);
            startBall(4, ray, 3, 5.3f);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(3, 9.3f));
        startTextures.add(new StartBallTexture(3, 8.3f));
        startTextures.add(new StartBallTexture(3, 7.3f));
        startTextures.add(new StartBallTexture(3, 6.3f));
        startTextures.add(new StartBallTexture(3, 5.3f));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(3f, 0.6f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(3f, 1.5f), 0.25f, InkBall.RED, world, "red.png"));
        holes.add(new Hole(new Vector2(3f, 2.4f), 0.25f, InkBall.WHITE, world, "white.png"));
        holes.add(new Hole(new Vector2(3f, 3.3f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(3f, 4.2f), 0.25f, InkBall.BLACK, world, "black.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

    }

}
