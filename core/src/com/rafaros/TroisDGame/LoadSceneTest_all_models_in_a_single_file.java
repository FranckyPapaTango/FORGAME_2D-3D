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

public class LoadSceneTest_all_models_in_a_single_file implements ApplicationListener {
    public AssetManager assets;
    public Array<ModelInstance> blocks = new Array<>();
    public PerspectiveCamera cam;
    public CameraInputController camController;
    String data = "loadscene/data";
    public Environment environment;
    public Array<ModelInstance> instances = new Array<>();
    public Array<ModelInstance> invaders = new Array<>();
    public boolean loading;
    public ModelBatch modelBatch;
    public ModelInstance ship;
    public ModelInstance space;

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
        this.assets.load(String.valueOf(this.data) + "/invaders.g3db", Model.class);
        this.loading = true;
    }

    private void doneLoading() {
        Model model = (Model) this.assets.get(String.valueOf(this.data) + "/invaders.g3db", Model.class);
        this.ship = new ModelInstance(model, "ship");
        this.ship.transform.setToRotation(Vector3.Y, 180.0f).trn(0.0f, 0.0f, 6.0f);
        this.instances.add(this.ship);
        for (float x = -5.0f; x <= 5.0f; x += 2.0f) {
            ModelInstance block = new ModelInstance(model, "block");
            block.transform.setToTranslation(x, 0.0f, 3.0f);
            this.instances.add(block);
            this.blocks.add(block);
        }
        for (float x2 = -5.0f; x2 <= 5.0f; x2 += 2.0f) {
            for (float z = -8.0f; z <= 0.0f; z += 2.0f) {
                ModelInstance invader = new ModelInstance(model, "invader");
                invader.transform.setToTranslation(x2, 0.0f, z);
                this.instances.add(invader);
                this.invaders.add(invader);
            }
        }
        this.space = new ModelInstance(model, "space");
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
    public void resize(int width, int height) {
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void pause() {
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resume() {
    }
}
