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
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;

public class LoadSceneTest_scene_and_models_in_one_file implements ApplicationListener {
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
        this.assets.load(String.valueOf(this.data) + "/invaderscene.g3db", Model.class);
        this.loading = true;
    }

    private void doneLoading() {
        Model model = (Model) this.assets.get(String.valueOf(this.data) + "/invaderscene.g3db", Model.class);
        for (int i = 0; i < model.nodes.size; i++) {
            String id = model.nodes.get(i).id;
            ModelInstance instance = new ModelInstance(model, id);
            Node node = instance.getNode(id);
            instance.transform.set(node.globalTransform);
            node.translation.set(0.0f, 0.0f, 0.0f);
            node.scale.set(1.0f, 1.0f, 1.0f);
            node.rotation.idt();
            instance.calculateTransforms();
            if (id.equals("space")) {
                this.space = instance;
            } else {
                this.instances.add(instance);
                if (id.equals("ship")) {
                    this.ship = instance;
                } else if (id.startsWith("block")) {
                    this.blocks.add(instance);
                } else if (id.startsWith("invader")) {
                    this.invaders.add(instance);
                }
            }
        }
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
