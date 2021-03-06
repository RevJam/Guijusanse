package com.mygdx.game.android.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mygdx.game.dataBase.SongDaoInterface;
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
            value.put(DataBaseMaker.TEMPS_CHANSON, chanson.getTempsChansonTotal());
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
        value.put(DataBaseMaker.TEMPS_CHANSON, chanson.getTempsChansonTotal());
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
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.SONG_NAME+", "+DataBaseMaker.SONG_DIFFICULTY+", "+DataBaseMaker.TEMPS_CHANSON
                +  " from " + DataBaseMaker.SONG_TABLE
                + " where id=?", new String[]{String.valueOf(id)});
        Chanson chanson = new Chanson();
       while(c.moveToNext()){
            chanson.setIdChanson(c.getInt(c.getColumnIndex("id")));
            chanson.setTitle(c.getString(c.getColumnIndex(DataBaseMaker.SONG_NAME)));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(String.valueOf(DataBaseMaker.SONG_DIFFICULTY)))));
            chanson.setTempsChansonTotal((c.getLong(c.getColumnIndex(DataBaseMaker.TEMPS_CHANSON))));
        }
        c.close();
        return chanson;
    }

    /**
     * recupere une chanson par rapport a un titre
     *
     * @param title
     * @return
     */
    @Override
    public Chanson getByTitle(String title) {
        open();
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.SONG_NAME+", "+DataBaseMaker.SONG_DIFFICULTY+", "+DataBaseMaker.TEMPS_CHANSON
                +  " from " + DataBaseMaker.SONG_TABLE
                + " where " + DataBaseMaker.SONG_NAME + "=?", new String[]{title});
        Chanson chanson = new Chanson();
        while(c.moveToNext()){
            chanson.setIdChanson(c.getInt(c.getColumnIndex("id")));
            chanson.setTitle(c.getString(c.getColumnIndex(DataBaseMaker.SONG_NAME)));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(String.valueOf(DataBaseMaker.SONG_DIFFICULTY)))));
            chanson.setTempsChansonTotal((c.getLong(c.getColumnIndex(DataBaseMaker.TEMPS_CHANSON))));
        }
        c.close();
        return chanson;
    }

    /**
     * recupere une chanson par rapport a un titre et une difficulte
     *
     * @param titre
     * @param difficulte
     * @return
     */
    @Override
    public Chanson getByTitleAndDifficulty(String titre, String difficulte) {
        open();
        Chanson chanson = new Chanson();
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.SONG_NAME+", "+DataBaseMaker.SONG_DIFFICULTY+", "+DataBaseMaker.TEMPS_CHANSON
                +  " from " + DataBaseMaker.SONG_TABLE
                + " where " + DataBaseMaker.SONG_NAME
                + "=? and " + DataBaseMaker.SONG_DIFFICULTY
                + "=?", new String[]{titre,difficulte});
        while(c.moveToNext()){
            chanson.setIdChanson(c.getInt(c.getColumnIndex("id")));
            chanson.setTitle(c.getString(c.getColumnIndex(DataBaseMaker.SONG_NAME)));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(String.valueOf(DataBaseMaker.SONG_DIFFICULTY)))));
            chanson.setTempsChansonTotal((c.getLong(c.getColumnIndex(DataBaseMaker.TEMPS_CHANSON))));
        }
        c.close();
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
        Cursor c = mDb.rawQuery("select id, "+DataBaseMaker.SONG_NAME+", "+DataBaseMaker.SONG_DIFFICULTY+", "+DataBaseMaker.TEMPS_CHANSON+" from "+ DataBaseMaker.SONG_TABLE+" ",null);
        while (c.moveToNext()) {
            chanson = new Chanson();
            chanson.setIdChanson(c.getInt(c.getColumnIndex("id")));
            chanson.setTitle(c.getString(c.getColumnIndex(DataBaseMaker.SONG_NAME)));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(String.valueOf(DataBaseMaker.SONG_DIFFICULTY)))));
            chanson.setTempsChansonTotal((c.getLong(c.getColumnIndex(DataBaseMaker.TEMPS_CHANSON))));
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
        Chanson chanson;
        String[] params = new String[]{difficulte};
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.SONG_TABLE
                + " where " + DataBaseMaker.SONG_DIFFICULTY + "=?", params);
        while (c.moveToNext()) {
            chanson = new Chanson();
            chanson.setIdChanson(c.getInt(c.getColumnIndex("id")));
            chanson.setTitle(c.getString(c.getColumnIndex(DataBaseMaker.SONG_NAME)));
            chanson.setDifficulter(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(String.valueOf(DataBaseMaker.SONG_DIFFICULTY)))));
            chanson.setTempsChansonTotal((c.getLong(c.getColumnIndex(DataBaseMaker.TEMPS_CHANSON))));
            liste.add(chanson);
        }
        c.close();
        return liste;
    }


}
