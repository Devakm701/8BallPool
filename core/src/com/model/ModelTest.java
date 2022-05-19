package com.model;

import java.util.stream.StreamSupport;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;


public class ModelTest implements ApplicationListener {
    private Environment environment;
    private PerspectiveCamera camera;
    private CameraInputController cameraController;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance instance;
    private ShapeRenderer shapeRenderer;
    int i = 0;


    @Override
    public void create() {
        // Create an environment so we have some lighting
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        // environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        // Create a perspective camera with some sensible defaults
        camera = new PerspectiveCamera(15, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 0, 400);
        camera.lookAt(0, 0, -10);
        camera.near = 1f;
        camera.far = 800f;
        camera.update();

        // Import and instantiate our model (called "myModel.g3dj")
        ModelBuilder modelBuilder = new ModelBuilder();
        model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("ball.g3dj"));
        instance = new ModelInstance(model);
        instance.transform.scale(0.01f, 0.01f, 0.01f);

        shapeRenderer = new ShapeRenderer();

        cameraController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraController);

    }

    @Override
    public void render() {
        cameraController.update();
        instance.transform.setTranslation(-4f, 0, 0);
        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        instance.transform.setToRotation(new Vector3(0,1,0), ++i);
        // Let our ModelBatch take care of efficient rendering of our ModelInstance
        modelBatch.begin(camera);
        modelBatch.render(instance, environment);
        for (int i = 1; i <= 40; i++) {
            instance.transform.setTranslation(2f * i, 0, 0);
            modelBatch.render(instance);
        }
        System.out.println(Gdx.graphics.getFramesPerSecond());
        modelBatch.end();
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(0, 0, 255, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 10);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    // SpriteBatch batch;
    // TextureRegion one;
    // Image oneImg;

    // @Override
    // public void create() {
    //     one = new TextureRegion(new Texture(Gdx.files.internal("img/one.jpg")));
    //     // oneImg = new Image(one);
    //     // oneImg.setOrigin(oneImg.getWidth() / 2, oneImg.getHeight() / 2);
    //     // oneImg.setPosition(stage.getWidth() / 2-32 , stage.getHeight()/2);
    //     // oneImg.setScale(2f,2f);
    //     // oneImg.addAction(rotateBy(360, 0.5f));
    // }

    // @Override
    // public void render() {
        
    //     SpriteBatch.draw(one.getTexture(), 500, 500, 0, 0, one.getRegionWidth(), one.getRegionWidth(), 1, 1, rotation, srcX, srcX, srcWidth, srcHeight, false, false);
    // }

    // @Override
    // public void dispose() {

    // }

    // @Override
    // public void resize(int width, int height) { }

    // @Override
    // public void pause() { }

    // @Override
    // public void resume() { }
}
