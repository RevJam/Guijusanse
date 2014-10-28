package com.mygdx.game.fichier;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by juliette on 28/10/14.
 */
public class Lecture {
    private Note note;
    private Chanson chansonqCourante;
    private Difficulte difficulteLue;

    private String texte = null;

    public Lecture(Note note, Chanson chansonqCourante, Difficulte difficulte) {
        this.note = note;
        this.chansonqCourante = chansonqCourante;
        this.difficulteLue = difficulte;
    }

    public Lecture() {

        this.chansonqCourante = new Chanson();
        this.note = new Note();
        this.difficulteLue = new Difficulte();
    }

    public void lecture(String input) throws IOException {

        String str;
        int i=0;

        BufferedReader reader = Gdx.files.internal(input).reader(2048);
        String [] tab =new String[10000];
        while ((str = reader.readLine()+1)!=null){
            tab[i]=str;
            i++;

        }
        Gdx.app.log("fr", "LICORNE !!!!");
    }
}
