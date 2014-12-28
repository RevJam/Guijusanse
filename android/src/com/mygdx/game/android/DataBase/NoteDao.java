package com.mygdx.game.android.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mygdx.game.DataBase.NoteDaoInterface;
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
    public void add(Note note) {
        open();
        ContentValues value = new ContentValues();
        value.put(DataBaseMaker.NOTE_TIME, note.getTemps());
        value.put(DataBaseMaker.NOTE_POSITION, note.getPosition());
        value.put(DataBaseMaker.NOTE_DUREE, note.getDuree());
        value.put(DataBaseMaker.ID_SONG, note.getIdChanson());
        mDb.insert(DataBaseMaker.NOTE_TABLE, null, value);
    }

    /**
     * Suppression d'une note de la base de donnee
     * @param id
     * @return
     */
    public void delete(int id) {
        open();
        mDb.delete(DataBaseMaker.NOTE_TABLE," id = ?", new String[] {String.valueOf(id)});
    }

    /**
     * Mise a jour d'une note dans la base de donnee
     * @param note
     * @return
     */
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
    public Note get(int id) {
        open();
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.NOTE_TIME+", "+DataBaseMaker.NOTE_POSITION
                +", "+DataBaseMaker.NOTE_DUREE+", "+DataBaseMaker.ID_SONG+  " from " + DataBaseMaker.NOTE_TABLE
                + " where id = ?", new String[]{String.valueOf(id)});
        Note note = new Note();
        note.setIdNote(c.getInt(0));
        note.setTemps(c.getInt(1));
        note.setPosition(c.getInt(2));
        note.setDuree(c.getInt(3));
        note.setIdChanson(c.getInt(4));
        return note;
    }

    /**
     * Renvoie toute les notes contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    public List<Note> getAll() throws Exception{
        open();
        List<Note> liste = new ArrayList<Note>();
        Note note = new Note();
        Cursor c = mDb.query(DataBaseMaker.NOTE_TABLE, new String[]{"id",
                DataBaseMaker.NOTE_TIME,DataBaseMaker.NOTE_POSITION,DataBaseMaker.NOTE_DUREE,DataBaseMaker.ID_SONG},"*", null, null, null, null);
        while (c.moveToNext()) {
            note.setIdNote(c.getInt(0));
            note.setTemps(c.getInt(1));
            note.setPosition(c.getInt(2));
            note.setDuree(c.getInt(3));
            note.setIdChanson(c.getInt(4));
           liste.add(note);
        }
        c.close();
        return liste;
    }
}
