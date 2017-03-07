package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Bonus;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Ball extends Bonus {
    static public final String name = "ball";

    public Ball(final TextureAtlas atlas, int tileX, int tileY){
        super("blinky", atlas, tileX, tileY);
    }
}
