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
import com.kekman.game.Entities.Pacman;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;
import com.kekman.game.Tools.Renderers.TextureMapObjectRenderer;

/**
 * Created by elytum on 06/03/2017.
 */

public class GameMap extends Stage {
    private final AssetManager          mManager;
    private OrthographicCamera          mCamera;
    private TiledMapTileLayer           mLayer;
    private TiledMap                    mTiledMap;
    private Pacman                      mPacman;

    private TiledMapRenderer tiledMapRenderer;


    public GameMap(final AssetManager manager, final Viewport viewport) {
        super(viewport);
        mManager = manager;
        loadAssets();
    }

    public GameMap(final AssetManager manager) {
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
        addActor(mPacman);
    }

    public void render(float delta, final SpriteBatch batch) {
        act(delta);
        render(batch);
    }

    public void render(final SpriteBatch batch) {
        if (mCamera == null)
            return;
//        CollisionDetector.checkCollision(mTiledMap, mPacman);
        System.out.println(CollisionDetector.checkCollision(mTiledMap, mPacman));

        tiledMapRenderer.render();
        draw();
//        batch.begin();
//        mPacman.draw(batch, .5f);
//        batch.end();
    }

    public void act(float delta) {
        mPacman.act(Gdx.graphics.getDeltaTime());
    }
}
