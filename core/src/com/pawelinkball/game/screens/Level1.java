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
 * Created by Pawel on 07.07.2017.
 */
public class Level1 extends AbstractLevelClass {

    private float ray = 0.25f;
    private float multiplier = 4;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 0.875f;
    private boolean start = false;
    private long time = 65;


    public Level1(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        //initB(1, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
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
            //startBall(1, ray, posX, posY + 1);
            startBall(1, ray, posX, posY + 2);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        //startTextures.add(new StartBallTexture(posX, posY + 1));
        startTextures.add(new StartBallTexture(posX, posY + 2));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(3.75f, 6.5f), 2f, 0.5f, world));
    }

}