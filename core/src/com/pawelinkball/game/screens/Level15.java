package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Ball;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.ColorBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 09.07.2017.
 */
public class Level15 extends AbstractLevelClass {

    private float speedX;
    private float speedY;
    private float ray = 0.25f;
    private float multiplier = 4;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 9;
    private boolean start = false;
    private long time = 60;
    private boolean start1 = false, start2 = false;

    public Level15(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
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
            startBall(0, ray, posX, posY);
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 6) && !start1 && startTime != 0){
            if(isPlaceForBall(1, posX, posY)){
                startBall(1, ray, posX, posY);
                start1 = true;
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 12) && !start2 && startTime != 0){
            if(isPlaceForBall(2, posX, posY)){
                startBall(2, ray, posX, posY);
                start2 = true;
            }
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(2f, 5f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(4f, 5f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new ColorBox(new Vector2(0.65f, 0.65f), 0.25f, 0.25f, world, "4", InkBall.BLUE, "blueball.png", Color.BLUE));
        boxes.add(new ColorBox(new Vector2(5.35f, 0.65f), 0.25f, 0.25f, world, "5", InkBall.BLUE, "blueball.png", Color.BLUE));
    }

}
