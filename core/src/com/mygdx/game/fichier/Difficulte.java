package com.mygdx.game.fichier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliette on 28/10/14
 */
public class Difficulte {


    private List<Chanson> listeChanson = null;
    private TypeDifficultee type=null;

    public Difficulte(){
        type=null;
        listeChanson=new ArrayList<Chanson>();
    }

    public Difficulte(List<Chanson> listeChanson, TypeDifficultee t) {
        this.listeChanson = listeChanson;
        this.type = t;
    }

    public List<Chanson> getListeChanson() {
        return listeChanson;
    }

    public void setListeChanson(List<Chanson> listeChanson) {
        this.listeChanson = listeChanson;
    }

    public TypeDifficultee getType() {
        return type;
    }

    public void setType(TypeDifficultee type) {
        this.type = type;
    }
}
