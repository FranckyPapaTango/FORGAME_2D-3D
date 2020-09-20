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

public class LoadSceneTest implements ApplicationListener {
    public AssetManager assets;
    public Array<ModelInstance> blocks = new Array<>();
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public Environment environment;
    public Array<ModelInstance> instances = new Array<>();
    public Array<ModelInstance> invaders = new Array<>();
    public boolean loading;
    public ModelBatch modelBatch;
    public ModelInstance ship;
    public ModelInstance space;
    public ModelInstance terre;

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.modelBatch = new ModelBatch();
        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        this.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.0f, -0.8f, -0.2f));
        this.cam = new PerspectiveCamera(67.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
        this.cam.position.set(0.0f, 7.0f, 10.0f);
        this.cam.lookAt(0.0f, 0.0f, 0.0f);
        this.cam.near = 1.0f;
        this.cam.far = 300.0f;
        this.cam.update();
        this.camController = new CameraInputController(this.cam);
        Gdx.input.setInputProcessor(this.camController);
        this.assets = new AssetManager();
        this.assets.load("loadscene/data/ship.obj", Model.class);
        this.assets.load("loadscene/data/block.obj", Model.class);
        this.assets.load("loadscene/data/invader.obj", Model.class);
        this.assets.load("terre/terre.obj", Model.class);
        this.assets.load("loadscene/data/spacesphere.obj", Model.class);
        this.loading = true;
    }

    private void doneLoading() {
        this.ship = new ModelInstance((Model) this.assets.get("loadscene/data/ship.obj", Model.class));
        this.ship.transform.setToRotation(Vector3.Y, 180.0f).trn(0.0f, 0.0f, 6.0f);
        this.instances.add(this.ship);
        this.terre = new ModelInstance((Model) this.assets.get("terre/terre.obj", Model.class));
        this.terre.transform.setToRotation(Vector3.X, 180.0f).trn(0.0f, 6.0f, 0.0f);
        this.instances.add(this.terre);
        Model blockModel = (Model) this.assets.get("loadscene/data/block.obj", Model.class);
        for (float x = -5.0f; x <= 5.0f; x += 2.0f) {
            ModelInstance block = new ModelInstance(blockModel);
            block.transform.setToTranslation(x, 0.0f, 3.0f);
            this.instances.add(block);
            this.blocks.add(block);
        }
        Model invaderModel = (Model) this.assets.get("loadscene/data/invader.obj", Model.class);
        for (float x2 = -5.0f; x2 <= 5.0f; x2 += 2.0f) {
            for (float z = -8.0f; z <= 0.0f; z += 2.0f) {
                ModelInstance invader = new ModelInstance(invaderModel);
                invader.transform.setToTranslation(x2, 0.0f, z);
                this.instances.add(invader);
                this.invaders.add(invader);
            }
        }
        this.space = new ModelInstance((Model) this.assets.get("loadscene/data/spacesphere.obj", Model.class));
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
        if (this.space != null) {
            this.modelBatch.render(this.space);
        }
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
