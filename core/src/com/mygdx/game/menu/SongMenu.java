package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

/**
 * Created by jamu on 28/10/14.
 */
public class SongMenu implements Screen {
    MyGdxGame game;
    Skin skin;
    Table table;
    Stage stage;
    ArrayList<TextButton> song;
    Label title;
    ArrayList<String> list;

    public SongMenu(MyGdxGame game) {
        this.game = game;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();


        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/300), 5f);
        skin.getFont("medium").setScale((game.getLargeur()/400), 2.5f);

        // On ajoute le titre a la trame
        title = new Label("GuiJuSanSe",skin);
        table.add(title).padBottom((game.getLongueur()/5)).row();

        // On crée une liste pour les chansons OK
        list = new ArrayList<String>();

        // Crée une liste de boutons
        song = new ArrayList<TextButton>();

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

        //On active nos boutons
        // Liste des chansons selon la difficulté.
        for (final TextButton button : song) {
            button.addListener(new ClickListener() {
                TextButton t=button;
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (t.getText().toString() == "Retour Precedent"){
                        game.setScreen(game.getTmA());
                    }else{
                        // On enregistre la chanson selectionnée
                        game.setSong(t.getText().toString());
                        //On l'affiche sur le menu
                        game.getTmA().buttonSong.setText(t.getText().toString());
                        //On retourne a l'ecran precedent
                        game.setScreen(game.getTmA());
                    }

                }
            });
        }


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
