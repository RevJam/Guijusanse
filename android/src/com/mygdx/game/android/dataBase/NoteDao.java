package com.mygdx.game.android.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.mygdx.game.dataBase.NoteDaoInterface;
import com.mygdx.game.fichier.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastien on 28/12/14.
 */
public class NoteDao extends Dao implements NoteDaoInterface {

    public NoteDao(Context pContext) {
        super(pContext);
    }

    /**
     * Ajout d'une note dans la base de donnee
     * @param note
     * @return
     */
    @Override
    public void add(Note note) {
        open();
        if(findId(note) == -1) {
            ContentValues value = new ContentValues();
            value.put(DataBaseMaker.NOTE_TIME, note.getTemps());
            value.put(DataBaseMaker.NOTE_POSITION, note.getPosition());
            value.put(DataBaseMaker.NOTE_DUREE, note.getDuree());
            value.put(DataBaseMaker.ID_SONG, note.getIdChanson());
            mDb.insert(DataBaseMaker.NOTE_TABLE, null, value);
        }
    }

    /**
     * Suppression d'une note de la base de donnee
     * @param id
     * @return
     */
    @Override
    public void delete(int id) {
        open();
        mDb.delete(DataBaseMaker.NOTE_TABLE," id = ?", new String[] {String.valueOf(id)});
    }

    /**
     * Mise a jour d'une note dans la base de donnee
     * @param note
     * @return
     */
    @Override
    public void update(Note note) {
        open();
        ContentValues value = new ContentValues();
        value.put(DataBaseMaker.NOTE_TIME, note.getTemps());
        value.put(DataBaseMaker.NOTE_POSITION, note.getPosition());
        value.put(DataBaseMaker.NOTE_DUREE, note.getDuree());
        mDb.update(DataBaseMaker.NOTE_TABLE, value," id = ?", new String[] {String.valueOf(note.getIdNote())});
    }

    /**
     * Renvoi une note en fonction de son id
     * @param id
     * @return
     */
    @Override
    public Note get(int id) {
        open();
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.NOTE_TIME+", "+DataBaseMaker.NOTE_POSITION
                +", "+DataBaseMaker.NOTE_DUREE+", "+DataBaseMaker.ID_SONG+  " from " + DataBaseMaker.NOTE_TABLE
                + " where id = ?", new String[]{String.valueOf(id)});
        Note note = new Note();
        while(c.moveToNext()) {
            note.setIdNote(c.getInt(c.getColumnIndex("id")));
            note.setTemps(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_TIME)));
            note.setPosition(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_POSITION)));
            note.setDuree(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_DUREE)));
            note.setIdChanson(c.getInt(c.getColumnIndex(DataBaseMaker.ID_SONG)));
        }
        c.close();
        return note;
    }

    /**
     * Renvoi l'id d'une note
     *
     * @param note
     * @return
     */
    @Override
    public int findId(Note note) {
        open();
        int id = -1;
        String[] params = new String[]{String.valueOf(note.getTemps()),String.valueOf(note.getPosition()),String.valueOf(note.getDuree()),String.valueOf(note.getIdChanson())};
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.NOTE_TABLE
                + " where "+ DataBaseMaker.NOTE_TIME + "=? and "
                + DataBaseMaker.NOTE_POSITION + "=? and "
                + DataBaseMaker.NOTE_DUREE + "=? and "
                + DataBaseMaker.ID_SONG + "=?", params);
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex("id"));
        }
        c.close();
        return id;
    }

    /**
     * Renvoie toute les notes contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    @Override
    public List<Note> getAll() throws Exception{
        open();
        List<Note> liste = new ArrayList<Note>();
        Note note;
        Cursor c = mDb.query(DataBaseMaker.NOTE_TABLE, new String[]{"id",
                DataBaseMaker.NOTE_TIME,DataBaseMaker.NOTE_POSITION,DataBaseMaker.NOTE_DUREE,DataBaseMaker.ID_SONG},"*", null, null, null, null);
        while (c.moveToNext()) {
            note = new Note();
            note.setIdNote(c.getInt(c.getColumnIndex("id")));
            note.setTemps(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_TIME)));
            note.setPosition(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_POSITION)));
            note.setDuree(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_DUREE)));
            note.setIdChanson(c.getInt(c.getColumnIndex(DataBaseMaker.ID_SONG)));
           liste.add(note);
        }
        c.close();
        return liste;
    }

    /**
     * renvoi une liste de note par rapport à l'id d'une chanson
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Note> getAllBySongId(int idSong) throws Exception {
        open();
        List<Note> liste = new ArrayList<Note>();
        Note note;
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.NOTE_TABLE
                + " where " + DataBaseMaker.ID_SONG + "=?", new String[]{String.valueOf(idSong)});
        while (c.moveToNext()) {
            note = new Note();
            note.setIdNote(c.getInt(c.getColumnIndex("id")));
            note.setTemps(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_TIME)));
            note.setPosition(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_POSITION)));
            note.setDuree(c.getInt(c.getColumnIndex(DataBaseMaker.NOTE_DUREE)));
            note.setIdChanson(c.getInt(c.getColumnIndex(DataBaseMaker.ID_SONG)));
            liste.add(note);
        }
        c.close();
        return liste;
    }


}
