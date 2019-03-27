package com.pawelinkball.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.pawelinkball.game.InkBall;

/**
 * Created by Pawel on 07.07.2017.
 */
public class Ball {
    private float speedX;
    private float speedY;
    private float ray, angularVelocity;
    private float multiplier;
    private float lineWidth;
    private String colorID;
    private Texture texture;
    private Color color;
    private boolean active = true;
    public Vector2 lastCollisionPoint;

    public Ball(String col, String file, float m, float aVelocity){
        colorID = col;
        color = InkBall.gameUtils.getColor(colorID);
        //texture = new Texture(file);
        speedX = 10;
        speedY = 10;
        ray = 0;
        angularVelocity = aVelocity;
        multiplier = m;
        lineWidth = 5;
        lastCollisionPoint = new Vector2(-30, -40);
    }

    public Vector2 getLastCollisionPoint() {
        return lastCollisionPoint;
    }

    public void setLastCollisionPoint(Vector2 lastCollisionPoint) {
        this.lastCollisionPoint = lastCollisionPoint;
    }

    public void draw(SpriteBatch sb, Vector2 pos){
        sb.draw(texture, pos.x - ray, pos.y - ray, ray * 2, ray * 2);
    }

    public void draw(ShapeRenderer shapeRenderer, Vector2 pos){
        shapeRenderer.setColor(color);
        shapeRenderer.circle(pos.x, pos.y, ray, 100);
    }

    public void setColor(String acolor) {
        color = InkBall.gameUtils.getColor(acolor);
    }

    public Color getColor(){
        return color;
    }

    public void setColorID(String colorID) {
        this.colorID = colorID;
    }


    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getRay() {
        return ray;
    }

    public void setRay(float ray) {
        this.ray = ray;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getColorID(){
        return colorID;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }


}
