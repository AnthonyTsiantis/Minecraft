package com.libgdx.minecraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter implements InputProcessor {
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private ModelBuilder modelBuilder;
	private Model dirt, diamond;
	private ModelInstance dirtModelInstance, diamondModelInstance;
	private Environment environment;
	private Texture dirtTexture, diamondTexture;


	@Override
	public void create () {
		// Create Camera Object
		camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Set Camera position 3 blocks behind origin
		camera.position.set(0f, 0f, 3f);

		// Make the Camera look at the origin
		camera.lookAt(0f, 0f, 0f);

		// Set Camera View distance
		camera.near = 0.1f;
		camera.far = 300f;

		// Create Model Batch
		modelBatch = new ModelBatch();
		ModelBuilder modelBuilder = new ModelBuilder();
		dirtTexture = new Texture(Gdx.files.internal("Blocks/dirt.png"));
		dirt = modelBuilder.createBox(1f, 1f, 1f,
				new Material(TextureAttribute.createDiffuse(dirtTexture)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates
		);

		diamondTexture = new Texture(Gdx.files.internal("Blocks/diamond_block.png"));
		diamond = modelBuilder.createBox(1f, 1f, 1f,
				new Material(TextureAttribute.createDiffuse(diamondTexture)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates
		);


		// Create an instance of this model at origin
		dirtModelInstance = new ModelInstance(dirt, 0,0,0);
		diamondModelInstance = new ModelInstance(diamond, 1,0,0);

		// Add an ambient light
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1.0f, 1.0f, 1.0f, 1.0f));

		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		camera.update();
		modelBatch.begin(camera);
		modelBatch.render(dirtModelInstance, environment);
		modelBatch.render(diamondModelInstance, environment);
		modelBatch.end();
	}
	
	@Override
	public void dispose () {
		dirt.dispose();
		diamond.dispose();
		modelBatch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.LEFT) {
			// Fix this so you do not create a new Vector 3 everytime a key is pressed // TODO
			// Use a temporary static member instead
			camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), 10f);
		}
		if(keycode == Input.Keys.RIGHT) {
			// Fix this so you do not create a new Vector 3 everytime a key is pressed // TODO
			// Use a temporary static member instead
			camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), -10f);
		}
		if(keycode == Input.Keys.UP) {
			// Fix this so you do not create a new Vector 3 everytime a key is pressed // TODO
			// Use a temporary static member instead
			camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(1f, 0f, 0f), -10f);
		}

		if(keycode == Input.Keys.DOWN) {
			// Fix this so you do not create a new Vector 3 everytime a key is pressed // TODO
			// Use a temporary static member instead
			camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(1f, 0f, 0f), -10f);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
