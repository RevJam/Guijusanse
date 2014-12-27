package com.mygdx.game.fichier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juliette on 28/10/14
 */
public class Chanson {
    private String title;
    private List<Note> listNote = new ArrayList<Note>();
    private TypeDifficultee difficulter;
    private int idChanson=-1;
    private int Score=0;

    public Chanson(String title,List<Note> listNote) {
        this.title = title;
        this.listNote = listNote;
    }

    public Chanson() {
        title=null;
        listNote=null;
        difficulter=null;
    }

    public Chanson(String titre, TypeDifficultee difficulteLue,List<Note> listNote2) {
        this.title=titre;
        this.listNote = listNote2;
        this.difficulter=difficulteLue;
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

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
