package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Ghost;
import com.kekman.game.Tools.Keyboard.DirectionHandler;
import com.kekman.game.Tools.Random.RandomUtils;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Blinky extends Ghost {
    static public final String name = "blinky";

    public Blinky(final TextureAtlas atlas, int tileX, int tileY) {
        super(name, atlas, tileY, tileY);
    }
}
