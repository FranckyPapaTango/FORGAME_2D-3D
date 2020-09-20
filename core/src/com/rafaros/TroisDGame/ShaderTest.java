package com.rafaros.TroisDGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.utils.JsonReader;

public class ShaderTest implements ApplicationListener {
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public Environment environment;
    public Model model;
    public RenderContext renderContext;
    public Renderable renderable;
    public Shader shader;

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
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
        this.model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("loadscene/data/invaders.g3db"));
        this.renderable = new Renderable();
        this.model.getNode("ship").parts.get(0).setRenderable(this.renderable);
        this.renderable.environment = this.environment;
        this.renderable.worldTransform.idt();
        this.renderContext = new RenderContext(new DefaultTextureBinder(1, 1));
        this.shader = new DefaultShader(this.renderable);
        this.shader.init();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        this.camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(16640);
        this.renderContext.begin();
        this.shader.begin(this.cam, this.renderContext);
        this.shader.render(this.renderable);
        this.shader.end();
        this.renderContext.end();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        this.shader.dispose();
        this.model.dispose();
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
