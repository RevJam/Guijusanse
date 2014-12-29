package com.mygdx.game.DataBase;

import com.mygdx.game.fichier.Note;

import java.util.List;

/**
 * Created by sebastien on 28/12/14.
 */
public interface NoteDaoInterface {

    /**
     * Ajout d'une note dans la base de donnee
     * @param note
     * @return
     */
    public void add(Note note);

    /**
     * Suppression d'une note de la base de donnee
     * @param id
     * @return
     */
    public void delete(int id);

    /**
     * Mise a jour d'une note dans la base de donnee
     * @param note
     * @return
     */
    public void update(Note note);

    /**
     * Renvoi une note en fonction de son id
     * @param id
     * @return
     */
    public Note get(int id);

    /**
     * Renvoi l'id d'une note
     * @param note
     * @return
     */
    public int findId(Note note);

    /**
     * Renvoie toute les notes contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    public List<Note> getAll() throws Exception;
}
