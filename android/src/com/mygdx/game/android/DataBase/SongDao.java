package com.mygdx.game.android.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mygdx.game.DataBase.SongDaoInterface;
import com.mygdx.game.fichier.Chanson;
import com.mygdx.game.fichier.TypeDifficultee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastien on 28/12/14.
 */
public class SongDao extends Dao implements SongDaoInterface{

    public SongDao(Context pContext) {
        super(pContext);
    }

    /**
     * Ajout d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    @Override
    public void add(Chanson chanson) {
        open();
        if(findId(chanson) == -1) {
            ContentValues value = new ContentValues();
            value.put(DataBaseMaker.SONG_NAME, chanson.getTitle());
            value.put(DataBaseMaker.SONG_DIFFICULTY, String.valueOf(chanson.getDifficulter()));
            mDb.insert(DataBaseMaker.SONG_TABLE, null, value);
        }
    }

    /**
     * Suppression d'une chanson de la base de donnee
     * @param id
     * @return
     */
    @Override
    public void delete(int id) {
        open();
        mDb.delete(DataBaseMaker.SONG_TABLE," id = ?", new String[] {String.valueOf(id)});
    }

    /**
     * Mise a jour d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    @Override
    public void update(Chanson chanson) {
        open();
        ContentValues value = new ContentValues();
        value.put(DataBaseMaker.SONG_NAME, chanson.getTitle());
        value.put(DataBaseMaker.SONG_DIFFICULTY, String.valueOf(chanson.getDifficulter()));
        mDb.update(DataBaseMaker.SONG_TABLE, value," id=?", new String[] {String.valueOf(chanson.getIdChanson())});
    }


    /**
     * Renvoi une chanson en fonction de son id
     * @param id
     * @return
     */
    @Override
    public Chanson get(int id) {
        open();
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.SONG_NAME+", "+DataBaseMaker.SONG_DIFFICULTY
                +  " from " + DataBaseMaker.SONG_TABLE
                + " where id=?", new String[]{String.valueOf(id)});
        Chanson chanson = new Chanson();
       while(c.moveToNext()){
            chanson.setIdChanson(c.getInt(0));
            chanson.setTitle(c.getString(1));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(2)));
        }
        return chanson;
    }

    /**
     * Renvoi l'id de la chanson voulue
     *
     * @param chanson
     * @return
     */
    @Override
    public int findId(Chanson chanson) {
        open();
        int id = -1;
        String[] params = new String[]{chanson.getTitle(),String.valueOf(chanson.getDifficulter())};
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.SONG_TABLE
                + " where "+ DataBaseMaker.SONG_NAME + "=? and "
                + DataBaseMaker.SONG_DIFFICULTY + "=?", params);
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex("id"));
        }
        c.close();
        return id;
    }

    /**
     * Renvoie toute les chansons contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    @Override
    public List<Chanson> getAll() throws Exception{
        open();
        List<Chanson> liste = new ArrayList<Chanson>();
        Chanson chanson;
        Cursor c = mDb.rawQuery("select id, "+DataBaseMaker.SONG_NAME+", "+DataBaseMaker.SONG_DIFFICULTY+" from "+ DataBaseMaker.SONG_TABLE+" ",null);
        while (c.moveToNext()) {
            chanson = new Chanson();
            chanson.setIdChanson(c.getInt(c.getColumnIndex("id")));
            chanson.setTitle(c.getString(c.getColumnIndex(DataBaseMaker.SONG_NAME)));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(String.valueOf(DataBaseMaker.SONG_DIFFICULTY)))));
            liste.add(chanson);
        }
        c.close();
        return liste;
    }

    /**
     * @param difficulte
     * @return
     * @throws Exception
     */
    @Override
    public List<Chanson> getSongByDifficulty(String difficulte) throws Exception {
        open();
        List<Chanson> liste = new ArrayList<Chanson>();
        Chanson chanson = new Chanson();
        String[] params = new String[]{difficulte};
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.SONG_TABLE
                + " where " + DataBaseMaker.SONG_DIFFICULTY + "=?", params);
        return liste;
    }


}
