package com.mygdx.game.DataBase;

import com.mygdx.game.fichier.Chanson;
import java.util.List;

/**
 * Created by sebastien on 28/12/14.
 */
public interface SongDaoInterface{

    /**
     * Ajout d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    public void add(Chanson chanson);

    /**
     * Suppression d'une chanson de la base de donnee
     * @param id
     * @return
     */
    public void delete(int id);
    /**
     * Mise a jour d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    public void update(Chanson chanson);

    /**
     * Renvoi une chanson en fonction de son id
     * @param id
     * @return
     */
    public Chanson get(int id);

    /**
     * recupere une chanson par rapport a un titre
     * @param title
     * @return
     */
    public Chanson getByTitle(String title);

    /**
     * recupere une chanson par rapport a un titre et une difficulte
     * @param titre
     * @param difficulte
     * @return
     */
    public Chanson getByTitleAndDifficulty(String titre, String difficulte);

    /**
     * Renvoi l'id de la chanson voulue
     * @param chanson
     * @return
     */
    public int findId(Chanson chanson);
    /**
     * Renvoie toute les chansons contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    public List<Chanson> getAll() throws Exception;

    /**
     * recupere une liste de chanson par rapport a une difficulte
     * @param difficulte
     * @return
     * @throws Exception
     */
    public List<Chanson> getSongByDifficulty(String difficulte) throws Exception;

}
