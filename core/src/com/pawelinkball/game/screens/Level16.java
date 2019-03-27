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
public class Level16 extends AbstractLevelClass {

    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 2, posY = 3;
    private boolean start = false;
    private long time = 70;


    public Level16(Game game) {
        super(game);
        initWalls();
        initB(0, 0, multiplier, angularVelocity, InkBall.WHITE, "whiteball.png");
        initB(1, 0, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, 0, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
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
            startBall(0, ray, 3, 7.25f);
            startBall(1, ray, 3, 9.25f);
            startBall(2, ray, 3, 5.25f);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(3, 7.25f));
        startTextures.add(new StartBallTexture(3, 9.25f));
        startTextures.add(new StartBallTexture(3, 5.25f));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.75f, 8.25f), 0.35f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.25f, 8.25f), 0.35f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.75f, 0.75f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(5.25f, 0.75f), 0.25f, InkBall.WHITE, world, "white.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(1.75f, 7.5f), 0.25f, 2.5f, world));
        boxes.add(new Box(new Vector2(4.25f, 7.5f), 0.25f, 2.5f, world));
        boxes.add(new Box(new Vector2(3, 2), 2f, 0.25f, world));

        //boxes.add(new Box(new Vector2(1.15f, 7.5f), 0.25f, 2.5f, world));
        //boxes.add(new Box(new Vector2(2.45f, 7.5f), 0.25f, 2.5f, world));
        //boxes.add(new Box(new Vector2(3.55f, 7.5f), 0.25f, 2.5f, world));
        //boxes.add(new Box(new Vector2(4.85f, 7.5f), 0.25f, 2.5f, world));
    }

}
