package com.mygdx.game.android.dataBase;

/**
 * Created by sebastien on 28/12/14.
 */
public class DataBaseMaker extends Exception {

    /*Table Song guijusanse*/
    protected static final int NB_COLUMNS_SONG = 4;

    protected static final String SONG_TABLE    = "song";
    protected static final String SONG_NAME = "intitule";
    protected static final String SONG_DIFFICULTY = "diffculte";
    protected static final String TEMPS_CHANSON = "tempsChanson";
    private static final String CREATETABLEIFNOTEXIST = "CREATE TABLE IF NOT EXISTS ";
    private static final String IDINTPRIMARYKEYAUTOINCREMENTNOTNULL = "id INTEGER PRIMARY KEY AUTOINCREMENT,";

    protected static final String CREATE_SONG_TABLE = CREATETABLEIFNOTEXIST + SONG_TABLE + "(" +
            IDINTPRIMARYKEYAUTOINCREMENTNOTNULL +
            SONG_NAME + " VARCHAR(255)," +
            SONG_DIFFICULTY + " INT,"+
            TEMPS_CHANSON + " INT );";

    protected static final String SONG_TABLE_DROP = "DROP TABLE IF EXISTS " + SONG_TABLE + ";";

    /*Table Note*/
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
            ID_SONG+" INT,"+
            "FOREIGN KEY("+ID_SONG+") " + "REFERENCES "+SONG_TABLE+ "(id)" +
            " ON DELETE CASCADE );";

    protected static final String NOTE_TABLE_DROP = "DROP TABLE IF EXISTS " + NOTE_TABLE + ";";

    /*Table Score*/
    protected static final int NB_COLUMNS_SCORE = 5;

    protected static final String SCORE_TABLE = "score";
    protected static final String SCORE_PLAYER = "joueur";
    protected static final String SCORE_SONGTITLE = "titre_chanson";
    protected static final String SCORE_DIFFICULTY = "difficulte";
    protected static final String SCORE_SCORE = "score";

    protected static final String CREATE_SCORE_TABLE = CREATETABLEIFNOTEXIST + SCORE_TABLE + "(" +
            IDINTPRIMARYKEYAUTOINCREMENTNOTNULL +
            SCORE_PLAYER + " INT," +
            SCORE_SONGTITLE + " VARCHAR(255)," +
            SCORE_DIFFICULTY + " VARCHAR(255)," +
            SCORE_SCORE + " INT );";

    protected static final String SCORE_TABLE_DROP = "DROP TABLE IF EXISTS " + SCORE_TABLE + ";";
}
