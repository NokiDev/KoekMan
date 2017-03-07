package com.kekman.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kekman.game.Entities.Ball;
import com.kekman.game.Entities.Blinky;
import com.kekman.game.Entities.Clyde;
import com.kekman.game.Entities.Inky;
import com.kekman.game.Entities.Pacman;
import com.kekman.game.Entities.Pinky;
import com.kekman.game.Tools.Renderers.TextureMapObjectRenderer;

/**
 * Created by elytum on 06/03/2017.
 */

public class GameMap extends Stage {
    private static GameMap              instance;
    private final AssetManager          mManager;
    private OrthographicCamera          mCamera;
    private TiledMapTileLayer           mLayer;
    private TiledMap                    mTiledMap;
    private Pacman                      mPacman;
    private Blinky                      mBlinky;
    private Inky                        mInky;
    private Pinky                       mPinky;
    private Clyde                       mClyde;
    private Ball[]                      mballs;

    private TiledMapRenderer tiledMapRenderer;

    public static GameMap getInstance() {
        return instance;
    }

    public static TiledMap getTilesMap() {
        if (instance == null)
            return null;
        return instance.mTiledMap;
    }

    public GameMap(final AssetManager manager, final Viewport viewport) {
        super(viewport);
        instance = this;
        mManager = manager;
        loadAssets();
    }

    public GameMap(final AssetManager manager) {
        instance = this;
        mManager = manager;
        loadAssets();
    }

    public void setCamera(final OrthographicCamera camera) {
        mCamera = camera;
        cameraUpdated();
    }

    private void cameraUpdated() {
        int w = mLayer.getWidth();
        int h = mLayer.getHeight();
        System.out.print(w + "+" + h);
        mCamera.setToOrtho(false,w*32,h*32);

        tiledMapRenderer = new TextureMapObjectRenderer(mTiledMap);//new OrthogonalTiledMapRenderer(mTiledMap);
        tiledMapRenderer.setView(mCamera);

        getViewport().setCamera(mCamera);
    }

    private void loadAssets() {
        if (mTiledMap != null)
            mTiledMap.dispose();
        mTiledMap = mManager.get("map.tmx", TiledMap.class);
        mLayer = (TiledMapTileLayer)mTiledMap.getLayers().get(0);

        mPacman = new Pacman(mManager.get("sprites.txt", TextureAtlas.class));
        mPinky = new Pinky(mManager.get("sprites.txt", TextureAtlas.class),mPacman);
        mBlinky = new Blinky(mManager.get("sprites.txt", TextureAtlas.class), mPacman);
        mClyde = new Clyde(mManager.get("sprites.txt", TextureAtlas.class), mPacman);
        mInky = new Inky(mManager.get("sprites.txt", TextureAtlas.class), mPacman);
        addActor(mPacman);
        addActor(mPinky);
        addActor(mBlinky);
        addActor(mClyde);
        addActor(mInky);
    }

    public void render(float delta, final SpriteBatch batch) {
        act(delta);
        render(batch);
    }

    public void render(final SpriteBatch batch) {
        if (mCamera == null)
            return;
        tiledMapRenderer.render();
        draw();
    }

    public void act(float delta) {
        mPacman.act(Gdx.graphics.getDeltaTime());
        mBlinky.act(Gdx.graphics.getDeltaTime());
        mPinky.act(Gdx.graphics.getDeltaTime());
        mInky.act(Gdx.graphics.getDeltaTime());
        mClyde.act(Gdx.graphics.getDeltaTime());
    }

    public void goPlayerUp() {
        mPacman.setDirectionUp();
    }

    public void goPlayerDown() {
        mPacman.setDirectionDown();
    }

    public void goPlayerLeft() {
        mPacman.setDirectionLeft();
    }

    public void goPlayerRight() {
        mPacman.setDirectionRight();
    }
}
