package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jamu on 28/10/14.
 */
public class GoGame implements Screen {
    MyGdxGame game;

    public GoGame(MyGdxGame game) {
        this.game = game;
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.hpos -= (int) (Gdx.input.getAccelerometerY());
        game.vpos -= (int) (Gdx.input.getAccelerometerX());

        if (game.hpos < 0) game.hpos = 0;
        if (game.hpos > Gdx.graphics.getHeight() - game.img.getHeight())
            game.hpos = Gdx.graphics.getHeight() - game.img.getHeight();
        if (game.vpos < 0) game.vpos = 0;
        if (game.vpos > Gdx.graphics.getWidth() - game.img.getWidth())
            game.vpos = Gdx.graphics.getWidth() - game.img.getWidth();

        /*hpos += (int)(Gdx.graphics.getDeltaTime() * hspeed);
        vpos += (int)(Gdx.graphics.getDeltaTime() * vspeed);
        if (hpos < 0) hspeed *= -1;
        if (hpos > Gdx.graphics.getHeight() - img.getHeight()) hspeed *= -1;
        if (vpos < 0) vspeed *= -1;
        if (vpos > Gdx.graphics.getWidth() - img.getWidth()) vspeed *= -1;*/

        game.batch.draw(game.img, game.vpos, game.hpos);
        game.batch.end();

        if (Gdx.input.justTouched())
            game.setScreen(game.fm);
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
