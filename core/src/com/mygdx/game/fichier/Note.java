package com.mygdx.game.fichier;

/**
 * Created by juliette on 28/10/14
 */
public class Note {
    private int temps;
    private int position;
    private int duree;
    private int idChanson;
    private int idNote;

    public Note() {
        this.idNote=-1;
        this.temps=-1;
        this.position=-1;
        this.duree = -1;
        this.idChanson=-1;
    }

    public Note(int temps, int duree, int position, int idChanson) {
        this.temps = temps;
        this.duree = duree;
        this.position = position;
        this.idChanson = idChanson;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getIdChanson() {
        return idChanson;
    }

    public void setIdChanson(int idChanson) {
        this.idChanson = idChanson;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public boolean isEmpty(){
        return temps == -1 && duree == -1 && position == -1 && idChanson == -1;
    }
}
