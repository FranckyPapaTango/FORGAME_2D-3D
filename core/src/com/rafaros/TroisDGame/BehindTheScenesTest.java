package com.rafaros.TroisDGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.utils.JsonReader;

public class BehindTheScenesTest implements ApplicationListener {
    public PerspectiveCamera cam;
    public CameraInputController camController;
    private String data = "behindscenes/data";
    public Environment environment;
    public Model model;
    public ModelBatch modelBatch;
    public Renderable renderable;

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.modelBatch = new ModelBatch();
        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        this.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.0f, -0.8f, -0.2f));
        this.cam = new PerspectiveCamera(67.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
        this.cam.position.set(2.0f, 2.0f, 2.0f);
        this.cam.lookAt(0.0f, 0.0f, 0.0f);
        this.cam.near = 1.0f;
        this.cam.far = 300.0f;
        this.cam.update();
        this.camController = new CameraInputController(this.cam);
        Gdx.input.setInputProcessor(this.camController);
        this.model = new Model(new G3dModelLoader(new JsonReader()).loadModelData(Gdx.files.internal(String.valueOf(this.data) + "/invaderscene.g3dj")), new TextureProvider.FileTextureProvider());
        this.renderable = new Renderable();
        this.renderable.material = this.model.getNode("ship").parts.get(0).material;
        this.renderable.environment = this.environment;
        this.renderable.worldTransform.idt();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        this.camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(16640);
        this.modelBatch.begin(this.cam);
        this.modelBatch.render(this.renderable);
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
