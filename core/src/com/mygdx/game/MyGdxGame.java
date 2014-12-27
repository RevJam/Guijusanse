package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

    // Différents menus
    private FirstMenu fm;
    private SecondMenu sm;
    private MultiMenu mm;
    private ThirdMenu tmA;
    private DifficultyMenu dm;
    private SongMenu songm;

    // Taille de l'écran
    private int largeur;
    private int longueur;

    // Variable pour le Jeu
    private GoGame gogame;
    private String difficulty;
    private  String song;
    // 0 = OnePlayer, 1 = Multi
    private int typeplayer;
    private int score = 0;

    private Music music;

    public MyGdxGame() {
        super();

    }


    @Override
    public void create () {


        // Initialisation variable Jeu
        difficulty = "";
        song = "";
        typeplayer = -1;
        largeur = Gdx.graphics.getWidth();
        longueur = Gdx.graphics.getHeight();
        Lecture lectur = new Lecture();
        try {
            lectur.lecture("ListeChanson.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Initialisation Screen Jeu
        Texture chien =new Texture(Gdx.files.internal("skin/buttonA.png"));
        Texture chat = new Texture(Gdx.files.internal("skin/buttonA.png"));
        Texture panda = new Texture(Gdx.files.internal("skin/buttonA.png"));
                    gogame = new GoGame(this, chat);


        // Initialisation Screen Menus
        fm = new FirstMenu(this);
        sm = new SecondMenu(this);
        tmA = new ThirdMenu(this);
        dm = new DifficultyMenu(this);
        songm = new SongMenu(this);
        mm = new MultiMenu(this);
        // Music
        music=Gdx.audio.newMusic(Gdx.files.internal("sound/songMenu.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        //Lancer le premier Menu
        setScreen(fm);



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

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}