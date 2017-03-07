package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Entity {
    private final int DEFAULT_WIDTH = 28;
    private final int DEFAULT_HEIGHT = 28;

    public Pacman(final TextureAtlas atlas) {
//        setName("pacman");
        setName("ghosts/blue");
        setAtlas(atlas);
        setAnimation("walk_right");
        setPosition(32, 32);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void setAtlas(final TextureAtlas atlas) {
        super.setAtlas(atlas);
    }
}
