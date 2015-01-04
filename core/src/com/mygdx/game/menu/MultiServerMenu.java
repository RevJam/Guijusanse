package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.network.Message;
import com.mygdx.game.network.MessagesHandler;
import com.mygdx.game.network.client.Client;
import com.mygdx.game.network.server.Server;

import java.util.List;

/**
 * Created by guillaume on 04/01/15.
 */
public class MultiServerMenu implements Screen {
    MyGdxGame game;
    Server mServer;
    Skin skin;
    Table table;
    Stage stage;
    TextButton buttonReturn, buttonStart;
    Label title, addressesListLabel, connectedClientLabel;
    
    int mNumberOfConnectedClient = -1;

    public MultiServerMenu(MyGdxGame myGdxGame) {
        this.game = myGdxGame;

        // Crée la scene
        stage = new Stage();

        // Crée la trame de la page
        table = new Table();

        // Charge le skin de l'appli
        skin = new Skin(Gdx.files.internal("skin/defaultskin.json"), new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        // On change la taille de la police
        skin.getFont("title").setScale((game.getLargeur()/500), 2.6f);
        skin.getFont("medium").setScale((game.getLargeur()/550), 1.2f);

        // On crée un bouton et un titre
        buttonReturn = new TextButton("Retour", skin, "buttonfour");
        buttonStart = new TextButton("Lancer la partie", skin, "buttonthree");
        title = new Label("GuiJuSanSe", skin);
        addressesListLabel = new Label("", skin, "score");
        connectedClientLabel = new Label("", skin, "score");


        // On ajoute les elements a la trame
        table.add(title).padBottom(40).row();
        table.add(buttonStart).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
        table.add(buttonReturn).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
        table.add(addressesListLabel).padBottom(0).row();
        table.add(connectedClientLabel).padBottom(0).row();

        // Création du serveur 
        mServer = new Server();

        // Event Listeners
        buttonReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getMm());
            }
        });
        buttonStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getTmA());
                
                // On envoi un message vide avec comme en-tête "start" pour signaler aux clients de démarrer
                // TO DO écran de sélection du niveau
                //mServer.sendMessageToAll(new Message("start", ""));
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

        // Maj du nombre de clients connecté 
        int numberOfConnectedClient = mServer.numberOfConnectedClient();
        if (numberOfConnectedClient != mNumberOfConnectedClient) {
            mNumberOfConnectedClient = numberOfConnectedClient;
            connectedClientLabel.setText("Client connectes: " +numberOfConnectedClient);
        }
    }

    public void resize(int width, int height) {

    }

    public void show() {

        table.setFillParent(true);
        //On ajoute les acteurs a la scène
        stage.addActor(table);

        // On place la scène
        Gdx.input.setInputProcessor(stage);

        // Liste des IP
        List<String> addresses = mServer.getAddresses();
        String addressesList = "";
        for (String address : addresses) {
            if (!address.equals("127.0.0.1"))
                addressesList = addressesList + address + "\n";
        }
        addressesListLabel.setText(addressesList);

        // Assigantion du gestionnaire de messages réseau et démarrage du serveur
        this.game.setMessagesHandler(mServer);
        mServer.startAcceptingConnections();
    }

    public void hide() {
        mServer.stopAcceptingConnections();
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

