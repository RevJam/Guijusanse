package com.mygdx.game.fichier;

/**
 * Created by juliette on 28/10/14.
 */
public class Note {
    private int temps;
    private int position;
    private int duree;

    public Note() {
        this.temps=-1;
        this.position=-1;
        this.duree = -1;
    }

    public Note(int temps, int duree, int position) {
        this.temps = temps;
        this.duree = duree;
        this.position = position;
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
}
