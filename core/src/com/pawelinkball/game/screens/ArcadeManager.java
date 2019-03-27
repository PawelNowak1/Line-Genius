package com.pawelinkball.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


import java.util.Random;

/**
 * Created by Pawel on 05.08.2017.
 */
public class ArcadeManager {

    private final String numberLevels = "010203040506070809101112131415161718192021222324252627282930313233"; //343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899";
    private String arrayLevels = numberLevels;
    public int currentLevel = 1;
    private Preferences pref;
    public ArcadeManager(){

    }

    public AbstractLevelClass getLevel(Game game){
        Random generator = new Random();
        pref = Gdx.app.getPreferences("game");

        if(!pref.getString("ArrayLevels").equals("empty"))
            arrayLevels = pref.getString("ArrayLevels");

        if(arrayLevels.length() == 0)
            arrayLevels = numberLevels;
        StringBuilder sb = new StringBuilder(arrayLevels);
        int n = generator.nextInt(arrayLevels.length()); // od 0 do numberOfLevels - 1 losuje
        if(n % 2 != 0)
            n--;
        char i = arrayLevels.charAt(n);
        char j = arrayLevels.charAt(n + 1);
        sb.deleteCharAt(n);
        sb.deleteCharAt(n);
        arrayLevels = sb.toString();
        currentLevel = Character.getNumericValue(i) * 10 + Character.getNumericValue(j);
        pref.putInteger("ArcadeLevel", currentLevel);
        pref.putString("ArrayLevels", arrayLevels);
        pref.flush();
        Gdx.app.log("sss", arrayLevels);
        return getLevelClass(game, currentLevel);
    }

    public AbstractLevelClass getLevelClass(Game game, int n){
        currentLevel = n;
        Gdx.app.log("integer n", Integer.toString(n));
        switch (n){
            case 1: return new Level1(game);

            case 2: return new Level2(game);

            case 3: return new Level3(game);

            case 4: return new Level4(game);

            case 5: return new Level5(game);

            case 6: return new Level6(game);

            case 7: return new Level7(game);

            case 8: return new Level8(game);

            case 9: return new Level9(game);

            case 10: return new Level10(game);

            case 11: return new Level11(game);

            case 12: return new Level12(game);

            case 13: return new Level13(game);

            case 14: return new Level14(game);

            case 15: return new Level15(game);

            case 16: return new Level16(game);

            case 17: return new Level17(game);

            case 18: return new Level18(game);

            case 19: return new Level19(game);

            case 20: return new Level20(game);

            case 21: return new Level21(game);

            case 22: return new Level22(game);

            case 23: return new Level23(game);

            case 24: return new Level24(game);

            case 25: return new Level25(game);

            case 26: return new Level26(game);

            case 27: return new Level27(game);

            case 28: return new Level28(game);

            case 29: return new Level29(game);

            case 30: return new Level30(game);

            case 31: return new Level31(game);

            case 32: return new Level32(game);

            case 33: return new Level33(game);
        }
        return new Level6(game);
    }
}
