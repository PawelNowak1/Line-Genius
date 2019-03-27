package com.pawelinkball.game.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Pawel on 07.07.2017.
 */
public class CurrentLine {
    public Vector2 begin;
    public Vector2 end;
    private boolean touching;

    public CurrentLine(){
        touching = false;
        begin = new Vector2(0, 0);
        end = new Vector2(0, 0);
    }

    public void setBegin(float a, float b) {
        begin.set(a, b);
    }

    public void setEnd(float a, float b) {
        end.set(a, b);
    }

    public Vector2 getBegin() {
        return begin;
    }

    public Vector2 getEnd() {
        return end;
    }

    public boolean isTouching(){return touching;}

    public void setTouching(boolean a){
        touching = a;
    }
}
