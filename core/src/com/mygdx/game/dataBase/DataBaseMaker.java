package com.mygdx.game.dataBase;

/**
 * Created by sebastien on 26/12/14.
 */
public class DataBaseMaker extends Exception{

    /*Table Song guijusanse*/
    protected static final int NB_COLUMNS_SONG = 4;

    protected static final String SONG_TABLE    = "song";
    protected static final String SONG_NAME = "intitule";
    protected static final String SONG_DIFFICULTY = "diffculte";
    protected static final String SONG_SCORE = "score";

    private static final String CREATETABLEIFNOTEXIST = "CREATE TABLE IF NOT EXISTS ";
    private static final String IDINTPRIMARYKEYAUTOINCREMENTNOTNULL = "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,";

    protected static final String CREATE_SONG_TABLE = CREATETABLEIFNOTEXIST + SONG_TABLE + "(" +
            IDINTPRIMARYKEYAUTOINCREMENTNOTNULL +
            SONG_NAME + " VARCHAR(255)," +
            SONG_NAME + " VARCHAR(255)," +
            SONG_DIFFICULTY + " INT,"+
            SONG_SCORE  + " INT );";

    /*Table Not*/
    protected static final int NB_COLUMNS_NOTE = 5;

    protected static final String NOTE_TABLE    = "note";
    protected static final String NOTE_TIME = "temps";
    protected static final String NOTE_POSITION = "position";
    protected static final String NOTE_DUREE = "duree";
    protected static final String ID_SONG = "id_song";

    protected static final String CREATE_NOTE_TABLE = CREATETABLEIFNOTEXIST + NOTE_TABLE + "(" +
            IDINTPRIMARYKEYAUTOINCREMENTNOTNULL +
            NOTE_TIME + " INT," +
            NOTE_POSITION + " INT," +
            NOTE_DUREE + " INT,"+
            "FOREIGN KEY("+ID_SONG+") " + "REFERENCES "+SONG_TABLE+ "(id)" +
            " ON DELETE CASCADE );";




}
