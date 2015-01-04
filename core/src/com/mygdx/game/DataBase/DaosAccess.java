package com.mygdx.game.DataBase;

/**
 * Created by sebastien on 28/12/14.
 */
public interface DaosAccess {

    /**
     * permet d'acceder au fonctions NoteDao
     * @return
     */
    public NoteDaoInterface getNoteDao();

    /**
     * permet d'acceder au fonctions SongDao
     * @return
     */
    public SongDaoInterface getSongDao();

    /**
     * permet d'acceder au fonctions ScoreDao
     * @return
     */
    public ScoreDaoInterface getScoreDao();

}
