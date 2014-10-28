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
public class DifficultyMenu implements Screen{
    MyGdxGame game;
    Skin skin;
    Table table;
    Stage stage;
    ArrayList<TextButton> difficulty;
    Label title;
    ArrayList<String> list;

    public DifficultyMenu(MyGdxGame game) {
        this.game = game;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();


        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        skin.getFont("title").setScale(3.5f, 5f);
        skin.getFont("medium").setScale(2.5f, 2.5f);
        title = new Label("GuiJuSanSe",skin);
        table.add(title).padBottom(500).row();

        // Liste de Difficulté et création des boutons
        list = new ArrayList<String>();
        list.add("Easy");
        list.add("Normal");
        list.add("Difficult");

        // Crée les boutons
        difficulty = new ArrayList<TextButton>();
        for (String s : list) {
            TextButton button = new TextButton(s, skin);
            difficulty.add(button);
            table.add(button).size(Gdx.graphics.getWidth(), 150).padBottom(20).row();
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

    public void resize(int width, int height) {

    }

    public void show() {
        //On active nos boutons

        for (final TextButton button : difficulty) {
            button.addListener(new ClickListener() {
                TextButton t=button;
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setSong("");
                    game.setSongm(new SongMenu(game));
                    game.getTmA().buttonSong.setText("Choix Chanson");
                    game.setDifficulty(t.getText().toString());
                    game.getTmA().buttonDifficulty.setText(t.getText().toString());
                    // Ajout des chansons OPE
                    game.getSongm().list.add("Song1");
                    game.getSongm().list.add("Song2");
                    game.getSongm().list.add("Song3");
                    for (String s : game.getSongm().list) {
                        TextButton button = new TextButton(s, skin);
                        game.getSongm().song.add(button);
                        game.getSongm().table.add(button).size(Gdx.graphics.getWidth(), 150).padBottom(20).row();
                    }
                    game.setScreen(game.getTmA());
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
