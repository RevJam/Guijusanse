package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.fichier.Lecture;

import java.io.IOException;

/**
 * Created by jamu on 28/10/14.
 */

// JEU EN LUI MEME
public class GoGame implements Screen {
    MyGdxGame game;
    Texture tex;
    SpriteBatch batch;
    int hpos, vpos, diff;
    int tailleBarre, score;

    public GoGame(MyGdxGame game, Texture tex){
        this.game = game;
        this.tex = tex;
        batch = new SpriteBatch();
        diff = 5;
        tailleBarre = game.getLongueur()/15;
        hpos = game.getLongueur() - tex.getHeight() - tailleBarre;
        vpos = game.getLargeur()/2 - tex.getHeight();
        score = 0;

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        hpos -= (int) (Gdx.input.getAccelerometerY())*diff;
        vpos -= (int) (Gdx.input.getAccelerometerX())*diff;

        if (hpos < 0) {
            hpos = 0;
        }

        if (hpos > Gdx.graphics.getHeight() - tex.getHeight() - tailleBarre)
            hpos = Gdx.graphics.getHeight() - tex.getHeight() - tailleBarre;

        if (vpos < 0) {
            vpos=0;
        }

        if (vpos > Gdx.graphics.getWidth() - tex.getWidth()) {
            vpos = Gdx.graphics.getWidth() - tex.getWidth();
        }

        hpos += (int)(Gdx.graphics.getDeltaTime());
        vpos += (int)(Gdx.graphics.getDeltaTime());

        batch.draw(tex,vpos,hpos);
        batch.end();
    }

    public void resize(int width, int height) {

    }

    public void show() {

    }

    public void hide() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
