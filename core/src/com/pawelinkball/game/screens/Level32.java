package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 17.09.2017.
 */
public class Level32 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3.75f;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 1.75f, posY = 9.45f;
    private boolean start = false;
    private long time = 30;


    public Level32(Game game) {
        super(game);
        initWalls();
        initB(0, rand.nextDouble() * Math.PI * 2, multiplier, angularVelocity, InkBall.BLUE, "blueball.png");
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
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(0.55f, 9.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 8.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 7.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 6.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 5.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 4.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 3.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 2.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(0.55f, 1.45f), 0.25f, InkBall.BLACK, world, "black.png"));

        holes.add(new Hole(new Vector2(0.55f, 0.55f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(1.55f, 0.55f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(2.55f, 0.55f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(3.55f, 0.55f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(4.55f, 0.55f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 0.55f), 0.25f, InkBall.BLACK, world, "black.png"));

        holes.add(new Hole(new Vector2(5.45f, 9.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 8.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 7.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 6.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 5.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 4.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 3.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 2.45f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(5.45f, 1.45f), 0.25f, InkBall.BLACK, world, "black.png"));

        holes.add(new Hole(new Vector2(4.25f, 9.45f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(3, 7), 0.25f, 3.75f, world));
    }
}
