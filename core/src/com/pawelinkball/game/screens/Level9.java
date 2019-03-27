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
 * Created by Pawel on 19.08.2017.
 */
public class Level9 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3f, posY = 5f;
    private boolean start = false;
    private long time = 100;
    private boolean start1 = false, start2 = false;

    public Level9(Game game) {
        super(game);

        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
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

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 5) && !start1 && startTime != 0){
            if(isPlaceForBall(1, posX, posY)){
                startBall(1, ray, posX, posY);
                start1 = true;
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 10) && !start2 && startTime != 0){
            if(isPlaceForBall(2, posX, posY)){
                start2 = true;
                startBall(2, ray, posX, posY);
            }
        }
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(3.5f, 6.5f), 2.25f, 0.5f, world));
        boxes.add(new Box(new Vector2(4.25f, 4f), 0.5f, 2.5f, world));
        boxes.add(new ColorBox(new Vector2(5.25f, 5.75f), 0.5f, 0.25f, world, "6", InkBall.BLUE, "blueball.png", Color.BLUE));
    }
}
