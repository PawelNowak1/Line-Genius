package com.pawelinkball.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pawelinkball.game.utils.Assets;

/**
 * Created by Pawel on 18.07.2017.
 */
public class Hole {
    private float ray;
    //private float bigRay;

    private Vector2 position;
    private String colorID;
    private BodyDef bodyDef;
    private Body body;
    //private Texture texture;
    private TextureRegion regHole;
    //private static final float proportion = 256/75;

    public Hole(Vector2 pos, float r, String c, World world, String file){
        //texture = new Texture(file);
        if(file.equals("black.png"))
            regHole = Assets.instance.hole.black;
        else if(file.equals("blue.png"))
            regHole = Assets.instance.hole.blue;
        else if(file.equals("white.png"))
            regHole = Assets.instance.hole.white;
        else if(file.equals("yellow.png"))
            regHole = Assets.instance.hole.yellow;
        else if(file.equals("red.png"))
            regHole = Assets.instance.hole.red;
        else if(file.equals("green.png"))
            regHole = Assets.instance.hole.green;
        position = pos;
        colorID = c;
        ray = r;
        //bigRay = r * proportion;
        CircleShape shape = new CircleShape();
        shape.setRadius(ray);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        body = world.createBody(bodyDef);
        body.createFixture(shape, 1.0f);
        body.setUserData(c);
    }

    public void draw(SpriteBatch sb){
        sb.draw(regHole, position.x - ray, position.y - ray, ray * 2, ray * 2);
    }

    public float getX(){
        return position.x;
    }

    public String getColorID(){
        return colorID;
    }
    public float getY(){
        return position.y;
    }

    public float getRay(){
        return ray;
    }


}
