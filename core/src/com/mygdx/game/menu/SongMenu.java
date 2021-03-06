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
import com.mygdx.game.fichier.Chanson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamu on 28/10/14.
 */
public class SongMenu implements Screen {
    MyGdxGame game;
    Skin skin;
    Table table;
    Stage stage;

    List<TextButton> listeSong;
    Label title;
    List<Chanson> listChanson;

    TextButton buttonReturn;

    public SongMenu(MyGdxGame game) {

        this.game = game;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();


        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));
        buttonReturn = new TextButton("Retour", skin, "buttonfour");

        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/500), 2.6f);
        skin.getFont("medium").setScale((game.getLargeur()/550), 1.2f);

        // On ajoute le titre a la trame
        title = new Label("GuiJuSanSe",skin);
        table.add(title).padBottom((game.getLongueur()/5)).row();

        // On crée une liste pour les chansons OK
        listChanson = new ArrayList<Chanson>();
        try {
            listChanson=game.getDaosAccess().getSongDao().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Crée une liste de boutons
        listeSong= new ArrayList<TextButton>();

        TextButton but;
        if(!game.getDifficulty().equals("")) {
            for (Chanson chanson : listChanson) {
                but = new TextButton(chanson.getTitle(), skin);
                listeSong.add(but);
                table.add(but).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
            }
        }else{
            table.add(buttonReturn).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
        }
    }

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

    public void show() {
        //On active nos boutons
        // Liste des chansons selon la difficulté.
        for(final TextButton but: listeSong) {
            but.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setSong("");
                    game.setSong(but.getText().toString());

                    if (!game.getDifficulty().equals("")) {
                        game.getTmA().buttonSong.setText(but.getText().toString());
                    }
                    game.setScreen(game.getTmA());
                }
            });
        }

        buttonReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setSong("");
                game.setScreen(game.getTmA());
            }
        });

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
