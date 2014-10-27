package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    Integer hpos = 0;
    Integer hspeed = 120;
    Integer vpos = 0;
    Integer vspeed = 200;

    public MyGdxGame() {
        super();
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        // Hey !

        hpos -= (int)(Gdx.input.getAccelerometerY());
        vpos -= (int)(Gdx.input.getAccelerometerX());

        if (hpos < 0) hpos = 0;
        if (hpos > Gdx.graphics.getHeight() - img.getHeight()) hpos = Gdx.graphics.getHeight() - img.getHeight();
        if (vpos < 0) vpos = 0;
        if (vpos > Gdx.graphics.getWidth() - img.getWidth()) vpos = Gdx.graphics.getWidth() - img.getWidth();

        /*hpos += (int)(Gdx.graphics.getDeltaTime() * hspeed);
        vpos += (int)(Gdx.graphics.getDeltaTime() * vspeed);
        if (hpos < 0) hspeed *= -1;
        if (hpos > Gdx.graphics.getHeight() - img.getHeight()) hspeed *= -1;
        if (vpos < 0) vspeed *= -1;
        if (vpos > Gdx.graphics.getWidth() - img.getWidth()) vspeed *= -1;*/

        batch.draw(img, vpos, hpos);
		batch.end();
	}
}
