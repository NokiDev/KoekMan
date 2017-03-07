package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Pinky extends Ghost{
    public Pinky(final TextureAtlas atlas){
        setName("Pinky");
        setAtlas(atlas);
        setAnimation("walk");
        setPosition(15, 28);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
