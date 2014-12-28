package com.mygdx.game.android.DataBase;

import android.content.ContentValues;
import android.content.Context;
import com.mygdx.game.DataBase.ScoreDaoInterface;
import com.mygdx.game.fichier.Score;

import java.util.List;

/**
 * Created by sebastien on 28/12/14.
 */
public class ScoreDao extends Dao implements ScoreDaoInterface{

    public ScoreDao(Context pContext) {
        super(pContext);
    }

    /**
     * Ajout d'un score dans la base de donnee
     *
     * @param score
     * @return
     */
    @Override
    public void add(Score score) {
        open();
        ContentValues value = new ContentValues();
        value.put(DataBaseMaker.SCORE_PLAYER, score.getPlayerName());
        value.put(DataBaseMaker.SCORE_SONGTITLE, score.getSongTitle());
        value.put(DataBaseMaker.SCORE_DIFFICULTY, String.valueOf(score.getDifficultee()));
        value.put(DataBaseMaker.SCORE_SCORE, score.getScore());
        mDb.insert(DataBaseMaker.SCORE_TABLE, null, value);
    }

    /**
     * Suppression d'un score de la base de donnee
     *
     * @param id
     * @return
     */
    @Override
    public void delete(int id) {

    }

    /**
     * Mise a jour d'un score dans la base de donnee
     *
     * @param score
     * @return
     */
    @Override
    public void update(Score score) {

    }

    /**
     * Renvoi un score en fonction de son id
     *
     * @param id
     * @return
     */
    @Override
    public Score get(int id) {
        return null;
    }

    /**
     * Renvoie tout les scores contenu dans la base de donnee
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Score> getAll() throws Exception {
        return null;
    }
}
