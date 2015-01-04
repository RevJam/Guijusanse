package com.mygdx.game.DataBase;

import com.mygdx.game.fichier.Score;

import java.util.List;

/**
 * Created by sebastien on 28/12/14.
 */
public interface ScoreDaoInterface {

    /**
     * Ajout d'un score dans la base de donnee
     * @param score
     * @return
     */
    public void add(Score score);

    /**
     * Suppression d'un score de la base de donnee
     * @param id
     * @return
     */
    public void delete(int id);

    /**
     * Mise a jour d'un score dans la base de donnee
     * @param score
     * @return
     */
    public void update(Score score);

    /**
     * Renvoi un score en fonction de son id
     * @param id
     * @return
     */
    public Score get(int id);

    /**
     * renvoi l'id d'un score contenu en base
     * @param score
     * @return
     */
    public int findId(Score score);

    /**
     * Renvoie tout les scores contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    public List<Score> getAll() throws Exception;

    /**
     * Renvoie tout les scores d'une chanson donnée
     * @param songTitle
     * @return
     * @throws Exception
     */
    public List<Score>getAllBySong(String songTitle) throws Exception;
}
