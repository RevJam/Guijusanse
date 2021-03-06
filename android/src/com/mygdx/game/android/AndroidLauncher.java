package com.mygdx.game.android;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.WindowManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.mygdx.game.dataBase.NoteDaoInterface;
import com.mygdx.game.dataBase.ScoreDaoInterface;
import com.mygdx.game.dataBase.SongDaoInterface;
import com.mygdx.game.game.TimeInterface;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.android.dataBase.AndroidDaosAccess;
import com.mygdx.game.android.dataBase.NoteDao;
import com.mygdx.game.android.dataBase.ScoreDao;
import com.mygdx.game.android.dataBase.SongDao;
import com.mygdx.game.android.gameTimer.TimeImpl;
import com.mygdx.game.fichier.Lecture;

import java.io.IOException;
import java.io.InputStream;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //empeche la mise en veille pendant le jeu
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //declaration des interfaces pour la dao
        SongDaoInterface songDaoInterface = new SongDao(this);
        NoteDaoInterface noteDaoInterface = new NoteDao(this);
        ScoreDaoInterface scoreDaoInterface = new ScoreDao(this);

        //declaration et initialisation de la dao
        AndroidDaosAccess daosAccess = new AndroidDaosAccess(noteDaoInterface,songDaoInterface,scoreDaoInterface);

        //utilisation du temps
        TimeInterface timeInterface = new TimeImpl();

        //lecture de fichier pour la premiere installation
        Lecture lectur = new Lecture(daosAccess);
        try {
            InputStream in = getAssets().open("file/ListeChanson.txt");
            lectur.lecture(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //lancement du jeu
        initialize(new MyGdxGame(daosAccess, timeInterface), config);
    }
}
