package com.mygdx.game.android.DataBase;

import com.mygdx.game.DataBase.DaosAccess;
import com.mygdx.game.DataBase.NoteDaoInterface;
import com.mygdx.game.DataBase.SongDaoInterface;

/**
 * Created by sebastien on 28/12/14.
 */
public class AndroidDaosAccess implements DaosAccess{

    private final NoteDaoInterface noteDao;
    private final SongDaoInterface songDao;

    public AndroidDaosAccess(NoteDaoInterface noteDao, SongDaoInterface songDao){
        this.noteDao = noteDao;
        this.songDao = songDao;
    }

    public NoteDaoInterface getNoteDao() {
        return noteDao;
    }

    public SongDaoInterface getSongDao() {
        return songDao;
    }
}
