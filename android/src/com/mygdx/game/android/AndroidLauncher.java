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
import com.mygdx.game.android.GameTimer.TimeImpl;
import com.mygdx.game.fichier.Lecture;

import java.io.IOException;
import java.io.InputStream;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
