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
public class Level30 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.75f;
    private float angularVelocity = 4;
    private float posX = 3, posY = 5;
    private boolean start = false;
    private long time = 55;
    private boolean start1 = false, start2 = false, start3 = false, start4 = false, start5 = false, start6 = false;


    public Level30(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(3, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(4, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(5, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(6, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");

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

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 4) && !start1 && startTime != 0){
            if(isPlaceForBall(1, posX, posY)){
                startBall(1, ray, posX, posY);
                start1 = true;
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 8) && !start2 && startTime != 0){
            if(isPlaceForBall(2, posX, posY)){
                start2 = true;
                startBall(2, ray, posX, posY);
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 12) && !start3 && startTime != 0){
            if(isPlaceForBall(3, posX, posY)){
                start3 = true;
                startBall(3, ray, posX, posY);
            }
        }
        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 16) && !start4 && startTime != 0){
            if(isPlaceForBall(4, posX, posY)){
                start4 = true;
                startBall(4, ray, posX, posY);
            }
        }
        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 20) && !start5 && startTime != 0){
            if(isPlaceForBall(5, posX, posY)){
                start5 = true;
                startBall(5, ray, posX, posY);
            }
        }
        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 24) && !start6 && startTime != 0){
            if(isPlaceForBall(6, posX, posY)){
                start6 = true;
                startBall(6, ray, posX, posY);
            }
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
        boxes.add(new ColorBox(new Vector2(0.5f, 0.5f), 0.25f, 0.25f, world, "4", InkBall.BLUE, "blueball.png", Color.BLUE));
    }
}