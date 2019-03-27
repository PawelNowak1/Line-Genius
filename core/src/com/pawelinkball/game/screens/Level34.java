package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.entities.Box;
import com.pawelinkball.game.entities.Hole;
import com.pawelinkball.game.entities.Line;
import com.pawelinkball.game.entities.StartBallTexture;

/**
 * Created by Pawel on 17.09.2017.
 */
public class Level34 extends AbstractLevelClass {
    private float ray = 0.25f;
    private float multiplier = 3;
    private double radOfBall = Math.PI / 2;
    private float angularVelocity = 4;
    private float posX = 3, posY = 0.875f;
    private boolean start = false;
    private long time = 6555;


    public Level34(Game game) {
        super(game);
        initWalls();
        initB(0, radOfBall, 1, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(1, radOfBall, 2, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(2, radOfBall, 3, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(3, radOfBall, 5, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(4, radOfBall, 7, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(5, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        initB(6, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        //initB(2, radOfBall, multiplier, angularVelocity, InkBall.YELLOW, "yellowball.png");
        //initStartTextures();
        //lines.add(new Line(new Vector2(1, 0), new Vector2(6,5)));
        //lines.add(new Line(new Vector2(2.3f, 2), new Vector2(3,2)));
        //lines.add(new Line(new Vector2(3, 2), new Vector2(3,2.7f)));
        initObstacles();
        initTime(time);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.justTouched() && !start){
            start = true;
            startTime();
            startBall(0, ray, 2, 1);
            startBall(1, ray, 1, 1);
            startBall(2, ray, 3, 1);
            startBall(3, ray, 5, 1);
            startBall(4, ray, 4, 1);
            //startBall(5, ray, 3, 3);
            //startBall(6, ray, 3, 3);

            //startBall(2, ray, 3.5f, 3.5f);
        }
    }

    private void initStartTextures() {
        startTextures.add(new StartBallTexture(posX, posY));
        startTextures.add(new StartBallTexture(posX, posY + 1));
        startTextures.add(new StartBallTexture(posX, posY + 2));
    }

    private void initObstacles(){
        holes.add(new Hole(new Vector2(5.35f, 9.35f), 0.25f, InkBall.YELLOW, world, "yellow.png"));
    }
    private void initWalls(){
        boxes.add(new Box(new Vector2(3, 0), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(3, 10), 3, 0.25f, world));
        boxes.add(new Box(new Vector2(0, 5), 0.25f, 5, world));
        boxes.add(new Box(new Vector2(6, 5), 0.25f, 5, world));
        //boxes.add(new Box(new Vector2(3, 5), 3f, 0.5f, world));
    }
}
