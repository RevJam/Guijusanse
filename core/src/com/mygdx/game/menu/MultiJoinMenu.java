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

/**
 * Created by guillaume on 04/01/15.
 */
public class MultiJoinMenu implements Screen {
    MyGdxGame game;
    Client mClient;
    Skin skin;
    Table table;
    Stage stage;
    TextButton buttonReturn, buttonConnexion;
    Label title, connectionLabel, connectionErrorLabel, connectedLabel;

    public MultiJoinMenu(MyGdxGame myGdxGame) {
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
        buttonConnexion = new TextButton("Saisir l'IP du serveur", skin, "buttonthree");
        title = new Label("GuiJuSanSe", skin);

        connectionLabel = new Label("Connexion en cours...", skin, "score");
        connectionErrorLabel = new Label("Erreur de connexion", skin, "score");
        connectedLabel = new Label("Connecte\nAttente du serveur", skin, "score");
        connectionLabel.setVisible(false);
        connectionErrorLabel.setVisible(false);
        connectedLabel.setVisible(false);

        // On ajoute les elements a la trame
        table.add(title).padBottom((game.getLongueur() / 5)).row();
        table.add(buttonConnexion).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
        table.add(buttonReturn).size(game.getLargeur(), (game.getLongueur() / 10)).padBottom(20).row();
        table.add(connectionLabel).padBottom(0).row();
        table.add(connectionErrorLabel).padBottom(0).row();
        table.add(connectedLabel).padBottom(0).row();

        // Création du client 
        mClient = new Client();

        // Event Listeners
        buttonConnexion.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Input.TextInputListener listener = new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        connectionLabel.setVisible(true);

                        // Tentative de connexion au serveur
                        Client client = (Client) game.getMessagesHandler();
                        boolean connected = client.connectToServer(text);

                        if (connected) {
                            connectionLabel.setVisible(false);
                            connectionErrorLabel.setVisible(false);
                            connectedLabel.setVisible(true);
                        }
                        else {
                            connectionLabel.setVisible(false);
                            connectionErrorLabel.setVisible(true);
                        }
                    }

                    @Override
                    public void canceled() {
                        // Rien
                    }
                };
                // TODO valeur par défaut vide
                Gdx.input.getTextInput(listener, "Dialog Title", "");

            }
        });
        buttonReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.getMm());
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
        
        // Check les messages
        Message msg = mClient.getOlderMessage();
        if (msg != null && msg.getHeader().equals("start")) {
            
            String content = msg.getContent();
            String[] msgData = content.split(";");
            
            if (msgData.length == 2) {
                game.setDifficulty(msgData[0]);
                game.setSong(msgData[1]);

                game.setScreen(game.getEcranJeu());
            }  
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

        // Assigantion du gestionnaire de messages réseau
        game.setMessagesHandler(mClient);
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

