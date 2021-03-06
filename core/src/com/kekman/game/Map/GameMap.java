package com.kekman.game.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kekman.game.Entities.Ball;
import com.kekman.game.Entities.Blinky;
import com.kekman.game.Entities.CT;
import com.kekman.game.Entities.Clyde;
import com.kekman.game.Entities.Definitions.Bonus;
import com.kekman.game.Entities.Definitions.Enemy;
import com.kekman.game.Entities.Definitions.Entity;
import com.kekman.game.Entities.Definitions.Required;
import com.kekman.game.Entities.Inky;
import com.kekman.game.Entities.LibgdxExtended.Stage;
import com.kekman.game.Entities.Pacman;
import com.kekman.game.Entities.Pinky;
import com.kekman.game.Tools.Random.RandomUtils;
import com.kekman.game.Tools.Renderers.TextureMapObjectRenderer;
import com.kekman.game.Tools.Score.Logic;
import com.kekman.game.Tools.Tilemap.TilemapUtils;

/**
 * Created by elytum on 06/03/2017.
 */

public class GameMap extends Stage {
    private static GameMap              instance;
    private final AssetManager          mManager;
    private OrthographicCamera          mCamera;
    private TiledMapTileLayer           mLayer;
    private TiledMap                    mTiledMap;
    private boolean[][]                 mMapColliders;
    private boolean[][]                 mMapUnavailable;

    private Array<Entity>               mEntities = new Array<Entity>();
    private Pacman                      mPacman;
//    private Blinky                      mBlinky;
//    private Inky                        mInky;
//    private Pinky                       mPinky;
//    private Clyde                       mClyde;
//    private Ball[]                      mballs;

    private TiledMapRenderer tiledMapRenderer;

    public static void checkWin() {
        if (instance == null)
            return;
        for (int i=0; i < instance.mEntities.size; i++) {
            final Entity entity = instance.mEntities.get(i);
            if (entity instanceof Required)
                return;
        }
        Logic.win();
    }

    public static GameMap getInstance() {
        return instance;
    }

    public static TiledMap getTilesMap() {
        if (instance == null)
            return null;
        return instance.mTiledMap;
    }

    public static Array<Entity> getEntities() {
        if (instance == null)
            return null;
        return instance.mEntities;
    }

    public static void removeEntity(final Entity entity) {
        if (instance == null)
            return;
        instance.mEntities.removeValue(entity, true);
        if (entity instanceof Bonus)
            instance.mMapUnavailable[entity.getCellY()][entity.getCellX()] = false;
    }

    public static float getTileWidth() {
        if (instance == null)
            return 0;
        return TilemapUtils.getTileWidth(instance.mTiledMap);
    }

    public static float getTileHeight() {
        if (instance == null)
            return 0;
        return TilemapUtils.getTileHeight(instance.mTiledMap);
    }

    public static boolean isColliding(int cellX, int cellY) {
        if (instance == null)
            return false;
        return !instance.mMapColliders[cellY][cellX];
    }

    public static boolean isColliding(final Entity entity) {
        if (instance == null)
            return false;
        boolean colliders[][] = instance.mMapColliders;

        float tileWidth = getTileWidth();
        float tileHeight = getTileHeight();
        float x = entity.getX();
        int   tileX = (int)(x / tileWidth);
        float y = entity.getY();
        int   tileY = (int)(y / tileHeight);
        float width = entity.getWidth() + (x % tileWidth);
        float height = entity.getHeight() + (y % tileHeight);

        int posX = tileX - 1;
        for (float w = width; w > 0; w -= tileWidth) {
            ++posX;
            if (posX < 0)
                continue;
            int posY = tileY - 1;
            for (float h = height; h > 0; h -= tileHeight) {
                ++posY;
                if (posY < 0 || posY >= colliders.length || posX >= colliders[posY].length)
                    continue;
                if (colliders[posY][posX])
                    return true;
            }
        }
        return false;
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

    public void addEntity(final Entity entity) {
        mEntities.add(entity);
        addActor(entity);
        if (entity instanceof Bonus)
            mMapUnavailable[entity.getCellY()][entity.getCellX()] = true;
    }


    public void fillWithDots() {
        for (int y = 0; y < mMapColliders.length; y++) {
            boolean yArray[] = mMapColliders[y];
            int x = 0;
            for (boolean xValue: yArray) {
                if (!xValue)
                    addEntity(new Ball(mManager.get("sprites.txt", TextureAtlas.class), x, y));
                ++x;
            }
        }
    }

//    protected void onResize(int width, int height, final Viewport viewport) {
//        setCamera(v);
//        tiledMapRenderer.setView(viewport.getCamera());
//    }

    private void cameraUpdated() {
        int w = mLayer.getWidth();
        int h = mLayer.getHeight();
        mCamera.setToOrtho(false,w*32,h*32);

        tiledMapRenderer = new TextureMapObjectRenderer(mTiledMap);//new OrthogonalTiledMapRenderer(mTiledMap);
        tiledMapRenderer.setView(mCamera);

        getViewport().setCamera(mCamera);
    }

    private void setAvailableCells() {
        mMapUnavailable = TilemapUtils.getEmptyCells(mTiledMap);
        for (int y = 0; y < mMapColliders.length; y++) {
            boolean yArray[] = mMapColliders[y];
            int x = 0;
            for (boolean xValue: yArray) {
                mMapUnavailable[y][x] = xValue;
                ++x;
            }
        }
    }

    private void loadAssets() {
        if (mTiledMap != null)
            mTiledMap.dispose();
        mTiledMap = mManager.get("map.tmx", TiledMap.class);
        mMapColliders = TilemapUtils.getEmptyCells(mTiledMap);
        mMapUnavailable = new boolean[mMapColliders.length][mMapColliders[0].length];
        setAvailableCells();
        mLayer = (TiledMapTileLayer)mTiledMap.getLayers().get(0);
        mPacman = new Pacman(mManager.get("sprites.txt", TextureAtlas.class), 11, 11);
        addEntity(mPacman);
        addEntity(new Pinky(mManager.get("sprites.txt", TextureAtlas.class), 21, 1));
        addEntity(new Blinky(mManager.get("sprites.txt", TextureAtlas.class), 1, 1));
        addEntity(new Clyde(mManager.get("sprites.txt", TextureAtlas.class), 1, 21));
        addEntity(new Inky(mManager.get("sprites.txt", TextureAtlas.class), 21, 21));
        fillWithDots();
    }

    public int[] randomEmptyCell() {
        return TilemapUtils.randomEmptyCell(mMapColliders);
    }
    public int[] randomAvailableCell() {
        return TilemapUtils.randomEmptyCell(mMapUnavailable);
    }

    public void render(float delta, final SpriteBatch batch) {
        act(delta);
        render(batch);
    }

    public void spawnRandomBonus() {
        int[] test = randomAvailableCell();
        if (test != null) {
            Entity test2 = new CT(mManager.get("sprites.txt", TextureAtlas.class), test[0], test[1]);
            mEntities.add(test2);
            addActor(test2);
        }
    }

    public void render(final SpriteBatch batch) {
        if (mCamera == null)
            return;
        tiledMapRenderer.render();
        draw();
    }

    float timeSinceLastEvent = 0;
    @Override
    public void act(float delta) {
        super.act(delta);
        timeSinceLastEvent += delta;
        while (timeSinceLastEvent > 1) {
            --timeSinceLastEvent;
            if (RandomUtils.randInt(10) == 0)
                spawnRandomBonus();
        }
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

    public void setEnemiesWeak(boolean weak) {
        for (int i=0; i < mEntities.size; i++) {
            final Entity entity = mEntities.get(i);
            if (entity instanceof Enemy)
                ((Enemy) entity).setWeak(weak);
        }
    }

    int mTotalInvincible;
    public static void invinciblePlayer(boolean invincible) {
        if (instance == null)
            return;
        if (invincible) {
            ++instance.mTotalInvincible;
            instance.setEnemiesWeak(true);
        } else {
            if (--instance.mTotalInvincible == 0)
                instance.setEnemiesWeak(false);
        }
    }
}
