package com.mygdx.game.android;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.os.Environment;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.dataBase.InitDataBase;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*probleme de création de base de donnée aec message d'erreur bizarre...*/
        //InitDataBase init = new InitDataBase();
		initialize(new MyGdxGame(), config);
	}
}
