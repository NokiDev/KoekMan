package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Clyde extends Ghost{
    public Clyde(final TextureAtlas atlas){
        setName("Clyde");
        setAtlas(atlas);
        setAnimation("walk");
        setPosition(12, 22);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
