package com.pawelinkball.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Pawel on 23.08.2017.
 */
public class ColorBox extends Box {
    private Color color;

    public ColorBox(Vector2 pos, float w, float h, World world, String index, String acolorID, String file, Color col) {
        super(pos, w, h, world);
        body.setUserData("colorWall" + index);
        colorID = acolorID;
        fileTexture = file;
        color = col;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
        shapeRenderer.setColor(Color.LIGHT_GRAY);
    }
}