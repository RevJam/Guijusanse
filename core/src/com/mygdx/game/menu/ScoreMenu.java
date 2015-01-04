package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.fichier.Score;

/**
 * Created by juliette on 03/01/15.
 */
public class ScoreMenu implements Screen {
    MyGdxGame game;
    Skin skin;
    Table table;
    Stage stage;
    TextButton buttonReturn;
    Label title, affScore;
    Score score;
    TextField nomJoueur;

    public ScoreMenu(MyGdxGame game) {
        this.game = game;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();

        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        // On crée un bouton, un titre et afficher le score
        score=new Score();


        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/500), 2.6f);
        skin.getFont("medium").setScale((game.getLargeur()/550), 1.2f);
        buttonReturn = new TextButton("Retour", skin, "buttonthree");
        title = new Label("GuiJuSanSe",skin);

        // On ajoute les elements a la trame
        table.add(title).padBottom((game.getLongueur()/5)).row();
        //table.add(nomJoueur).padBottom((game.getLongueur()/5)).row();

    }
    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // On colore le fond
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On lance la scene et la met en visible
        stage.act();
        stage.draw();
    }

    /**
     * @param width
     * @param height
     * @see com.badlogic.gdx.ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void show() {
        buttonReturn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Score score = game.getScore();
                score.setIdScore(game.getDaosAccess().getScoreDao().findId(score));
                if(!nomJoueur.getText().equals("")){
                    score.setPlayerName(nomJoueur.getText());
                    game.getDaosAccess().getScoreDao().update(score);
                }
                game.setScore(new Score());
                game.resetAll();
                game.setScreen(game.getFm());
            }
        });


        score=game.getScore();
        affScore = new Label("Votre Score est de : "+score.getScore(),skin, "score");



        nomJoueur=new TextField("",skin);
        table.add(nomJoueur).padBottom((game.getLongueur()/7)).size(game.getLargeur()/2, (game.getLongueur()/15)).row();
        table.add(affScore).padBottom((game.getLongueur()/10)).row();
        //
        table.add(buttonReturn).size(game.getLargeur(), (game.getLongueur()/20)).padBottom(20).row();

        game.setMusic(Gdx.audio.newMusic(Gdx.files.internal("sound/songMenu.mp3")));
        game.getMusic().setLooping(true);
        game.getMusic().setVolume(0.5f);
        game.getMusic().play();

        table.setFillParent(true);
        //On ajoute les acteurs a la scène
        stage.addActor(table);
        // On place la scène
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}