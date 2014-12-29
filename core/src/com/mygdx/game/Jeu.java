package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliette on 28/12/14.
 */
public class Jeu implements Screen {
    MyGdxGame myGdxGame;
    Skin skin;
    Stage stage;

    TextButton button1,button2,button3;
    public Jeu(MyGdxGame game) {

        myGdxGame=game;

        stage = new Stage();

        // Charge le skin de l'appli
        skin =new Skin(Gdx.files.internal("skin/defaultskin.json"),new TextureAtlas(Gdx.files.internal("skin/default.pack")));

        button1 = new TextButton(null,skin,"buttontwo");
        button1.setSize(150,150);
        button1.setPosition(150,100);
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                System.out.println("bouton1");
                super.clicked(event, x, y);
            }
        });
        stage.addActor(button1);

        button2 = new TextButton(null,skin,"buttonthree");
        button2.setSize(150,150);
        button2.setPosition(450,100);
        button2.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("bouton2");
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                System.out.println("bouton2");
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("bouton2");
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("bouton2");
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("bouton2");
                super.exit(event, x, y, pointer, toActor);
            }

            /**
             * If a touch down is being monitored, the drag and touch up events are ignored until the next touch up.
             */
            @Override
            public void cancel() {
                System.out.println("bouton2");
                super.cancel();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("bouton2");
                super.clicked(event, x, y);
            }

            /**
             * Returns true if the specified position is over the specified actor or within the tap square.
             *
             * @param actor
             * @param x
             * @param y
             */
            @Override
            public boolean isOver(Actor actor, float x, float y) {
                System.out.println("bouton2");
                return super.isOver(actor, x, y);
            }

            @Override
            public boolean inTapSquare(float x, float y) {
                System.out.println("bouton2");
                return super.inTapSquare(x, y);
            }

            /**
             * Returns true if a touch is within the tap square.
             */
            @Override
            public boolean inTapSquare() {
                System.out.println("bouton2");
                return super.inTapSquare();
            }

            /**
             * The tap square will not longer be used for the current touch.
             */
            @Override
            public void invalidateTapSquare() {
                System.out.println("bouton2");
                super.invalidateTapSquare();
            }

            /**
             * Returns true if a touch is over the actor or within the tap square.
             */
            @Override
            public boolean isPressed() {
                System.out.println("bouton2");
                return super.isPressed();
            }
        });
        stage.addActor(button2);

        button3 = new TextButton(null,skin,"buttonfour");
        button3.setSize(150,150);
        button3.setPosition(750,100);

        button3.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("bouton2");
                super.clicked(event, x, y);
                System.out.println("bouton2");
            }
        });
        stage.addActor(button3);


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

    }

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
