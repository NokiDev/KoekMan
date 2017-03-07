package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by elytum on 07/03/2017.
 */

public class StaticEntity extends Entity {
    protected StaticEntity(final String name, final TextureAtlas atlas) {
        super(name,atlas);
        setAnimation("static");
    }
}
