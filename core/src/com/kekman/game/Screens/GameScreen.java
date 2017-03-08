package com.kekman.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kekman.game.KekMan;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Keyboard.DirectionHandler;
import com.kekman.game.Tools.Keyboard.Keyboard;
import com.kekman.game.Tools.Ternary;

public class GameScreen extends ScreenAdapter {
	private final KekMan game = (KekMan) Gdx.app.getApplicationListener();
	private final AssetManager assetManager = game.getAssetManager();
	private final Viewport viewport = game.getViewport();
	private final SpriteBatch batch = game.getBatch();

	private GameMap 	mMap;

	OrthographicCamera	mCamera;
	Keyboard			mKeyboard;
	DirectionHandler 	mDirectionHandler;

	@Override
	public void show() {
		mCamera = new OrthographicCamera();
		mKeyboard = new Keyboard();
		mDirectionHandler = new DirectionHandler(new DirectionHandler.ADirectionListener() {
			@Override
			public Ternary onUp() {
				mMap.goPlayerUp();
				return new Ternary(Ternary.TRUE);
			}

			@Override
			public Ternary onDown() {
				mMap.goPlayerDown();
				return new Ternary(Ternary.TRUE);
			}

			@Override
			public Ternary onLeft() {
				mMap.goPlayerLeft();
				return new Ternary(Ternary.TRUE);
			}

			@Override
			public Ternary onRight() {
				mMap.goPlayerRight();
				return new Ternary(Ternary.TRUE);
			}
		});

		mMap = new GameMap(assetManager, viewport);
		mMap.setCamera(mCamera);
		mKeyboard.setInputProcessor();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (mDirectionHandler.applyDirection(mKeyboard).is(Ternary.FALSE))
			System.out.println("Wrong key");
		mMap.render(delta, batch);
	}
}