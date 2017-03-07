package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Pinky extends com.kekman.game.Entities.Definitions.Ghost {
    public Pinky(final TextureAtlas atlas){
        setName("pinky");
        setAtlas(atlas);
        setAnimation("walk_up");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setCell(1, 1);
    }
}
