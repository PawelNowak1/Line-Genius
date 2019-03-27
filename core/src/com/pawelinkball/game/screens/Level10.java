package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 20.08.2017.
 */
public class Level10 extends AbstractLevelClass{
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 5;
    private boolean start = false;
    private long time = 55;
    private boolean start1 = false, start2 = false, start3 = false;

    public Level10(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(1, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(3, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
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
                Gdx.app.log("piłka wystartowała", "1");

                startBall(1, ray, posX, posY);
                start1 = true;
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 10) && !start2 && startTime != 0){
            if(isPlaceForBall(2, posX, posY)){
                start2 = true;
                Gdx.app.log("piłka wystartowała", "2");
                startBall(2, ray, posX, posY);
            }
        }

        if((time - (System.currentTimeMillis() - startTime) / 1000 < time - 15) && !start3 && startTime != 0){
            Gdx.app.log("o co cho", "dzi");
            if(isPlaceForBall(3, posX, posY)){
                Gdx.app.log("piłka wystartowała", "dxiz");
                start3 = true;
                startBall(3, ray, posX, posY);
            }
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.5f, 0.5f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(0.5f, 9.5f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(5.5f, 0.5f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(5.5f, 9.5f), 0.25f, InkBall.BLUE, world, "blue.png"));

        holes.add(new Hole(new Vector2(0.5f, 5f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.5f, 5f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(2.5f, 0.5f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(3.5f, 0.5f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(2.5f, 9.5f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(3.5f, 9.5f), 0.25f, InkBall.BLACK, world, "black.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(1.75f, 3.25f), 0.25f, 0.25f, world));
        boxes.add(new Box(new Vector2(1.75f, 6.75f), 0.25f, 0.25f, world));
        boxes.add(new Box(new Vector2(4.25f, 3.25f), 0.25f, 0.25f, world));
        boxes.add(new Box(new Vector2(4.25f, 6.75f), 0.25f, 0.25f, world));

    }
}
