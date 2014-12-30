package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.fichier.Chanson;
import com.mygdx.game.fichier.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliette on 28/12/14.
 */
public class Jeu implements Screen {

    MyGdxGame myGdxGame;
    Skin skin;
    Table table;
    Stage stage;
    Chanson chanson;
    float vitesse;
    Thread t;
    private Music music;
    TextButton button1,button2,button3;
    List<Note> listNote;

    public Jeu(MyGdxGame game) {

        myGdxGame=game;
        table=new Table();
        stage = new Stage();
        vitesse = 1f;
        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        button1 = new TextButton(null,skin,"buttontwo");
        button1.setSize(150, 150);
        button1.setPosition(150, 100);
        stage.addActor(button1);

        button2 = new TextButton(null,skin,"buttonthree");
        button2.setSize(150,150);
        button2.setPosition(450,100);
        stage.addActor(button2);


        button3 = new TextButton(null,skin,"buttonfour");
        button3.setSize(150,150);
        button3.setPosition(750, 100);
        stage.addActor(button3);
        t= new Thread() {
            public void run() {
                stage.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(button1.isPressed())
                            System.out.println("1");
                        if(button2.isPressed())
                            System.out.println("2");
                        if(button3.isPressed())
                            System.out.println("3");
                    }
                });

            }
        };
        t.start();
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

        //Couper la musique
        myGdxGame.getMusic().stop();

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
        table.setFillParent(true);

        //On ajoute les acteurs a la scène
        stage.addActor(table);

        try {
            chanson.setListNote(myGdxGame.getDaosAccess().getNoteDao().getAllBySongId(chanson.getIdChanson()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // On place la scène
        Gdx.input.setInputProcessor(stage);
        String s=chanson.getTitle().replaceAll("[\\W]", "");
        music=Gdx.audio.newMusic(Gdx.files.internal("sound/"+ s+".mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        //Crée la liste d'image correspondant au note.
        MoveToAction moveAction;
        Image img;
        for(Note n: chanson.getListNote()){
            img = null;
            moveAction = new MoveToAction();
            moveAction.setDuration(vitesse);
            if (n.getPosition() == 0) {
                img = new Image(new Texture("skin/bB.png"));
                img.setPosition(150, myGdxGame.getLongueur()+ vitesse*50);
                moveAction.setPosition(150f, 100f);
            }else if (n.getPosition() == 1) {
                img = new Image(new Texture("skin/bA.png"));
                img.setPosition(450,myGdxGame.getLongueur()+ vitesse*50);
                moveAction.setPosition(450f, 100f);
            }else if (n.getPosition() == 2) {
                img = new Image(new Texture("skin/bC.png"));
                img.setPosition(750, myGdxGame.getLongueur()+ vitesse*50);
                moveAction.setPosition(750f, 100f);
            }
            img.setSize(150, 150);
            img.addAction(moveAction);
            stage.addActor(img);
        }

    }

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void hide() {
        t.interrupt();
        music.pause();
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#pause()
     */
    @Override
    public void pause() {
        t.interrupt();
        music.pause();
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#resume()
     */
    @Override
    public void resume() {
        t.interrupt();
        music.pause();
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    public Chanson getChanson() {
        return chanson;
    }

    public void setChanson(Chanson chanson) {
        this.chanson = chanson;
    }
}
