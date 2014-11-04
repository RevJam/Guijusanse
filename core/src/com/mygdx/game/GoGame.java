package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by jamu on 28/10/14.
 */

// JEU EN LUI MEME
public class GoGame implements Screen {
    MyGdxGame game;
    Stage stage;
    Texture chute1;

    public GoGame(MyGdxGame game) {
        this.game = game;
        // Si song OR difficulty == "", => Aleatoire
        stage = new Stage();

        // Une image qui doit descendre
        chute1 =  new Texture("skin/chuteA.png");
        Image chuteImg1 = new Image(chute1);
        chuteImg1.setPosition(game.getLargeur()/10, game.getLongueur()-50);
        stage.addActor(chuteImg1);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        /*game.batch.begin();

        game.hpos -= (int) (Gdx.input.getAccelerometerY());
        game.vpos -= (int) (Gdx.input.getAccelerometerX());

        if (game.hpos < 0) game.hpos = 0;
        if (game.hpos > Gdx.graphics.getHeight() - game.img.getHeight())
            game.hpos = Gdx.graphics.getHeight() - game.img.getHeight();
        if (game.vpos < 0) game.vpos = 0;
        if (game.vpos > Gdx.graphics.getWidth() - game.img.getWidth())
            game.vpos = Gdx.graphics.getWidth() - game.img.getWidth();*/

        /*hpos += (int)(Gdx.graphics.getDeltaTime() * hspeed);
        vpos += (int)(Gdx.graphics.getDeltaTime() * vspeed);
        if (hpos < 0) hspeed *= -1;
        if (hpos > Gdx.graphics.getHeight() - img.getHeight()) hspeed *= -1;
        if (vpos < 0) vspeed *= -1;
        if (vpos > Gdx.graphics.getWidth() - img.getWidth()) vspeed *= -1;*/

        /*game.batch.draw(game.img, game.vpos, game.hpos);
        game.batch.end();*/

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
        chute1.dispose();
        stage.dispose();
    }
}
