package com.mygdx.game.fichier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliette on 28/10/14.
 */
public class Chanson {

    private int idChanson;
    private String title;
    private TypeDifficultee difficulte;
    private int score;
    private List<Note> listNote;

    public Chanson(String title,TypeDifficultee difficulte,int score, List<Note> listNote) {
        this.title = title;
        this.difficulte = difficulte;
        this.score = 0;
        this.listNote = listNote;
    }

    public Chanson() {
        idChanson = -1;
        title="";
        difficulte=null;
        listNote=new ArrayList<Note>();
    }

    public int getIdChanson() {
        return idChanson;
    }

    public void setIdChanson(int idChanson) {
        this.idChanson = idChanson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TypeDifficultee getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(TypeDifficultee difficulte) {
        this.difficulte = difficulte;
    }

    public List<Note> getListNote() {
        return listNote;
    }

    public void setListNote(List<Note> listNote) {
        this.listNote = listNote;
    }

    /* temps: moment où le joueur appuie,
        position: note sur lequel le joueur appuie,
        duree: temps entre le push et le up du joueur sur la note
        difficultyTime: plage acceptable de temps ex: la note est a 42ms, difficultytime=3ms, temps=41ms => OKAY
     */
    public boolean validation(int temps, int position, int duree, int difficultyTime){
        Note n= null;
        for(int i = 0; i<listNote.size(); i++){
            n = listNote.get(i);
            if (n.getTemps()< temps+difficultyTime && n.getTemps()>temps-difficultyTime){
                if (n.getPosition() == position){
                    if (n.getDuree() < duree+difficultyTime && n.getDuree()>duree-difficultyTime){
                        // On retire de la liste la note et ses predecesseurs (note raté). La note est toujours une des premiere trouvée.
                        for (int j=0; j<i+1; j++){
                            listNote.remove(j);
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }
        }
        return false;
    }
}
