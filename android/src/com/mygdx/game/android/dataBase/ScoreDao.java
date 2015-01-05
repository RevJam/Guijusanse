package com.mygdx.game.android.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.mygdx.game.dataBase.ScoreDaoInterface;
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
        mDb.update(DataBaseMaker.SCORE_TABLE, value," id=?", new String[] {String.valueOf(score.getIdScore())});
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
        while(c.moveToNext()){
            score.setIdScore(c.getInt(c.getColumnIndex("id")));
            score.setPlayerName(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_PLAYER)));
            score.setSongTitle(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_SONGTITLE)));
            score.setDifficultee(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_DIFFICULTY))));
            score.setScore(c.getInt(c.getColumnIndex(DataBaseMaker.SCORE_SCORE)));
        }
        c.close();
        return score;
    }

    /**
     * renvoi l'id d'un score contenu en base
     *
     * @param score
     * @return
     */
    @Override
    public int findId(Score score) {
        open();
        int id = -1;
        String[] params = new String[]{score.getPlayerName(),score.getSongTitle(),String.valueOf(score.getDifficultee()),String.valueOf(score.getScore())};
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.SCORE_TABLE
                + " where "+ DataBaseMaker.SCORE_PLAYER + "=? and "
                + DataBaseMaker.SCORE_SONGTITLE + "=? and "
                + DataBaseMaker.SCORE_DIFFICULTY + "=? and "
                + DataBaseMaker.SCORE_SCORE + "=?", params);
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex("id"));
        }
        c.close();
        return id;
    }

    /**
     * Renvoie tout les scores contenu dans la base de donnee
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Score> getAll() throws Exception {
        open();
        List<Score> liste = new ArrayList<Score>();
        Score score = new Score();
        Cursor c = mDb.query(DataBaseMaker.SCORE_TABLE, new String[]{"id",
                DataBaseMaker.SCORE_PLAYER,DataBaseMaker.SCORE_SONGTITLE,
                DataBaseMaker.SCORE_DIFFICULTY,DataBaseMaker.SCORE_SCORE},"*", null, null, null, null);
        while (c.moveToNext()) {
            score.setIdScore(c.getInt(c.getColumnIndex("id")));
            score.setPlayerName(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_PLAYER)));
            score.setSongTitle(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_SONGTITLE)));
            score.setDifficultee(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_DIFFICULTY))));
            score.setScore(c.getInt(c.getColumnIndex(DataBaseMaker.SCORE_SCORE)));
            liste.add(score);
        }
        c.close();
        return liste;
    }

    /**
     * Renvoie tout les scores d'une chanson donnée
     *
     * @param songTitle
     * @return
     * @throws Exception
     */
    @Override
    public List<Score> getAllBySong(String songTitle) throws Exception {
        open();
        List<Score> liste = new ArrayList<Score>();
        Score score;
        String[] params = new String[]{songTitle};
        Cursor c = mDb.rawQuery("select * from "+ DataBaseMaker.SCORE_TABLE
                + " where " + DataBaseMaker.SCORE_SONGTITLE + "=? order by " + DataBaseMaker.SCORE_SCORE + " desc", params);
        while (c.moveToNext()) {
            score = new Score();
            score.setIdScore(c.getInt(c.getColumnIndex("id")));
            score.setPlayerName(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_PLAYER)));
            score.setSongTitle(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_SONGTITLE)));
            score.setDifficultee(TypeDifficultee.valueOf(c.getString(c.getColumnIndex(DataBaseMaker.SCORE_DIFFICULTY))));
            score.setScore(c.getInt(c.getColumnIndex(DataBaseMaker.SCORE_SCORE)));
            liste.add(score);
        }
        c.close();
        return liste;
    }


}
