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

public class LoadScene_and_objects_with_multiple_file implements ApplicationListener {
    public AssetManager assets;
    public Array<ModelInstance> blocks = new Array<>();
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public Environment environment;
    public Array<ModelInstance> instances = new Array<>();
    public Array<ModelInstance> invaders = new Array<>();
    public boolean loading;
    public ModelBatch modelBatch;
    public Array<ModelInstance> ships = new Array<>();
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
        this.assets.load("loadscene/data/invader.obj", Model.class);
        this.assets.load("loadscene/data/spacesphere.obj", Model.class);
        this.assets.load("loadscene/data/terre.obj", Model.class);
        this.loading = true;
    }

    private void doneLoading() {
        Model shipModel = (Model) this.assets.get("loadscene/data/ship.obj", Model.class);
        ModelInstance ship = new ModelInstance(shipModel);
        ship.transform.setToRotation(Vector3.Y, 180.0f).trn(0.0f, 0.0f, 6.0f);
        this.instances.add(ship);
        this.ships.add(ship);
        ModelInstance ship2 = new ModelInstance(shipModel);
        ship2.transform.setToRotation(Vector3.Y, 180.0f).trn(3.0f, 4.0f, 4.0f);
        this.instances.add(ship2);
        this.ships.add(ship2);
        ModelInstance ship3 = new ModelInstance(shipModel);
        ship3.transform.setToRotation(Vector3.Y, 180.0f).trn(3.0f, -10.0f, 2.0f);
        this.instances.add(ship3);
        this.ships.add(ship3);
        ModelInstance ship4 = new ModelInstance(shipModel);
        ship4.transform.setToRotation(Vector3.Y, 180.0f).trn(5.0f, -8.0f, 4.0f);
        this.instances.add(ship4);
        this.ships.add(ship4);
        ModelInstance ship5 = new ModelInstance(shipModel);
        ship5.transform.setToRotation(Vector3.Y, 180.0f).trn(7.0f, -6.0f, 6.0f);
        this.instances.add(ship5);
        this.ships.add(ship5);
        Model invaderModel = (Model) this.assets.get("loadscene/data/invader.obj", Model.class);
        for (float x = -5.0f; x <= 5.0f; x += 2.0f) {
            for (float z = -8.0f; z <= 0.0f; z += 2.0f) {
                ModelInstance invader = new ModelInstance(invaderModel);
                invader.transform.setToTranslation(x, 0.0f, z);
                this.instances.add(invader);
                this.invaders.add(invader);
            }
        }
        for (float x2 = -5.0f; x2 <= 5.0f; x2 += 4.0f) {
            for (float z2 = -8.0f; z2 <= 0.0f; z2 += 4.0f) {
                ModelInstance invader2 = new ModelInstance(invaderModel);
                invader2.transform.setToTranslation(x2, -3.0f, z2);
                this.instances.add(invader2);
                this.invaders.add(invader2);
            }
        }
        for (float x3 = -15.0f; x3 <= -5.0f; x3 += 3.0f) {
            for (float z3 = -2.0f; z3 <= 6.0f; z3 += 3.0f) {
                ModelInstance invader3 = new ModelInstance(invaderModel);
                invader3.transform.setToTranslation(x3, 6.0f, z3);
                this.instances.add(invader3);
                this.invaders.add(invader3);
            }
        }
        ModelInstance invader4 = new ModelInstance(invaderModel);
        invader4.transform.setToRotation(Vector3.Y, 0.0f).trn(-5.0f, 0.0f, -8.0f);
        this.instances.add(invader4);
        this.invaders.add(invader4);
        ModelInstance invader5 = new ModelInstance(invaderModel);
        invader5.transform.setToRotation(Vector3.Y, 0.0f).trn(-3.0f, 10.0f, -20.0f);
        this.instances.add(invader5);
        this.invaders.add(invader5);
        ModelInstance invader6 = new ModelInstance(invaderModel);
        invader6.transform.setToRotation(Vector3.Y, 0.0f).trn(5.0f, 3.0f, 6.0f);
        this.instances.add(invader6);
        this.invaders.add(invader6);
        ModelInstance invader7 = new ModelInstance(invaderModel);
        invader7.transform.setToRotation(Vector3.Y, 0.0f).trn(-3.0f, -10.0f, 15.0f);
        this.instances.add(invader7);
        this.invaders.add(invader7);
        ModelInstance invader8 = new ModelInstance(invaderModel);
        invader8.transform.setToRotation(Vector3.Y, 45.0f).trn(13.0f, -30.0f, -6.0f);
        this.instances.add(invader8);
        this.invaders.add(invader8);
        this.terre = new ModelInstance((Model) this.assets.get("loadscene/data/terre.obj", Model.class));
        this.terre.transform.setToRotation(Vector3.Z, 180.0f).trn(0.0f, 0.0f, -35.0f);
        this.instances.add(this.terre);
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
