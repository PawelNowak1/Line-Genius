package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.ColorBox;
import com.pawelinkball.game.entities.DestroyableBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 09.09.2017.
 */
public class Level25 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 0.875f;
    private boolean start = false;
    private long time = 80;


    public Level25(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        //initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initStartTextures();
        //initB(3, radOfBall, multiplier, angularVelocity, posX + 1, posY, InkBall.YELLOW, "yellowball.png");

        initObstacles();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, posX + 2, posY + 1);
            startBall(1, ray, posX, posY + 1);
            //startBall(2, ray, posX, posY + 2);
            //startBall(3, ray);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX + 2, posY + 1));
        startTextures.add(new StartBallTexture(posX, posY + 1));
        //startTextures.add(new StartBallTexture(posX, posY + 2));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(4.5f, 4.9f), 1.5f, 1.9f, world));

        boxes.add(new ColorBox(new Vector2(1.5f, 0.5f), 1.25f, 0.25f, world, "5", InkBall.BLUE, "blueball.png", Color.BLUE));
        boxes.add(new ColorBox(new Vector2(5.5f, 0.5f), 0.25f, 0.25f, world, "6", InkBall.YELLOW, "yellowball.png", Color.YELLOW));


        for(int i = 0; i < 5; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * i, 3.25f), world, InkBall.YELLOW, Integer.toString(i)));

        for(int i = 5; i < 10; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * (i - 5), 3.80f), world, InkBall.YELLOW, Integer.toString(i)));

        for(int i = 10; i < 15; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * (i - 10), 4.35f), world, InkBall.YELLOW, Integer.toString(i)));

        for(int i = 15; i < 20; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * (i - 15), 4.9f), world, InkBall.YELLOW, Integer.toString(i)));

        for(int i = 20; i < 25; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * (i - 20), 5.45f), world, InkBall.YELLOW, Integer.toString(i)));

        for(int i = 25; i < 30; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * (i - 25), 6f), world, InkBall.YELLOW, Integer.toString(i)));

        for(int i = 30; i < 35; i++)
            destroyableBoxes.add(new DestroyableBox(new Vector2(0.52f + 0.55f * (i - 30), 6.55f), world, InkBall.YELLOW, Integer.toString(i)));
    }
}
