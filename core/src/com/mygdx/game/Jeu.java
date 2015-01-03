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
import com.mygdx.game.Game.TimeInterface;
import com.mygdx.game.fichier.*;
import com.mygdx.game.menu.ScoreMenu;


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
    private Music music;
    TextButton button1,button2,button3;
    Image img;
    Note note;
    MoveToAction moveAction;
    TimeInterface timeInterface;
    long t;
    int i;
    int tailleRond, pos1, pos2, pos3, cpt;
    int scoreCourant=0;

    public Jeu(MyGdxGame game) {

        myGdxGame=game;
        timeInterface=game.getTimeInterface();
        table=new Table();
        stage = new Stage();
        t = 0;
        i = 0;
        cpt = 4;
        vitesse = 5f;
        tailleRond = myGdxGame.getLargeur() / 7;
        pos1 = (myGdxGame.getLargeur() / 6) - (tailleRond/2);
        pos2 = (myGdxGame.getLargeur() / 2) - (tailleRond/2);
        pos3 = (myGdxGame.getLargeur()*5 / 6) - (tailleRond/2);
        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        button1 = new TextButton(null,skin,"buttontwo");
        button1.setSize(tailleRond, tailleRond);
        button1.setPosition(pos1, tailleRond);
        stage.addActor(button1);

        button2 = new TextButton(null,skin,"buttonthree");
        button2.setSize(tailleRond,tailleRond);
        button2.setPosition(pos2, tailleRond);
        stage.addActor(button2);

        button3 = new TextButton(null,skin,"buttonfour");
        button3.setSize(tailleRond,tailleRond);
        button3.setPosition(pos3, tailleRond);
        stage.addActor(button3);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (y> tailleRond && y < tailleRond*2) {
                    if (x > pos1 - tailleRond && x < pos1 + tailleRond) {
                        //if (button1.isPressed()) {
                        for (int j = cpt; j < stage.getActors().size; j++) {
                            if (stage.getActors().get(j).getY() == -300) {
                                cpt++;
                            } else {
                                if (stage.getActors().get(j).getX() == pos1 && (stage.getActors().get(j).getY() > (2/3)*tailleRond && stage.getActors().get(j).getY() < tailleRond * (4/3))) {
                                    System.out.println("OH YEAH 1");
                                    scoreCourant+=100;
                                    break;
                                }else {
                                    scoreCourant-=10;
                                }
                            }
                        }
                    }
                    if (x > pos2 - tailleRond && x < pos2 + tailleRond) {
                        //if (button2.isPressed()){
                        for (int j = cpt; j < stage.getActors().size; j++) {
                            if (stage.getActors().get(j).getY() == -300) {
                                cpt++;
                            } else {
                                if (stage.getActors().get(j).getX() == pos2 && (stage.getActors().get(j).getY() > (2/3)*tailleRond && stage.getActors().get(j).getY() < tailleRond * (4/3))) {
                                    System.out.println("OH YEAH 2");
                                    scoreCourant+=100;
                                    break;
                                }else {
                                    scoreCourant-=10;
                                }
                            }
                        }
                    }

                    if (x > pos3 - tailleRond && x < pos3 + tailleRond) {
                        //if (button3.isPressed()){
                        for (int j = cpt; j < stage.getActors().size; j++) {
                            if (stage.getActors().get(j).getY() == -300) {
                                cpt++;
                                scoreCourant+=1000;
                            } else {
                                if (stage.getActors().get(j).getX() == pos3 && (stage.getActors().get(j).getY() > (2/3)*tailleRond && stage.getActors().get(j).getY() < tailleRond * (4/3))) {
                                    System.out.println("OH YEAH 3");
                                    scoreCourant+=100;
                                    break;
                                }else {
                                    scoreCourant-=10;
                                }
                            }
                        }
                    }
                }

            }
        });


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

        //pour eviter le nullpointerException
        //a rajouter une condition pour si la musique est finie, affichage d'une fenetre de score
        if(i<chanson.getListNote().size()) {
            note = chanson.getListNote().get(i);

            if(t/1000 == note.getTemps()/1000) {
                //img = null;
                moveAction = new MoveToAction();
                moveAction.setDuration(vitesse);

                if (note.getPosition() == 0) {
                    img = new Image(new Texture("skin/bB.png"));
                    img.setPosition(pos1, myGdxGame.getLongueur() + vitesse * 50);
                    moveAction.setPosition(pos1, -300f);
                } else if (note.getPosition() == 1) {
                    img = new Image(new Texture("skin/bA.png"));
                    img.setPosition(pos2, myGdxGame.getLongueur() + vitesse * 50);
                    moveAction.setPosition(pos2, -300f);
                } else if (note.getPosition() == 2) {
                    img = new Image(new Texture("skin/bC.png"));
                    img.setPosition(pos3, myGdxGame.getLongueur() + vitesse * 50);
                    moveAction.setPosition(pos3, -300f);
                }
                img.setSize(tailleRond, tailleRond);

                img.addAction(moveAction);

                stage.addActor(img);
                i++;
            }

        }

        if(t>=chanson.getTempsChansonTotal()){
            System.out.println("in the if "+t);
            Score score = new Score("user",chanson.getTitle(), chanson.getDifficulter(), scoreCourant);
            myGdxGame.getDaosAccess().getScoreDao().add(score);
            myGdxGame.setScore(score);
            myGdxGame.setScreen(myGdxGame.getScoreMenu());
        }

        t=timeInterface.startTime();
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

        timeInterface.setCurrentTimeSystem();

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
        music.setLooping(false);
        music.setVolume(0.5f);
        music.play();
    }

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void hide() {
        music.pause();
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#pause()
     */
    @Override
    public void pause() {
        music.pause();
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#resume()
     */
    @Override
    public void resume() {
        music.play();
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        music.dispose();
    }

    public Chanson getChanson() {
        return chanson;
    }

    public void setChanson(Chanson chanson) {
        this.chanson = chanson;
    }
}