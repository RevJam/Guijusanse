package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import com.mygdx.game.dataBase.DaosAccess;

import com.mygdx.game.game.TimeInterface;
import com.mygdx.game.fichier.Score;
import com.mygdx.game.menu.*;
import com.mygdx.game.network.MessagesHandler;


public class MyGdxGame extends Game {

    // Différents menus
    private FirstMenu fm;
    private SecondMenu sm;
    private MultiMenu mm;
    private MultiJoinMenu mjm;
    private MultiServerMenu msm;
    private ThirdMenu tmA;
    private DifficultyMenu dm;
    private SongMenu songm;
    private ScoreMenu scoreMenu;
    private HighScoreMenu hsmenu;

    // Taille de l'écran
    private int largeur;
    private int longueur;

    // Variable pour le Jeu
    private Jeu ecranJeu;
    private String difficulty;
    private String song;

    // Attributs pour le réseau
    private MessagesHandler mMessagesHandler;

    // 0 = OnePlayer, 1 = Multi (Serveur), 2 = Multi (Client)
    private int typeplayer;

    private Music music;
    private Score score;
    private final DaosAccess daosAccess;
    private TimeInterface timeInterface;

    public MyGdxGame(DaosAccess daosAccess, TimeInterface t) {
        super();
        this.daosAccess = daosAccess;
        timeInterface = t;
    }


    @Override
    public void create() {
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
        mjm = new MultiJoinMenu(this);
        msm = new MultiServerMenu(this);
        ecranJeu = new Jeu(this);
        scoreMenu = new ScoreMenu(this);
        hsmenu = new HighScoreMenu(this);

        // Music
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/songMenu.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        //Lancer le premier Menu
        setScreen(fm);
    }

    public void resetAll(){
        difficulty = "";
        song = "";
        typeplayer = -1;
        timeInterface.resetTime();
        tmA = new ThirdMenu(this);
        songm = new SongMenu(this);
        ecranJeu = new Jeu(this);
        scoreMenu=new ScoreMenu(this);
    }

    public void setMessagesHandler(MessagesHandler messagesHandler) {
        this.mMessagesHandler = messagesHandler;
    }
    public MessagesHandler getMessagesHandler() {
        return this.mMessagesHandler;
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

    public HighScoreMenu getHsmenu() {
        return hsmenu;
    }

    public void setHsmenu(HighScoreMenu hsmenu) {
        this.hsmenu = hsmenu;
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

    public MultiJoinMenu getMjm() {
        return mjm;
    }

    public void setMjm(MultiJoinMenu mjm) {
        this.mjm = mjm;
    }

    public MultiServerMenu getMsm() {
        return msm;
    }

    public void setMsm(MultiServerMenu msm) {
        this.msm = msm;
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

    public TimeInterface getTimeInterface() {
        return timeInterface;
    }

    public ScoreMenu getScoreMenu() {
        return scoreMenu;
    }

    public void setScoreMenu(ScoreMenu scoreMenu) {
        this.scoreMenu = scoreMenu;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
