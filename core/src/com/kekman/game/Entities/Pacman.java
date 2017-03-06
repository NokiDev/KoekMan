package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Entity {

    public Pacman(final TextureAtlas atlas) {
        setName("pacman");
        setAtlas(atlas);
        setAnimation("walk");
    }

    @Override
    public void setAtlas(final TextureAtlas atlas) {
        super.setAtlas(atlas);
    }
}
