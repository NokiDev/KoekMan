package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Bonus;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Ball extends Bonus {
    public Ball(final TextureAtlas atlas){
        setName("blinky");
        setAtlas(atlas);
        setAnimation("static");
        setPosition(200, 32);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
