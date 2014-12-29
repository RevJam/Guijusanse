package com.mygdx.game.fichier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliette on 28/10/14
 */
public class Chanson {
    /**
     * titre de la chanson
     */
    private String title;
    /**
     * liste de note
     */
    private List<Note> listNote;
    /**
     * difficultée
     */
    private TypeDifficultee difficulter;
    /**
     * l'id de la chanson en base
     */
    private int idChanson;

    public Chanson(String title,TypeDifficultee difficulte,List<Note> listNote) {
        this.title = title;
        this.difficulter = difficulte;
        this.listNote = listNote;
    }

    public Chanson() {
        idChanson = -1;
        title = "";
        listNote = new ArrayList<Note>();
        difficulter = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Note> getListNote() {
        return listNote;
    }

    public void setListNote(List<Note> listNote) {
        this.listNote = listNote;
    }

    public TypeDifficultee getDifficulter() {
        return difficulter;
    }

    public void setDifficulter(TypeDifficultee difficulter) {
        this.difficulter = difficulter;
    }

    public int getIdChanson() {
        return idChanson;
    }

    public void setIdChanson(int idChanson) {
        this.idChanson = idChanson;
    }

    @Override
    public String toString() {
        return "Chanson{" +
                "title='" + title + '\'' +
                ", listNote=" + listNote +
                ", difficulter=" + difficulter +
                ", idChanson=" + idChanson +
                '}';
    }

    public boolean isEmpty(){
        return title.isEmpty() && listNote.isEmpty() && difficulter == null && idChanson == -1;
    }
}
