package com.pawelinkball.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pawelinkball.game.InkBall;
import com.pawelinkball.game.utils.Assets;

/**
 * Created by Pawel on 07.09.2017.
 */
public class DestroyableBox {
    private Vector2 position;
    private float height = 0.25f;
    private float width = 0.25f;
    private BodyDef bodyDef;
    public Body body;
    private PolygonShape shape;
    private TextureRegion boxReg;
    public boolean active = true;
    private String idColor;

    public DestroyableBox(Vector2 pos, World world, String id, String index) {
        // pos = pozycja lewego dolnego narożnika
        if(id.equals(InkBall.BLUE))
            boxReg = Assets.instance.destroyableBox.bluebox;
        else if(id.equals(InkBall.YELLOW))
            boxReg = Assets.instance.destroyableBox.yellowbox;
        else if(id.equals(InkBall.RED))
            boxReg = Assets.instance.destroyableBox.redbox;
        else if(id.equals(InkBall.GREEN))
            boxReg = Assets.instance.destroyableBox.greenbox;
        idColor = id;
        position = pos;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        shape = new PolygonShape();
        shape.setAsBox(width, height);
        body = world.createBody(bodyDef);
        body.createFixture(shape, 1.0f);
        body.setUserData("desBox" + index);
    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(boxReg, position.x - width, position.y - height, width * 2, height * 2);
    }

    public String getID(){
        return idColor;
    }

    public boolean isActive(){
        return active;
    }
}
