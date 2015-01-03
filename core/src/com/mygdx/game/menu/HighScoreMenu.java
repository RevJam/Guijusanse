package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.fichier.Chanson;
import com.mygdx.game.fichier.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamu on 03/01/15.
 */
public class HighScoreMenu implements Screen {
    MyGdxGame game;
    Stage stage;
    Table table;
    Skin skin;
    TextButton buttonReturn, buttonReturn2;
    Label title;
    ScrollPane scroll;
    List<Score> listScore;
    List<Chanson> listChanson;
    List<TextButton> listButton;

    public HighScoreMenu(MyGdxGame ggame){
        game = ggame;
        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();

        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        // On crée deux boutons et un titre
        buttonReturn = new TextButton("Retour", skin, "buttonfour");
        buttonReturn2 = new TextButton("Retour", skin, "buttontwo");
        title = new Label("GuiJuSanSe",skin);

        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/500), 2.6f);
        skin.getFont("medium").setScale((game.getLargeur()/550), 1.2f);

        // On ajoute les elements a la trame

        table.add(title).padBottom((game.getLongueur()/5)).row();

        listScore = new ArrayList<Score>();
        try {
            listScore=game.getDaosAccess().getScoreDao().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // On crée une liste pour les chansons OK
        listChanson = new ArrayList<Chanson>();
        try {
            listChanson=game.getDaosAccess().getSongDao().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Crée une liste de boutons
        listButton= new ArrayList<TextButton>();
        TextButton but;
        for (Chanson chanson : listChanson) {
            but = new TextButton(chanson.getTitle(), skin);
            listButton.add(but);
            table.add(but).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
        }
        table.add(buttonReturn).size( game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
        scroll = new ScrollPane(table);
    }

    @Override
    public void render(float delta) {
        // On colore le fond
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On lance la scene et la met en visible
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        for(TextButton button: listButton){
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.getHsmenu().table.clear();
                    game.getHsmenu().table.add(title).padBottom((game.getLongueur()/5)).row();
                    for(Score score: listScore){
                        Label label = new Label(score.getPlayerName()+": "+String.valueOf(score.getScore()),skin,"score");
                        game.getHsmenu().table.add(label).size( game.getLargeur(), (game.getLongueur()/10)).padBottom(5).row();
                    }

                    game.getHsmenu().table.add(buttonReturn2).size( game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
                    scroll = new ScrollPane(game.getHsmenu().table);
                    game.setScreen(game.getHsmenu());
                }
            });
        }

        buttonReturn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getFm());
            }
        });

        buttonReturn2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getHsmenu().table.clear();
                game.getHsmenu().table.add(title).padBottom((game.getLongueur()/5)).row();
                for (TextButton chanson : listButton) {
                    game.getHsmenu().table.add(chanson).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
                }
                game.getHsmenu().table.add(buttonReturn).size( game.getLargeur(), (game.getLongueur()/10)).padBottom(20).row();
                scroll = new ScrollPane( game.getHsmenu().table);
                game.setScreen(game.getHsmenu());
            }
        });

        table.setFillParent(true);
        //On ajoute les acteurs a la scène
        stage.addActor(table);

        // On place la scène
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
