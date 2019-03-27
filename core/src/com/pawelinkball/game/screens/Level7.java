package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

import java.util.Random;

/**
 * Created by Pawel on 04.08.2017.
 */
public class Level7 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 5;
    private boolean start = false;
    private long time = 95;
    private boolean start1 = false, start2 = false, start3 = false;

    public Level7(Game game) {
        super(game);
        initWalls();
        initB(0, Math.PI / 2 + rand.nextDouble() * Math.PI, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI / 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, Math.PI / 2 + rand.nextDouble() * Math.PI, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(3, rand.nextDouble() * Math.PI / 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
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
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 3) && !start1 && startTime != 0){
            if(isPlaceForBall(1, posX, posY)){
                startBall(1, ray, posX, posY);
                start1 = true;
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 6) && !start2 && startTime != 0){
            if(isPlaceForBall(2, posX, posY)){
                start2 = true;
                startBall(2, ray, posX, posY);
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 9) && !start3 && startTime != 0){
            if(isPlaceForBall(3, posX, posY)){
                start3 = true;
                startBall(3, ray, posX, posY);
            }
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(0.65f, 0.65f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(4, 8.0f), 2, 0.25f, world));
        boxes.add(new Box(new Vector2(2.25f, 5.8f), 2.25f, 0.25f, world));
        //boxes.add(new Box(new Vector2(4, 5.75f), 2, 0.25f, world));
        //boxes.add(new Box(new Vector2(2, 4.25f), 2, 0.25f, world));
        boxes.add(new Box(new Vector2(3.75f, 4.2f), 2.25f, 0.25f, world));
        boxes.add(new Box(new Vector2(2, 2.0f), 2, 0.25f, world));
    }
}
