package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Ball;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.DestroyableBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 07.07.2017.
 */
public class Level11 extends AbstractLevelClass {

    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 0.74f, posY = 1;
    private boolean start = false;
    private long time = 40;


    public Level11(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(3, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        /*initB(2, radOfBall, multiplier, angularVelocity, posX, posY + 2, InkBall.RED, "redball.png");
        initB(3, radOfBall, multiplier, angularVelocity, posX + 1, posY, InkBall.BLUE, "blueball.png");
        initB(4, radOfBall, multiplier, angularVelocity, posX + 1, posY + 1, InkBall.BLUE, "blueball.png");
        initB(5, radOfBall, multiplier, angularVelocity, posX + 1, posY + 2, InkBall.BLUE, "blueball.png");
        initB(6, radOfBall, multiplier, angularVelocity, posX + 1, posY + 3, InkBall.BLUE, "blueball.png");
        initB(7, radOfBall, multiplier, angularVelocity, posX + 1, posY + 4, InkBall.BLUE, "blueball.png");
        initB(8, radOfBall, multiplier, angularVelocity, posX + 1, posY + 5, InkBall.BLUE, "blueball.png");
        initB(9, radOfBall, multiplier, angularVelocity, posX + 1, posY + 6, InkBall.BLUE, "blueball.png");
        initB(10, radOfBall, multiplier, angularVelocity, posX + 3, posY, InkBall.BLUE, "blueball.png");
        initB(11, radOfBall, multiplier, angularVelocity, posX + 4, posY, InkBall.BLUE, "blueball.png");
        initB(12, radOfBall, multiplier, angularVelocity, posX + 5, posY, InkBall.BLUE, "blueball.png");*/

        initStartTextures();
        initObstacles();
        initDesBoxes();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, posX, posY);
            startBall(1, ray, posX, posY + 3);
            startBall(2, ray, posX, posY + 1);
            startBall(3, ray, posX, posY + 2);
        }
    }
    private void initDesBoxes(){
        for(int i = 0; i < 10; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.5f + 0.55f * i, 5), world, InkBall.YELLOW, Integer.toString(i)));
        for(int i = 10; i < 20; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.5f + 0.55f * (i - 10), 6), world, InkBall.BLUE, Integer.toString(i)));
    }
    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        startTextures.add(new StartBallTexture(posX, posY + 3));
        startTextures.add(new StartBallTexture(posX, posY + 1));
        startTextures.add(new StartBallTexture(posX, posY + 2));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(0.65f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));

    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
        //boxes.add(new Box(new Vector2(3.5f, 4.5f), 6.25f, 0.5f, world));
    }

}