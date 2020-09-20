package com.rafaros.TroisDGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class TroisDGame_ implements ApplicationListener, InputProcessor {
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public Environment environment;
    public ModelInstance instance;
    public Model model;
    public ModelBatch modelBatch;
    private MyGameCallback myGameCallback;

    public interface MyGameCallback {
        void onStartActivityA();

        void onStartActivityB();

        void onStartSomeActivity(int i, String str);
    }

    public void setMyGameCallback(MyGameCallback callback) {
        this.myGameCallback = callback;
    }

    private void someMethod() {
        if (this.myGameCallback != null) {
            this.myGameCallback.onStartActivityA();
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        this.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.0f, -0.8f, -0.2f));
        this.cam = new PerspectiveCamera(67.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
        this.cam.position.set(10.0f, 10.0f, 10.0f);
        this.cam.lookAt(0.0f, 0.0f, 0.0f);
        this.cam.near = 1.0f;
        this.cam.far = 300.0f;
        this.model = new ModelBuilder().createBox(5.0f, 5.0f, 5.0f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), 9);
        this.instance = new ModelInstance(this.model);
        this.modelBatch = new ModelBatch();
        this.camController = new CameraInputController(this.cam);
        Gdx.input.setInputProcessor(this.camController);
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        this.modelBatch.dispose();
        this.model.dispose();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        this.camController.update();
        this.cam.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(16640);
        this.modelBatch.begin(this.cam);
        this.modelBatch.render(this.instance, this.environment);
        this.modelBatch.end();
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

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyDown(int keycode) {
        if (keycode == 21) {
            this.cam.rotateAround(new Vector3(0.0f, 0.0f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f), 1.0f);
        }
        if (keycode != 22) {
            return true;
        }
        this.cam.rotateAround(new Vector3(0.0f, 0.0f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f), -1.0f);
        return true;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char arg0) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyUp(int arg0) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int arg0, int arg1) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean scrolled(int arg0) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int arg0, int arg1, int arg2) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
        return false;
    }
}
