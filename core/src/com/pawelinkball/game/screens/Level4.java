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
public class Level4 extends AbstractLevelClass {

    private float ray = 0.25f;
    private float multiplier = 3.75f;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 3.75f, posY = 7.25f;
    private boolean start = false;
    private long time = 40;


    public Level4(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initObstacles();
        initStartTextures();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, 1.625f, 1);
            startBall(1, ray, 4.625f, 1);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(1.625f, 1));
        startTextures.add(new StartBallTexture(4.625f, 1));
    }
    private void initObstacles(){
        holes.add(new Hole(new Vector2(1.625f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(4.625f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(3, 8), 0.25f, 2.2f, world));
        boxes.add(new Box(new Vector2(3, 2), 0.25f, 2.2f, world));
    }

}
