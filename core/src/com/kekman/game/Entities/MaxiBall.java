package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Bonus;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class MaxiBall extends Bonus {
    static public final String  name = "clyde";
    static public final int     value = 50;

    public MaxiBall(final TextureAtlas atlas, int tileX, int tileY) {
        super("blinky", atlas, tileX, tileY, value);
    }
}
