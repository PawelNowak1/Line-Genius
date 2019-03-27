package com.pawelinkball.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.pawelinkball.game.screens.AbstractLevelClass;

/**
 * Created by Pawel on 07.07.2017.
 */
public class Input extends InputAdapter implements ContactListener {

    private Vector3 touch;
    private AbstractLevelClass abstractLevelClass;
    private Vector2 collisionPoint;

    public Input(AbstractLevelClass game){

        collisionPoint = new Vector2();
        abstractLevelClass = game;
        touch = new Vector3();
    }

    @Override
    public void beginContact(Contact contact) {
        Vector2 points[] = contact.getWorldManifold().getPoints();
        collisionPoint = points[0];
        //Gdx.app.log("A", contact.getFixtureA().getBody().getUserData().toString());
        //Gdx.app.log("B", contact.getFixtureB().getBody().getUserData().toString());
        //Gdx.app.log("COliis", collisionPoint.toString());
        if(contact.getWorldManifold().getNumberOfContactPoints() > 1)
            Gdx.app.log("mamy problem", Integer.toString(contact.getWorldManifold().getNumberOfContactPoints()));

        if(contact.getFixtureB().getBody().getUserData().toString().equals("wall")){
            abstractLevelClass.collisionWithWall(collisionPoint, contact.getFixtureA().getBody().getUserData().toString());
            return;
        } else if(contact.getFixtureB().getBody().getUserData().toString().contains("desBox")){
            abstractLevelClass.collisionWithDesWall(collisionPoint, contact.getFixtureA().getBody().getUserData().toString(), contact.getFixtureB().getBody().getUserData().toString().substring(6));
        }
        else if(contact.getFixtureB().getBody().getUserData().toString().contains("colorWall")){
            abstractLevelClass.collisionWithColorWall(collisionPoint, contact.getFixtureA().getBody().getUserData().toString(), contact.getFixtureB().getBody().getUserData().toString().substring(9));
        }
        else if(contact.getFixtureB().getBody().getUserData().toString().length() > 2){
            abstractLevelClass.ballToHole(contact.getFixtureB().getBody().getUserData().toString(), contact.getFixtureA().getBody().getUserData().toString());
            return;
        }

        if(contact.getFixtureA().getBody().getUserData().equals("wall"))
            abstractLevelClass.collisionWithWall(collisionPoint, contact.getFixtureB().getBody().getUserData().toString());
        else if(contact.getFixtureA().getBody().getUserData().toString().contains("desBox")){
            abstractLevelClass.collisionWithDesWall(collisionPoint, contact.getFixtureB().getBody().getUserData().toString(), contact.getFixtureA().getBody().getUserData().toString().substring(6));
        }
        else if(contact.getFixtureA().getBody().getUserData().toString().contains("colorWall")){
            abstractLevelClass.collisionWithColorWall(collisionPoint, contact.getFixtureB().getBody().getUserData().toString(), contact.getFixtureA().getBody().getUserData().toString().substring(9));
        }
        else if(contact.getFixtureA().getBody().getUserData().toString().length() > 2) {
            abstractLevelClass.ballToHole(contact.getFixtureA().getBody().getUserData().toString(), contact.getFixtureB().getBody().getUserData().toString());
        }
        else {
            abstractLevelClass.collisionBasedOnGamasutra(collisionPoint, contact.getFixtureA().getBody().getUserData().toString(), contact.getFixtureB().getBody().getUserData().toString());
            abstractLevelClass.collision(contact.getFixtureA().getBody().getUserData().toString(), contact.getFixtureB().getBody().getUserData().toString());
        }

    }

    @Override
    public void endContact(Contact contact) {
        //abstractLevelClass.collision(contact.getFixtureB().getBody().getUserData().toString(), contact.getFixtureB().getBody().getUserData().toString());
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        abstractLevelClass.cam.unproject(touch.set(screenX, screenY, 0));
        if (pointer > 0)
            return false;
        //if(abstractLevelClass.currentLine.isTouching())
            //abstractLevelClass.addCurrentLine();
        abstractLevelClass.currentLine.setBegin(touch.x, touch.y);
        abstractLevelClass.currentLine.setEnd(touch.x, touch.y);
        abstractLevelClass.currentLine.setTouching(true);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer > 0)
            return false;
        abstractLevelClass.currentLine.setTouching(false);
        if(abstractLevelClass.isGameActive())
            abstractLevelClass.addCurrentLine();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer > 0)
            return false;
        abstractLevelClass.cam.unproject(touch.set(screenX, screenY, 0));
        abstractLevelClass.currentLine.setEnd(touch.x, touch.y);
        return true;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}