package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Inky extends Ghost{
    public Inky(final TextureAtlas atlas){
        setName("Inky");
        setAtlas(atlas);
        setAnimation("walk");
        setPosition(22, 12);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
