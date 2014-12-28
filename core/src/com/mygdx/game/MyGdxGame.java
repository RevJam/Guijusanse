package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.DataBase.DaosAccess;
import com.mygdx.game.fichier.Lecture;
import com.mygdx.game.menu.DifficultyMenu;
import com.mygdx.game.menu.FirstMenu;
import com.mygdx.game.menu.MultiMenu;
import com.mygdx.game.menu.SecondMenu;
import com.mygdx.game.menu.SongMenu;
import com.mygdx.game.menu.ThirdMenu;

import java.io.IOException;

public class MyGdxGame extends Game {

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
    private Jeu ecranJeu;
    private String difficulty;
    private String song;
    // 0 = OnePlayer, 1 = Multi
    private int typeplayer;

    private Music music;

    private final DaosAccess daosAccess;

    public MyGdxGame(DaosAccess daosAccess) {
        super();
        this.daosAccess = daosAccess;
    }


    @Override
    public void create () {
        // Initialisation variable Jeu
        difficulty = "";
        song = "";
        typeplayer = -1;
        largeur = Gdx.graphics.getWidth();
        longueur = Gdx.graphics.getHeight();

        // Initialisation Screen Menus
        fm = new FirstMenu(this);
        sm = new SecondMenu(this);
        tmA = new ThirdMenu(this);
        dm = new DifficultyMenu(this);
        songm = new SongMenu(this);
        mm = new MultiMenu(this);
        ecranJeu=new Jeu(this);
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

    public Jeu getEcranJeu() {
        return ecranJeu;
    }

    public void setEcranJeu(Jeu ecranJeu) {
        ecranJeu = ecranJeu;
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

    public DaosAccess getDaosAccess() {
        return daosAccess;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}