package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.ColorBox;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 08.09.2017.
 */
public class Level22 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = 0;
    private float angularVelocity = 4;
    private float posX = 3, posY = 1.5f;
    private boolean start = false;
    private long time = 80;


    public Level22(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, multiplier, angularVelocity, InkBall.RED, "redball.png");
        initB(1, radOfBall, multiplier, angularVelocity, InkBall.RED, "redball.png");
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
            startBall(0, ray, posX, posY);
            startBall(1, ray, posX, posY + 1);
            //startBall(3, ray);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        startTextures.add(new StartBallTexture(posX, posY + 1));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.BLUE, world, "blue.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));

        //boxes.add(new Box(new Vector2(3, 4.75f), 1, 0.25f, world));
        //boxes.add(new Box(new Vector2(1, 6.75f), 1, 0.25f, world));
        //boxes.add(new Box(new Vector2(5, 6.75f), 1, 0.25f, world));

        boxes.add(new ColorBox(new Vector2(0.55f, 0.55f), 0.25f, 0.25f, world, "4", InkBall.BLUE, "blueball.png", Color.BLUE));

        boxes.add(new ColorBox(new Vector2(3, 4.75f), 1, 0.25f, world, "5", InkBall.RED, "redball.png", Color.RED));
        boxes.add(new ColorBox(new Vector2(1, 6.75f), 0.75f, 0.25f, world, "6", InkBall.RED, "redball.png", Color.RED));
        boxes.add(new ColorBox(new Vector2(5, 6.75f), 0.75f, 0.25f, world, "7", InkBall.RED, "redball.png", Color.RED));

    }
}
