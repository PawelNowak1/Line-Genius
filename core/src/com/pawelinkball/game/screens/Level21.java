package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.DestroyableBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 08.09.2017.
 */
public class Level21 extends AbstractLevelClass{

    private float ray = 0.25f;
    private float multiplier = 3.25f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 5;
    private boolean start = false;
    private long time = 160;


    public Level21(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, radOfBall, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initStartTextures();
        //initB(3, radOfBall, multiplier, angularVelocity, posX + 1, posY, InkBall.YELLOW, "yellowball.png");
        initDesBox();
        initObstacles();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, 2, posY);
            startBall(1, ray, 4, posY);
            //startBall(3, ray);
        }
    }

    private void initDesBox(){
        destroyableBoxes.add(new DestroyableBox(new Vector2(5.45f, 3.5f), world, InkBall.YELLOW, "0"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.90f, 3.5f), world, InkBall.YELLOW, "1"));

        destroyableBoxes.add(new DestroyableBox(new Vector2(5.45f, 1.5f), world, InkBall.YELLOW, "2"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.90f, 1.5f), world, InkBall.YELLOW, "3"));

        destroyableBoxes.add(new DestroyableBox(new Vector2(0.55f, 6.5f), world, InkBall.BLUE, "4"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1f, 6.5f), world, InkBall.BLUE, "5"));

        destroyableBoxes.add(new DestroyableBox(new Vector2(0.55f, 8.5f), world, InkBall.BLUE, "6"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1f, 8.5f), world, InkBall.BLUE, "7"));
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(2, posY));
        startTextures.add(new StartBallTexture(4, posY ));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.65f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(5.35f, 0.65f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(4f, 6.5f), 2.6f, 0.25f, world));
        boxes.add(new Box(new Vector2(4f, 8.5f), 2.6f, 0.25f, world));

        boxes.add(new Box(new Vector2(2f, 3.5f), 2.6f, 0.25f, world));
        boxes.add(new Box(new Vector2(2f, 1.5f), 2.6f, 0.25f, world));

    }
}
