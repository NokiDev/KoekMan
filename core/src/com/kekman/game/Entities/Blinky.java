package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Blinky extends Ghost{
    public Blinky(final TextureAtlas atlas){
        setName("Blinky");
        setAtlas(atlas);
        setAnimation("walk");
        setPosition(22, 22);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    @Override
    public void setAtlas(final TextureAtlas atlas) {
        super.setAtlas(atlas);
    }
}
