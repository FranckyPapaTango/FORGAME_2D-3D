package com.rafaros.TroisDGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class LoadModelsTest implements ApplicationListener {
    public AssetManager assets;
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public Environment environment;
    public Array<ModelInstance> instances = new Array<>();
    public boolean loading;
    public ModelBatch modelBatch;
    private float xcam;
    private float ycam;
    private float zcam;

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.modelBatch = new ModelBatch();
        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.9f, 0.9f, 0.9f, 1.0f));
        this.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.0f, -0.8f, -0.2f));
        this.cam = new PerspectiveCamera(67.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
        this.xcam = 0.0f;
        this.ycam = 0.0f;
        this.zcam = -23.0f;
        this.cam.position.set(this.xcam, this.ycam, this.zcam);
        this.cam.lookAt(0.0f, 0.0f, 0.0f);
        this.cam.near = 0.0f;
        this.cam.far = 300.0f;
        this.cam.update();
        this.camController = new CameraInputController(this.cam);
        Gdx.input.setInputProcessor(this.camController);
        this.assets = new AssetManager();
        this.assets.load("terre/terre.obj", Model.class);
        this.loading = true;
    }

    private void doneLoading() {
        ModelInstance shipInstance = new ModelInstance((Model) this.assets.get("terre/terre.obj", Model.class));
        shipInstance.transform.setToRotation(Vector3.X, 180.0f).trn(0.0f, 0.0f, 0.0f);
        this.instances.add(shipInstance);
        this.loading = false;
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        if (this.loading && this.assets.update()) {
            doneLoading();
        }
        this.camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(16640);
        this.modelBatch.begin(this.cam);
        this.modelBatch.render(this.instances, this.environment);
        this.modelBatch.end();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        this.modelBatch.dispose();
        this.instances.clear();
        this.assets.dispose();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resume() {
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resize(int width, int height) {
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void pause() {
    }
}
