package com.mygdx.game.DataBase;

/**
 * Created by sebastien on 28/12/14.
 */
public interface DaosAccess {

    public NoteDaoInterface getNoteDao();

    public SongDaoInterface getSongDao();

    public ScoreDaoInterface getScoreDao();

}
