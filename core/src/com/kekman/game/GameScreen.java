package com.kekman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Pacman;

public class GameScreen extends ScreenAdapter {

    private final LoadingScreenGame game = (LoadingScreenGame) Gdx.app.getApplicationListener();
    private final AssetManager assetManager = game.getAssetManager();
    private final Batch batch = game.getBatch();

    private Texture splash;

    Pacman pacman;

    @Override
    public void show() {
        super.show();
        System.out.println("LOADED");
        splash = assetManager.get("splash.png");

        pacman = new Pacman(assetManager.get("sprites.txt", TextureAtlas.class));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        pacman.act(delta);

        batch.begin();
//        batch.draw(splash, 0, 0);
        pacman.draw(batch, .5f);
        batch.end();
    }
}