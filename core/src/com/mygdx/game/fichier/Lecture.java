package com.mygdx.game.fichier;

import com.mygdx.game.DataBase.DaosAccess;
import com.mygdx.game.DataBase.NoteDaoInterface;
import com.mygdx.game.DataBase.SongDaoInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by juliette on 28/10/14
 */
public class Lecture {

    private List<Note> listNote;
    private List<Chanson> listeChanson;
    private TypeDifficultee difficulteLue;
    int duree,position,temps;
    private String titre;
    private DaosAccess dao=null;

    public Lecture(DaosAccess daosAccess) {
        this.difficulteLue = null;
        this.listNote = new ArrayList<Note>();
        this.listeChanson = new ArrayList<Chanson>();
        dao=daosAccess;
    }

    public void lecture(InputStream input) throws IOException {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String str;
            while ((str = br.readLine()) != null ){
                listNote = new ArrayList<Note>();
                int difficulty = Integer.parseInt(str);
                titre =br.readLine();
                System.out.println(titre);
                int nombreNoteALire = Integer.parseInt(br.readLine());
                for(int i = 0; i< nombreNoteALire; i++ ){
                    String [] notes = br.readLine().split("\\| ");
                    try{
                        // any positive or negative integer or not!
                        if(notes[0].matches("-?\\d+ ")) {
                            temps = Integer.parseInt(notes[0].substring(0,notes[0].length()-1));
                        }
                        if(notes[1].matches("-?\\d+ ")) {
                            position = Integer.parseInt(notes[1].substring(0,notes[1].length()-1));
                        }

                        if(notes[2].matches("-?\\d+")){
                            duree = Integer.parseInt(notes[2].substring(0,notes[2].length()-1));
                        }
                    }catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    listNote.add(new Note(temps, duree, position));
                }
                switch (difficulty) {
                    case 0:
                        difficulteLue= TypeDifficultee.FACILE;
                        break;

                    case 1:
                        difficulteLue=TypeDifficultee.NORMAL;
                        break;
                    default:
                        break;
                }
                listeChanson.add(new Chanson(titre,difficulteLue,listNote));
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SongDaoInterface songDaoInterface = null;
        NoteDaoInterface noteDaoInterface = null;

        for(Chanson c :listeChanson) {
            dao.getSongDao().add(c);
            c.setIdChanson(dao.getSongDao().findId(c));
            for(Note n : c.getListNote()){
                n.setIdChanson(c.getIdChanson());
                dao.getNoteDao().add(n);
            }
        }
    }
}
