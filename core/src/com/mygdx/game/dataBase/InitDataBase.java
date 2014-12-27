package com.mygdx.game.dataBase;

import com.badlogic.gdx.Gdx;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by sebastien on 26/12/14.
 */
public class InitDataBase {

    /**
     * connectio
     */
    private ConnectionFactory co=null;
    private DaoSong daoSong;
    private DaoNote daoNote;

    private static final Logger LOGGER = Logger.getLogger(InitDataBase.class.getName());

    public InitDataBase(){
        daoSong = new DaoSong();
        daoNote = new DaoNote();
        createDataBase();
    }

    public void createDataBase() {
        LOGGER.finest(InitDataBase.class.getName() + " début createDataBase");
        try {

            co=new ConnectionFactory();
            co.createConnection();
            daoSong.createTable();
            daoNote.createTable();

        } catch (SQLException ex){
            LOGGER.finest(InitDataBase.class.getName() + " erreur createDataBase "+ex);
        }
        LOGGER.finest(InitDataBase.class.getName() + " fin createDataBase");
    }


}
