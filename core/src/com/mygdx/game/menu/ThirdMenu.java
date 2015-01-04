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
import com.mygdx.game.network.Message;
import com.mygdx.game.network.server.Server;


/**
 * Created by jamu on 28/10/14.
 */
public class ThirdMenu implements Screen {
    MyGdxGame game;
    Skin skin;
    Table table;
    Stage stage;
    TextButton buttonPlay, buttonReturn, buttonDifficulty, buttonSong;
    Label title;
    ScrollPane scroll;
    public ThirdMenu(MyGdxGame ggame) {
        this.game = ggame;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();

        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        // On crée deux boutons et un titre
        buttonPlay = new TextButton("Go!", skin);
        buttonReturn = new TextButton("Retour", skin, "buttonfour");
        buttonDifficulty = new TextButton("Difficulte", skin, "buttontwo");
        buttonSong = new TextButton("Choix Chanson", skin, "buttonthree");
        title = new Label("GuiJuSanSe",skin);

        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/500), 2.6f);
        skin.getFont("medium").setScale((game.getLargeur()/550), 1.2f);

        // On ajoute les elements a la trame

        table.add(title).padBottom((game.getLongueur()/5)).row();
        table.add(buttonDifficulty).size(game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
        table.add(buttonSong).size(game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
        table.add(buttonPlay).size(game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
        table.add(buttonReturn).size( game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
        scroll = new ScrollPane(table);

        //On active nos boutons
        buttonDifficulty.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getDm());
            }
        });
        buttonSong.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.getSongm().listChanson.size() == 0) {
                    game.getSongm().listeSong.clear();
                    game.getSongm().table.clear();
                    game.getSongm().table.add(game.getSongm().title).padBottom((game.getLongueur() / 5)).row();
                    TextButton button = new TextButton("Retour Precedent", skin);
                    game.getSongm().listeSong.add(button);
                    game.getSongm().table.add(button).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
                }
                game.setScreen(game.getSongm());
            }
        });

        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Si type de partie = multijoueur
                if (game.getTypeplayer() == 1){
                    Server server = (Server) game.getMessagesHandler();
                    server.sendMessageToAll(new Message("start", game.getDifficulty() + ";" + game.getSong()));
                    game.setScreen(game.getEcranJeu());
                }
                // Sinon, solo
                else {
                    if(!game.getDifficulty().equals("") && !game.getSong().equals("")){
                        game.setScreen(game.getEcranJeu());
                    }
                }
            }
        });

        buttonReturn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getSm());
            }
        });
    }

    public void render(float delta) {
        // On colore le fond
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On lance la scene et la met en visible
        stage.act();
        stage.draw();

    }

    public void resize(int width, int height) {

    }

    public void show() {


        table.setFillParent(true);
        //On ajoute les acteurs a la scène
        stage.addActor(table);

        // On place la scène
        Gdx.input.setInputProcessor(stage);

    }

    public void hide() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}