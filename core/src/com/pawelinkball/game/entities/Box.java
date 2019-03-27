package com.pawelinkball.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pawelinkball.game.InkBall;

/**
 * Created by Pawel on 07.07.2017.
 */
public class Box {
    private Vector2 position;
    private float height;
    private float width;
    private BodyDef bodyDef;
    protected Body body;
    private PolygonShape shape;
    protected String colorID = InkBall.YELLOW;
    protected String fileTexture = "yellowball.png";

    public Box(Vector2 pos, float w, float h, World world) {
        /*
        pos -> pozycja
        w -> połowa szerokości
        h -> połowa wysokości
         */
        position = pos;
        height = h;
        width = w;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        shape = new PolygonShape();
        shape.setAsBox(width, height);
        body = world.createBody(bodyDef);
        body.createFixture(shape, 1.0f);
        body.setUserData("wall");
    }

    public String getFileTexture() {
        return fileTexture;
    }

    public String getColorID() {
        return colorID;
    }

    public void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    public float getPosX(){
        return position.x - width;
    }

    public float getPosY(){
        return position.y - height;
    }

    public float getWidth(){
        return width * 2;
    }

    public float getHeight(){
        return height * 2;
    }
}
