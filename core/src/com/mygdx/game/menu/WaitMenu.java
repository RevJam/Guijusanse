package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.MyGdxGame;

/**
 * Created by jamu on 28/10/14.
 */
public class WaitMenu implements Screen {

    MyGdxGame game;
    Skin skin;
    Table table;
    Stage stage;
    TextButton buttonGo, buttonCancel;
    Label title;
ScrollPane scroll = new ScrollPane(table);
    public WaitMenu(MyGdxGame game) {
        this.game = game;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();

        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        // On crée trois boutons et un titre
        buttonGo = new TextButton("Lancer", skin);
        buttonCancel = new TextButton("Retour", skin, "buttonthree");
        title = new Label("GuiJuSanSe",skin);

        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/300), 5f);
        skin.getFont("medium").setScale((game.getLargeur()/400), 2.5f);

        // On ajoute les elements a la trame
        table.add(title).padBottom((game.getLongueur()/5)).row();
        scroll = new ScrollPane(table);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

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
