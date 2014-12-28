package com.mygdx.game.android.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.mygdx.game.DataBase.ScoreDaoInterface;
import com.mygdx.game.fichier.Score;
import com.mygdx.game.fichier.TypeDifficultee;

import java.util.ArrayList;
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
        open();
        mDb.delete(DataBaseMaker.SCORE_TABLE," id = ?", new String[] {String.valueOf(id)});
    }

    /**
     * Mise a jour d'un score dans la base de donnee
     *
     * @param score
     * @return
     */
    @Override
    public void update(Score score) {
        open();
        ContentValues value = new ContentValues();
        value.put(DataBaseMaker.SCORE_PLAYER, score.getPlayerName());
        value.put(DataBaseMaker.SCORE_SONGTITLE, score.getSongTitle());
        value.put(DataBaseMaker.SCORE_DIFFICULTY, String.valueOf(score.getDifficultee()));
        value.put(DataBaseMaker.SCORE_SCORE, score.getScore());
        mDb.update(DataBaseMaker.SONG_TABLE, value," id = ?", new String[] {String.valueOf(score.getIdScore())});
    }

    /**
     * Renvoi un score en fonction de son id
     *
     * @param id
     * @return
     */
    @Override
    public Score get(int id) {
        open();
        Cursor c = mDb.rawQuery("select id, " + DataBaseMaker.SCORE_PLAYER + ", "
                + DataBaseMaker.SCORE_SONGTITLE + ", " + DataBaseMaker.SCORE_DIFFICULTY
                + ", " + DataBaseMaker.SCORE_SCORE
                + " where id = ?", new String[]{String.valueOf(id)});
        Score score = new Score();
        if(!c.isNull(0) && !c.isNull(1) && !c.isNull(2) && !c.isNull(3) && !c.isNull(4)) {
            score.setIdScore(c.getInt(0));
            score.setPlayerName(c.getString(1));
            score.setSongTitle(c.getString(2));
            score.setDifficultee(TypeDifficultee.valueOf(c.getString(3)));
            score.setScore(c.getInt(4));
        }
        return score;
    }

    /**
     * Renvoie tout les scores contenu dans la base de donnee
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Score> getAll() throws Exception {
        List<Score> liste = new ArrayList<Score>();
        Score score = new Score();
        Cursor c = mDb.query(DataBaseMaker.SCORE_TABLE, new String[]{"id",
                DataBaseMaker.SCORE_PLAYER,DataBaseMaker.SCORE_SONGTITLE,
                DataBaseMaker.SCORE_DIFFICULTY,DataBaseMaker.SCORE_SCORE},"*", null, null, null, null);
        while (c.moveToNext()) {
            score.setIdScore(c.getInt(0));
            score.setPlayerName(c.getString(1));
            score.setSongTitle(c.getString(2));
            score.setDifficultee(TypeDifficultee.valueOf(c.getString(3)));
            score.setScore(c.getInt(4));
            liste.add(score);
        }
        c.close();
        return liste;
    }
}
