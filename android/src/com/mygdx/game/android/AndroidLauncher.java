package com.mygdx.game.android;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.DataBase.DaosAccess;
import com.mygdx.game.DataBase.NoteDaoInterface;
import com.mygdx.game.DataBase.SongDaoInterface;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.android.DataBase.AndroidDaosAccess;
import com.mygdx.game.android.DataBase.Dao;
import com.mygdx.game.android.DataBase.NoteDao;
import com.mygdx.game.android.DataBase.SongDao;
import com.mygdx.game.fichier.Chanson;
import com.mygdx.game.fichier.Lecture;
import com.mygdx.game.fichier.Note;
import com.mygdx.game.fichier.TypeDifficultee;

import java.io.IOException;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SongDaoInterface songDaoInterface = new SongDao(this);
        NoteDaoInterface noteDaoInterface = new NoteDao(this);

        AndroidDaosAccess daosAccess = new AndroidDaosAccess(noteDaoInterface,songDaoInterface);

        Lecture lectur = new Lecture();
        try {
            String essai = Uri.parse("file:///android_asset/file/ListeChanson.txt").toString();
            lectur.lecture(essai);
        } catch (IOException e) {
            e.printStackTrace();
        }

		initialize(new MyGdxGame(daosAccess), config);
	}
}
