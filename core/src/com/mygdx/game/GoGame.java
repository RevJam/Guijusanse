package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import java.io.IOException;

/**
 * Created by jamu on 28/10/14.
 */

// JEU EN LUI MEME
public class GoGame implements Screen {
    MyGdxGame game;
    Texture tex;


    public GoGame(MyGdxGame game, Texture tex) throws IOException {
        this.game = game;
        this.tex = tex;

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    /**
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Called when this screen becomes the current screen .
     */
    @Override
    public void show() {

    }

    /**
     * Called when this screen is no longer the current screen
     */
    @Override
    public void hide() {

    }

    /**
     *
     */
    @Override
    public void pause() {

    }

    /**
     *
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
