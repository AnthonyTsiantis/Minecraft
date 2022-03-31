package com.libgdx.minecraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter implements InputProcessor {
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private Model dirt;
	private Environment environment;
	private Texture dirtTexture;
	private Vector3 desiredCamPos;
	private boolean keyDownW, keyDownA, keyDownS, keyDownD = false;
	private Array<ModelInstance> instances;
	private ModelBuilder modelBuilder;


	@Override
	public void create () {
		// Create Camera Object
		camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Set Camera position 3 blocks behind and 2 above origin
		camera.position.set(0f, 2f, 3f);
		desiredCamPos = new Vector3(0f,2f,3f);

		// Make the Camera look at the origin
		camera.lookAt(0f, 0f, 0f);

		// Set Camera View distance
		camera.near = 0.1f;
		camera.far = 300f;

		// Create Model Batch
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		dirtTexture = new Texture(Gdx.files.internal("Blocks/dirt.png"));
		instances = new Array<ModelInstance>();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = -11; k < 0; k++) {
					dirt = modelBuilder.createBox(1f, 1f, 1f,
							new Material(TextureAttribute.createDiffuse(dirtTexture)),
							VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates
					);
					instances.add(new ModelInstance(dirt, i, k, j));
				}
			}
		}

		// Add an ambient light
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1.0f, 1.0f, 1.0f, 1.0f));

		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
		camera.update();
		checkInput();
		modelBatch.begin(camera);
		for (int i = 0; i < instances.size; i++) {
			modelBatch.render(instances.get(i), environment);
		}
		modelBatch.end();
	}

	@Override
	public void dispose() {
		dirt.dispose();
		modelBatch.dispose();
	}

	public void checkInput() {
		if (keyDownA) {
			desiredCamPos.x -= 0.1f;
			camera.position.set(desiredCamPos);
		}

		if (keyDownW) {
			desiredCamPos.z -= 0.1f;
			camera.position.set(desiredCamPos);
		}

		if (keyDownD) {
			desiredCamPos.x += 0.1f;
			camera.position.set(desiredCamPos);
		}
		if (keyDownS) {
			desiredCamPos.z += 0.1f;
			camera.position.set(desiredCamPos);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.A) {
			keyDownA = true;
			return true;
		}

		if (keycode == Input.Keys.W) {
			keyDownW = true;
			return true;
		}

		if (keycode == Input.Keys.D) {
			keyDownD = true;
			return true;
		}
		if (keycode == Input.Keys.S) {
			keyDownS = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.A) {
			keyDownA = false;
			return true;
		}

		if (keycode == Input.Keys.W) {
			keyDownW = false;
			return true;
		}

		if (keycode == Input.Keys.D) {
			keyDownD = false;
			return true;
		}
		if (keycode == Input.Keys.S) {
			keyDownS = false;
			return true;
		}
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
