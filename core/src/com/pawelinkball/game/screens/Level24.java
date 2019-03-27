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
public class Level24 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.5f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 5;
    private boolean start = false;
    private long time = 70;
    private boolean start1 = false, start2 = false, start3 = false;

    public Level24(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(3, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.YELLOW, "yellow.png");
        initObstacles();
        initTime(time);
        initDesBoxes();
        initStartTextures();
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
                Gdx.app.log("piłka wystartowała", "1");

                startBall(1, ray, posX, posY);
                start1 = true;
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 6) && !start2 && startTime != 0){
            if(isPlaceForBall(2, posX, posY)){
                start2 = true;
                Gdx.app.log("piłka wystartowała", "2");
                startBall(2, ray, posX, posY);
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 9) && !start3 && startTime != 0){
            Gdx.app.log("o co cho", "dzi");
            if(isPlaceForBall(3, posX, posY)){
                Gdx.app.log("piłka wystartowała", "dxiz");
                start3 = true;
                startBall(3, ray, posX, posY);
            }
        }
    }

    private void initDesBoxes(){

        destroyableBoxes.add(new DestroyableBox(new Vector2(0.55f, 8.9f), world, InkBall.YELLOW, "0"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1f, 8.9f), world, InkBall.YELLOW, "1"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1f, 9.45f), world, InkBall.YELLOW, "2"));

        destroyableBoxes.add(new DestroyableBox(new Vector2(5.45f, 8.9f), world, InkBall.RED, "3"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.9f, 8.9f), world, InkBall.RED, "4"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.9f, 9.45f), world, InkBall.RED, "5"));

    }
    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }
    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.55f, 9.45f), 0.25f, InkBall.RED, world, "red.png"));
        holes.add(new Hole(new Vector2(5.45f, 9.45f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
    }
}
