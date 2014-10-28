package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.fichier.Lecture;
import com.mygdx.game.menu.DifficultyMenu;
import com.mygdx.game.menu.FirstMenu;
import com.mygdx.game.menu.MultiMenu;
import com.mygdx.game.menu.SecondMenu;
import com.mygdx.game.menu.SongMenu;
import com.mygdx.game.menu.ThirdMenu;

import java.io.IOException;

public class MyGdxGame extends Game {
    // Faux jeu pour le moment
	SpriteBatch batch;
	Texture img;
    Texture background;

    Integer hpos = 0;
    Integer hspeed = 120;
    Integer vpos = 0;
    Integer vspeed = 200;

    // Différents menus
    FirstMenu fm;
    SecondMenu sm;
    MultiMenu mm;
    ThirdMenu tmA;
    DifficultyMenu dm;
    SongMenu songm;

    // Variable pour le Jeu
    GoGame gogame;
    String difficulty;
    String song;
    // 0 = OnePlayer, 1 = Multi
    int typeplayer;

    public MyGdxGame() {
        super();

    }


    @Override
	public void create () {
        // Faux Jeu
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        // Initialisation variable Jeu
        difficulty = "";
        song = "";
        typeplayer = -1;

        // Initialisation Screen Jeu
        gogame = new GoGame(this);

        // Initialisation Screen Menus
        fm = new FirstMenu(this);
        sm = new SecondMenu(this);
        tmA = new ThirdMenu(this);
        dm = new DifficultyMenu(this);
        songm = new SongMenu(this);
        mm = new MultiMenu(this);

        //background = new Texture("skin/sky.jpg");

        //Lancer le premier Menu
        setScreen(fm);


        Lecture lectur = new Lecture();
       /* try {
            lectur.lecture("fichiers/"+"test.srt");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
	}

    public FirstMenu getFm() {
        return fm;
    }

    public void setFm(FirstMenu fm) {
        this.fm = fm;
    }

    public SecondMenu getSm() {
        return sm;
    }

    public void setSm(SecondMenu sm) {
        this.sm = sm;
    }

    public ThirdMenu getTmA() {
        return tmA;
    }

    public void setTmA(ThirdMenu tmA) {
        this.tmA = tmA;
    }

    public DifficultyMenu getDm() {
        return dm;
    }

    public void setDm(DifficultyMenu dm) {
        this.dm = dm;
    }

    public GoGame getGogame() {
        return gogame;
    }

    public void setGogame(GoGame gogame) {
        this.gogame = gogame;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public SongMenu getSongm() {
        return songm;
    }

    public void setSongm(SongMenu songm) {
        this.songm = songm;
    }

    public int getTypeplayer() {
        return typeplayer;
    }

    public void setTypeplayer(int typeplayer) {
        this.typeplayer = typeplayer;
    }

    public MultiMenu getMm() {
        return mm;
    }

    public void setMm(MultiMenu mm) {
        this.mm = mm;
    }
}
