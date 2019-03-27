package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 12.08.2017.
 */
public class Level8 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 5.25f, posY = 0.75f;
    private boolean start = false;
    private long time = 45;
    private boolean start1 = false, start2 = false;

    public Level8(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, radOfBall * 5, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, radOfBall * 3, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
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

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.85f, 0.75f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(1.7f, 0.75f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(2.55f, 0.75f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(3.4f, 0.75f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(2.125f, 7.5f), 1f, InkBall.BLACK, world, "black.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(4.5f, 2.5f), 0.25f, 2.5f, world));
    }

}
