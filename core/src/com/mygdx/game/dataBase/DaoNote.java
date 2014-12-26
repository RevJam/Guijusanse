package com.mygdx.game.dataBase;

import com.mygdx.game.fichier.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastien on 26/12/14.
 */
public class DaoNote extends DataBaseMaker {

    public void createTable() {
        Dao.createDrop(CREATE_NOTE_TABLE);
    }

    /**
     * Ajout d'une note dans la base de donnee
     * @param note
     * @return
     */
    public int add(Note note) {
        String query = String.format(
                "INSERT INTO %s (%s,%s,%s,%s) VALUES('%d','%d','%d','%d');",
                NOTE_TABLE, NOTE_TIME, NOTE_POSITION, NOTE_DUREE, ID_SONG, note.getTemps(), note.getPosition(), note.getDuree(), note.getIdChanson());
        return Dao.add(query);
    }

    /**
     * Suppression d'une note de la base de donnee
     * @param id
     * @return
     */
    public int delete(int id) {
        String query = String.format("DELETE FROM %s WHERE id='%d';",
                NOTE_TABLE, id);
        return Dao.deleteUpdate(query);
    }

    /**
     * Mise a jour d'une note dans la base de donnee
     * @param note
     * @return
     */
    public int update(Note note) {
        String query = String.format("UPDATE %s SET %s='%d',%s='%d',%s='%d' WHERE id='%d';",
                NOTE_TABLE,
                NOTE_TIME, note.getTemps(),
                NOTE_POSITION, note.getPosition(),
                NOTE_DUREE, note.getDuree(),
                note.getIdNote());
        return Dao.deleteUpdate(query);
    }

    /**
     * Supprime tout les users de la base de donnee
     * @return
     */
    public int deleteAll() {
        String query = String.format("DELETE FROM %s;", SONG_TABLE);
        return Dao.deleteUpdate(query);
    }

    /**
     * Cherche l'id d'une chanson dans la base de donnee
     * @param note
     * @return
     */
    public int findId(Note note){
        String query =String.format("SELECT * FROM %s WHERE %s='%d' AND %s='%d' AND %s='%d' AND %s='%d' AND %s='%d';",
                NOTE_TABLE, NOTE_TIME, note.getTemps(), NOTE_POSITION, note.getPosition(), NOTE_DUREE, note.getDuree(), ID_SONG, note.getIdNote());
        Object[] object = Dao.getUniqueElement(query,NB_COLUMNS_SONG);
        if(object != null){
            return (Integer)object[0];
        }
        return -1;
    }

    /**
     * Renvoi une note en fonction de son id
     * @param id
     * @return
     */
    public Note get(int id) {
        Note note = new Note();
        String query = String.format("SELECT * FROM %s WHERE id='%d';", NOTE_TABLE, id);
        Object[] object = Dao.getUniqueElement(query, NB_COLUMNS_NOTE);
        note.setIdNote((Integer) object[0]);
        note.setTemps((Integer) object[1]);
        note.setPosition((Integer) object[2]);
        note.setDuree((Integer) object[3]);
        note.setIdChanson((Integer) object[4]);
        return note;
    }

    /**
     * Renvoie toute les notes contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    public List<Note> getAll() throws Exception{
        String query = String.format("SELECT * FROM %s;", NOTE_TABLE);
        List<Note> notes= new ArrayList<Note>();
        List<Object[]> listRecuperer = new ArrayList<Object[]>(Dao.getSeveralsElements(query, NB_COLUMNS_NOTE));
        Note note = null;
        if(listRecuperer !=null) {
            for (Object[] l : listRecuperer) {
                note =new Note();
                note.setIdNote((Integer) l[0]);
                note.setTemps((Integer) l[1]);
                note.setPosition((Integer) l[2]);
                note.setDuree((Integer) l[3]);
                note.setIdChanson((Integer) l[4]);
                notes.add(note);
            }
        }else{
            throw new Exception("PB DAO SONG");
        }
        return notes;

    }
}
