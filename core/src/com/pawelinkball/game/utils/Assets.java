package com.pawelinkball.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Pawel on 19.07.2017.
 */
public class Assets implements Disposable{
    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    public AssetHole hole;
    public AssetDestroyableBox destroyableBox;
    public AssetStartBall startBall;
    public AssetFonts fonts;

    private Assets(){}

    public void init(AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.load("game.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get("game.atlas");

        for(Texture t : atlas.getTextures()){
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        hole = new AssetHole(atlas);
        destroyableBox = new AssetDestroyableBox(atlas);
        startBall = new AssetStartBall(atlas);
        fonts = new AssetFonts();
    }

    public class AssetHole{
        public final TextureAtlas.AtlasRegion black;
        public final TextureAtlas.AtlasRegion green;
        public final TextureAtlas.AtlasRegion yellow;
        public final TextureAtlas.AtlasRegion blue;
        public final TextureAtlas.AtlasRegion red;
        public final TextureAtlas.AtlasRegion white;


        public AssetHole(TextureAtlas atlas){
            black = atlas.findRegion("black");
            green = atlas.findRegion("green");
            yellow = atlas.findRegion("yellow");
            blue = atlas.findRegion("blue");
            red = atlas.findRegion("red");
            white = atlas.findRegion("white");
        }
    }

    public class AssetDestroyableBox{
        public final TextureAtlas.AtlasRegion greenbox;
        public final TextureAtlas.AtlasRegion yellowbox;
        public final TextureAtlas.AtlasRegion bluebox;
        public final TextureAtlas.AtlasRegion redbox;
        public final TextureAtlas.AtlasRegion block;

        public AssetDestroyableBox(TextureAtlas atlas){
            greenbox = atlas.findRegion("greenbox");
            yellowbox = atlas.findRegion("yellowbox");
            bluebox = atlas.findRegion("bluebox");
            redbox = atlas.findRegion("redbox");
            block = atlas.findRegion("block");
        }
    }

    public class AssetStartBall{
        public final TextureAtlas.AtlasRegion startBall;
        public final TextureAtlas.AtlasRegion logo;

        public AssetStartBall(TextureAtlas atlas){
            logo = atlas.findRegion("logo");
            startBall = atlas.findRegion("startball");
        }
    }

    public class AssetFonts{
        public final BitmapFont bitmapFontSmall;
        public final BitmapFont bitmapFontBig;
        public final BitmapFont bitmapFontNormal;

        public AssetFonts(){
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
            parameter.size = 16;
            bitmapFontSmall = generator.generateFont(parameter);
            parameter.size = 48;
            bitmapFontBig = generator.generateFont(parameter);
            parameter.size = 26;
            bitmapFontNormal = generator.generateFont(parameter);
            bitmapFontSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            bitmapFontBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            bitmapFontNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            generator.dispose();
        }
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fonts.bitmapFontBig.dispose();
        fonts.bitmapFontSmall.dispose();
        fonts.bitmapFontNormal.dispose();
    }
}
