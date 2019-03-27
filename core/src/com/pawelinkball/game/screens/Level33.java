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
 * Created by Pawel on 25.07.2017.
 */
public class Level33 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.35f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 1.375f, posY = 1.375f;
    private boolean start = false;
    private long time = 105;

    public Level33(Game game) {
        super(game);
        initObstacles();
        initWalls();
        initB(0, 0, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, 0, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initB(2, 0, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
        initTime(time);
        initStartTextures();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, 3, 7.25f);
            startBall(1, ray, 3, 9.25f);
            startBall(2, ray, 3, 5.25f);
        }
    }

    @Override
    protected void hudWin() {
        hud.allLevelsCompleted();
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(3, 7.25f));
        startTextures.add(new StartBallTexture(3, 9.25f));
        startTextures.add(new StartBallTexture(3, 5.25f));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.75f, 9.25f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
        holes.add(new Hole(new Vector2(5.25f, 9.25f), 0.25f, InkBall.BLUE, world, "blue.png"));
        holes.add(new Hole(new Vector2(2.25f, 1.25f), 0.5f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(3.75f, 1.25f), 0.5f, InkBall.BLACK, world, "black.png"));
    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(1.75f, 7.5f), 0.25f, 2.5f, world));
        boxes.add(new Box(new Vector2(4.25f, 7.5f), 0.25f, 2.5f, world));

        destroyableBoxes.add(new DestroyableBox(new Vector2(0.5625f, 6.45f), world, InkBall.BLUE, "0"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1875f, 6.45f), world, InkBall.BLUE, "1"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(0.5625f, 5.85f), world, InkBall.BLUE, "2"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1875f, 5.85f), world, InkBall.BLUE, "3"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(0.5625f, 5.25f), world, InkBall.BLUE, "4"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(1.1875f, 5.25f), world, InkBall.BLUE, "5"));

        /*destroyableBoxes.add(new DestroyableBox(new Vector2(5.4375f, 6.45f), world, InkBall.YELLOW, "6"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.8125f, 6.45f), world, InkBall.YELLOW, "7"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(5.4375f, 5.85f), world, InkBall.YELLOW, "8"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.8125f, 5.85f), world, InkBall.YELLOW, "9"));*/
        destroyableBoxes.add(new DestroyableBox(new Vector2(5.4375f, 5.25f), world, InkBall.YELLOW, "6"));
        destroyableBoxes.add(new DestroyableBox(new Vector2(4.8125f, 5.25f), world, InkBall.YELLOW, "7"));
    }


}
