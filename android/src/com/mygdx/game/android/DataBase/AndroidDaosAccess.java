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

    @Override
    public NoteDaoInterface getNoteDao() {
        return noteDao;
    }

    @Override
    public SongDaoInterface getSongDao() {
        return songDao;
    }

    @Override
    public ScoreDaoInterface getScoreDao() {
        return scoreDao;
    }
}
