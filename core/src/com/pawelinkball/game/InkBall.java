package com.pawelinkball.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.pawelinkball.game.screens.ArcadeManager;
import com.pawelinkball.game.screens.StartLevel;
import com.pawelinkball.game.utils.Assets;
import com.pawelinkball.game.utils.GameUtils;

public class InkBall extends Game {

	public static final String YELLOW = "1000";
	public static final String BLUE = "1001";
	public static final String RED = "1002";
	public static final String BLACK = "1003";
	public static final String WHITE = "1004";
	public static final String GREEN = "1005";

	public static final ArcadeManager arcadeManager = new ArcadeManager();
	public static final GameUtils gameUtils = new GameUtils();
	private Preferences pref;


	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_NONE);
		Assets.instance.init(new AssetManager());
		initPreferences();
		setScreen(new StartLevel(this));
	}

	private void initPreferences(){

		pref = Gdx.app.getPreferences("game");
		if(!pref.contains("HighScore"))
			pref.putLong("HighScore", 0);

		if(!pref.contains("LevelScore"))
			pref.putInteger("LevelScore", 1);

		if(!pref.contains("CurrentScore"))
			pref.putLong("CurrentScore", 0);

		if(!pref.contains("ArrayLevels"))
			pref.putString("ArrayLevels", "empty");

		if(!pref.contains("ArcadeLevel"))
			pref.putInteger("ArcadeLevel", 2); // arcade level zawsze zaczyna się od 2 levela

		pref.flush();
	}
}