package com.mygdx.game.android.DataBase;

import com.mygdx.game.DataBase.DaosAccess;
import com.mygdx.game.DataBase.NoteDaoInterface;
import com.mygdx.game.DataBase.ScoreDaoInterface;
import com.mygdx.game.DataBase.SongDaoInterface;

/**
 * Created by sebastien on 28/12/14.
 */
public class AndroidDaosAccess implements DaosAccess{

    private final NoteDaoInterface noteDao;
    private final SongDaoInterface songDao;
    private final ScoreDaoInterface scoreDao;


    public AndroidDaosAccess(NoteDaoInterface noteDao, SongDaoInterface songDao, ScoreDaoInterface scoreDao){
        this.noteDao = noteDao;
        this.songDao = songDao;
        this.scoreDao = scoreDao;
    }

    /**
     * permet d'acceder au fonctions NoteDao
     *
     * @return
     */
    @Override
    public NoteDaoInterface getNoteDao() {
        return noteDao;
    }

    /**
     * permet d'acceder au fonctions SongDao
     *
     * @return
     */
    @Override
    public SongDaoInterface getSongDao() {
        return songDao;
    }

    /**
     * permet d'acceder au fonctions ScoreDao
     *
     * @return
     */
    @Override
    public ScoreDaoInterface getScoreDao() {
        return scoreDao;
    }
}
