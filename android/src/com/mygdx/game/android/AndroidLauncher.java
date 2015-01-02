package com.mygdx.game.android;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.mygdx.game.DataBase.NoteDaoInterface;
import com.mygdx.game.DataBase.ScoreDaoInterface;
import com.mygdx.game.DataBase.SongDaoInterface;
import com.mygdx.game.Game.TimeInterface;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.android.DataBase.AndroidDaosAccess;
import com.mygdx.game.android.DataBase.NoteDao;
import com.mygdx.game.android.DataBase.ScoreDao;
import com.mygdx.game.android.DataBase.SongDao;
import com.mygdx.game.fichier.Lecture;

import java.io.IOException;
import java.io.InputStream;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SongDaoInterface songDaoInterface = new SongDao(this);
        NoteDaoInterface noteDaoInterface = new NoteDao(this);
        ScoreDaoInterface scoreDaoInterface = new ScoreDao(this);

        AndroidDaosAccess daosAccess = new AndroidDaosAccess(noteDaoInterface,songDaoInterface,scoreDaoInterface);
        TimeInterface timeInterface = new TimeImpl();
        Lecture lectur = new Lecture(daosAccess);
        try {
            InputStream in = getAssets().open("file/ListeChanson.txt");
            lectur.lecture(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

		initialize(new MyGdxGame(daosAccess, timeInterface), config);
	}
}
