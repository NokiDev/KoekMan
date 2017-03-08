package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Ghost;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Clyde extends Ghost {
    static public final String name = "clyde";

    public Clyde(final TextureAtlas atlas, int tileX, int tileY){
        super(name, atlas, tileX, tileY);
    }
}
