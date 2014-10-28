package com.mygdx.game.fichier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliette on 28/10/14.
 */
public class Chanson {
    private String title;
    private List<Note> listNote = new ArrayList<Note>();

    public Chanson(String title, int difficulty, List<Note> listNote) {
        this.title = title;
        this.listNote = listNote;
    }

    public Chanson() {
        title=null;
        listNote=null;
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
}
