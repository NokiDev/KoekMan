package com.kekman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.kekman.game.Entities.Pacman;

public class GameScreen extends ScreenAdapter implements InputProcessor {
	private final KekMan game = (KekMan) Gdx.app.getApplicationListener();
	private final AssetManager assetManager = game.getAssetManager();
	private final Batch batch = game.getBatch();

	Texture img;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	TiledMapTileLayer layer;
	Pacman pacman;

	@Override
	public void show() {
		tiledMap = assetManager.get("map.tmx", TiledMap.class);
		pacman = new Pacman(assetManager.get("sprites.txt", TextureAtlas.class));
		layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
		int w = layer.getWidth();
		int h = layer.getHeight();
		System.out.print(w + "+" + h);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w*32,h*32);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		tiledMapRenderer.setView(camera);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/* Replace with stage rendering */
		tiledMapRenderer.render();
		pacman.act(Gdx.graphics.getDeltaTime());
		batch.begin();
		pacman.draw(batch, .5f);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
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
	public boolean scrolled(int amount) {
		return false;
	}
}