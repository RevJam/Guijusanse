package com.mygdx.game.dataBase;

import com.mygdx.game.fichier.Chanson;
import com.mygdx.game.fichier.TypeDifficultee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastien on 26/12/14.
 */
public class DaoSong extends DataBaseMaker{


    public void createTable() {
        Dao.createDrop(CREATE_SONG_TABLE);
    }

    /**
     * Ajout d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    public int add(Chanson chanson) {
        String query = String.format(
                "INSERT INTO %s (%s,%s,%s) VALUES('%s','%s','%s');",
                SONG_TABLE, SONG_NAME, SONG_DIFFICULTY, SONG_SCORE, chanson.getTitle(), chanson.getDifficulte().toString(), chanson.getScore());
        return Dao.add(query);
    }

    /**
     * Suppression d'une chanson de la base de donnee
     * @param id
     * @return
     */
    public int delete(int id) {
        String query = String.format("DELETE FROM %s WHERE id='%d';",
                SONG_TABLE, id);
        return Dao.deleteUpdate(query);
    }

    /**
     * Mise a jour d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    public int update(Chanson chanson) {
        String query = String.format("UPDATE %s SET %s='%s',%s='%s',%s='%s' WHERE id=%d;",
                SONG_TABLE,
                SONG_NAME, chanson.getTitle(),
                SONG_DIFFICULTY, chanson.getDifficulte().toString(),
                SONG_SCORE, chanson.getScore(),
                chanson.getIdChanson());
        return Dao.deleteUpdate(query);
    }

    /**
     * Supprime tout les users de la base de donnee
     * @return
     */
    public int deleteAll() {
        String query = String.format("DELETE FROM %s;", SONG_TABLE);
        return Dao.deleteUpdate(query);
    }

    /**
     * Cherche l'id d'une chanson dans la base de donnee
     * @param chanson
     * @return
     */
    public int findId(Chanson chanson){
        String query =String.format("SELECT * FROM %s WHERE %s='%s' AND %s='%s' AND %s='%s';",
                SONG_TABLE, SONG_NAME,chanson.getTitle(), SONG_DIFFICULTY, chanson.getDifficulte(), SONG_SCORE, chanson.getScore());
        Object[] object = Dao.getUniqueElement(query,NB_COLUMNS_SONG);
        if(object != null){
            return (Integer)object[0];
        }
        return -1;
    }

    /**
     * Renvoi une chanson en fonction de son id
     * @param id
     * @return
     */
    public Chanson get(int id) {
        Chanson chanson = new Chanson();
        String query = String.format("SELECT * FROM %s WHERE id='%d';", SONG_TABLE, id);
        Object[] object = Dao.getUniqueElement(query, NB_COLUMNS_SONG);
        chanson.setIdChanson((Integer) object[0]);
        chanson.setTitle((String) object[1]);
        chanson.setDifficulte(TypeDifficultee.valueOf((String) object[2]));
        chanson.setScore((Integer) object[3]);
        return chanson;
    }

    /**
     * Renvoie tout les QCM contenu dans la base de donnee
     * @return
     * @throws Exception
     */
    public List<Chanson> getAll() throws Exception{
        String query = String.format("SELECT * FROM %s;", SONG_TABLE);
        List<Chanson> chansons= new ArrayList<Chanson>();
        List<Object[]> listRecuperer = new ArrayList<Object[]>(Dao.getSeveralsElements(query, NB_COLUMNS_SONG));
        Chanson chanson = null;
        if(listRecuperer !=null) {
            for (Object[] l : listRecuperer) {
                chanson =new Chanson();
                chanson.setIdChanson((Integer) l[0]);
                chanson.setTitle((String) l[1]);
                chanson.setDifficulte(TypeDifficultee.valueOf((String) l[2]));
                chanson.setScore((Integer) l[3]);
                chansons.add(chanson);

            }
        }else{
            throw new Exception("PB DAO SONG");
        }
        return chansons;

    }

}
