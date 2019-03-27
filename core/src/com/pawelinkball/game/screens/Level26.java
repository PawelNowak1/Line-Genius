package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 09.09.2017.
 */
public class Level26 extends AbstractLevelClass{
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 1, posY = 9f;
    private boolean start = false;
    private long time = 70;


    public Level26(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, radOfBall, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(3, radOfBall, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
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
            startBall(1, ray, posX + 3, posY);
            startBall(2, ray, posX + 1, posY);
            startBall(3, ray, posX + 4, posY);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        startTextures.add(new StartBallTexture(posX + 3, posY));
        startTextures.add(new StartBallTexture(posX + 1, posY));
        startTextures.add(new StartBallTexture(posX + 4, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(2, 7.5f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(4, 7.5f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(3, 8.25f), 2, 0.25f, world));
        boxes.add(new Box(new Vector2(1.25f, 4.75f), 0.25f, 3.25f, world));
        boxes.add(new Box(new Vector2(3, 4.75f), 0.5f, 3.25f, world));
        boxes.add(new Box(new Vector2(4.75f, 4.75f), 0.25f, 3.25f, world));
    }
}
