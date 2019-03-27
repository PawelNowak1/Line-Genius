package com.pawelinkball.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.pawelinkball.game.InkBall;

public class DesktopLauncher {
	private static boolean rebuildAtlas = false;

	public static void main (String[] arg) {
		if(rebuildAtlas){
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxWidth = 2048;
			settings.maxHeight = 2048;
			settings.duplicatePadding = true;
			settings.debug = false;
			TexturePacker.process(settings, "image", "E:\\LibGdx\\InkBall_v2\\android\\assets", "game");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new InkBall(), config);
		int r1 = 3;
		int r2 = 5;
		//config.width = 160 * r1;
		//config.height = 160 * r2;
		config.width = 504;
		config.height = 896;
	}
}
