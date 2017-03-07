package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Entity {
    private final int DEFAULT_WIDTH = 32;
    private final int DEFAULT_HEIGHT = 32;

    public Pacman(final TextureAtlas atlas) {
        setName("pacman");
        setAtlas(atlas);
        setAnimation("walk");
        setPosition(32, 32);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void setAtlas(final TextureAtlas atlas) {
        super.setAtlas(atlas);
    }
}
