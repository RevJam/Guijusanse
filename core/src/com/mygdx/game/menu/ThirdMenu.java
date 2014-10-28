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

    public ThirdMenu(MyGdxGame game) {
        this.game = game;

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

        skin.getFont("title").setScale(3.5f, 5f);
        skin.getFont("medium").setScale(2.5f, 2.5f);
        // On ajoute les elements a la trame

        table.add(title).padBottom(500).row();
        table.add(buttonDifficulty).size(Gdx.graphics.getWidth(), 150).padBottom(20).row();
        table.add(buttonSong).size(Gdx.graphics.getWidth(), 150).padBottom(20).row();
        table.add(buttonPlay).size(Gdx.graphics.getWidth(), 150).padBottom(20).row();
        table.add(buttonReturn).size( Gdx.graphics.getWidth(),150).padBottom(20).row();

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
        buttonDifficulty.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getDm());
            }
        });
        buttonSong.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.getSongm().list.size() == 0){
                    TextButton button = new TextButton("Retour Precedent", skin);
                    game.getSongm().song.add(button);
                    game.getSongm().table.add(button).size(Gdx.graphics.getWidth(), 150).padBottom(20).row();
                }
                game.setScreen(game.getSongm());
            }
        });
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getTypeplayer() == 1){
                    // game.setScreen(game.Waitgame());
                }else {
                    game.setScreen(game.getGogame());
                }
            }
        });
        buttonReturn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getSm());
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