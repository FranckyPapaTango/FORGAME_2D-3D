package com.rafaros.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game implements ApplicationListener {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Sprite sprite;
    private Texture texture;

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.camera = new OrthographicCamera(1.0f, ((float) Gdx.graphics.getHeight()) / ((float) Gdx.graphics.getWidth()));
        this.batch = new SpriteBatch();
        this.texture = new Texture(Gdx.files.internal("data/libgdx.png"));
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.sprite = new Sprite(new TextureRegion(this.texture, 0, 0, 512, 275));
        this.sprite.setSize(0.9f, (this.sprite.getHeight() * 0.9f) / this.sprite.getWidth());
        this.sprite.setOrigin(this.sprite.getWidth() / 2.0f, this.sprite.getHeight() / 2.0f);
        this.sprite.setPosition((-this.sprite.getWidth()) / 2.0f, (-this.sprite.getHeight()) / 2.0f);
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        this.batch.dispose();
        this.texture.dispose();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16384);
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
        this.sprite.draw(this.batch);
        this.batch.end();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resize(int width, int height) {
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void pause() {
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resume() {
    }
}
