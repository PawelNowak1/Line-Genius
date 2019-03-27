package com.pawelinkball.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pawelinkball.game.utils.Assets;

/**
 * Created by Pawel on 03.09.2017.
 */
public class StartBallTexture {
    private TextureRegion startBall;
    private float posX;
    private float posY;

    public StartBallTexture(float x, float y){
        startBall = Assets.instance.startBall.startBall;
        posX = x;
        posY = y;
    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(startBall, posX - 0.25f, posY - 0.25f, 0.5f, 0.5f);
    }

}
