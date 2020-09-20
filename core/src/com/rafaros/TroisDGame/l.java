package com.rafaros.TroisDGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class l implements ApplicationListener {
    public PerspectiveCamera cam;
    public CameraInputController camController;
    private String data = "ship";
    public Environment environment;
    public ModelInstance instance;
    public Model model;
    public ModelBatch modelBatch;

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.modelBatch = new ModelBatch();
        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        this.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.0f, -0.8f, -0.2f));
        this.cam = new PerspectiveCamera(67.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
        this.cam.position.set(1.0f, 1.0f, 1.0f);
        this.cam.lookAt(0.0f, 0.0f, 0.0f);
        this.cam.near = 1.0f;
        this.cam.far = 300.0f;
        this.cam.update();
        this.model = new ObjLoader().loadModel(Gdx.files.internal(String.valueOf(this.data) + "/ship.obj"));
        this.instance = new ModelInstance(this.model);
        this.camController = new CameraInputController(this.cam);
        Gdx.input.setInputProcessor(this.camController);
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        this.camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(16640);
        this.modelBatch.begin(this.cam);
        this.modelBatch.render(this.instance, this.environment);
        this.modelBatch.end();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        this.modelBatch.dispose();
        this.model.dispose();
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
