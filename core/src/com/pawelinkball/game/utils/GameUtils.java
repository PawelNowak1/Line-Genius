package com.pawelinkball.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.pawelinkball.game.InkBall;

/**
 * Created by Pawel on 07.09.2017.
 */
public class GameUtils {

    public GameUtils() {
    }

    public Color getColor(String colorID){
        if(colorID.equals(InkBall.YELLOW))
            return Color.YELLOW;
        if(colorID.equals(InkBall.BLUE))
            return Color.BLUE;
        if(colorID.equals(InkBall.WHITE))
            return Color.WHITE;
        if(colorID.equals(InkBall.BLACK))
            return Color.BLACK;
        if(colorID.equals(InkBall.RED))
            return Color.RED;
        if(colorID.equals(InkBall.GREEN))
            return Color.GREEN;
        return Color.YELLOW;
    }
}
