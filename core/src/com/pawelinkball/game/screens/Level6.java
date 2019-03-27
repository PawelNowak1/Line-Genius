package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 04.08.2017.
 */
public class Level6 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = Math.PI / 4.0;
    private float angularVelocity = 4;
    private float posX = 0.6f, posY = 0.6f;
    private boolean start = false;
    private long time = 70;


    public Level6(Game game) {
        super(game);
        initWalls();
        initB(0, 0, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, 0, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initObstacles();
        initStartTextures();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, 1, 3);
            startBall(1, ray, 1, 7);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(1, 3));
        startTextures.add(new StartBallTexture(1, 7));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(1.75f, 2.25f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(4.25f, 2.25f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(1.75f, 7.75f), 0.25f, InkBall.BLACK, world, "black.png"));
        holes.add(new Hole(new Vector2(4.25f, 7.75f), 0.25f, InkBall.YELLOW, world, "yellow.png"));

    }

    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        boxes.add(new Box(new Vector2(3, 5), 1, 3.5f, world));
        boxes.add(new Box(new Vector2(1.75f, 5), 0.25f, 2.5f, world));
        boxes.add(new Box(new Vector2(4.25f, 5), 0.25f, 2.5f, world));
        //boxes.add(new Box(new Vector2(3, 1.25f), 0.25f, 0.25f, world));
        boxes.add(new Box(new Vector2(1.25f, 5f), 0.25f, 0.25f, world));
        //boxes.add(new Box(new Vector2(3, 8.75f), 0.25f, 0.25f, world));
        boxes.add(new Box(new Vector2(4.75f, 5), 0.25f, 0.25f, world));
    }

}
