package com.pawelinkball.game.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Pawel on 07.07.2017.
 */
public class Line {
    private Vector2 begin;
    private Vector2 end;
    private double tangent; // współczynnik kierunkowy prostej; tangens alfa
    private double angle;

    public Line(Vector2 b, Vector2 e) {
        begin = b;
        end = e;
        tangent = (begin.y - end.y) / (begin.x - end.x);
        angle = Math.atan(tangent);

    }

    public Vector2 getBegin() {
        return begin;
    }

    public Vector2 getEnd() {
        return end;
    }

    public double getAngle() {
        return angle;
    }
}
