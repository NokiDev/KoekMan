package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Ghost;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Clyde extends Ghost {

    public Clyde(final TextureAtlas atlas){
        super("clyde", atlas);
        setCell(1, 1);
    }
}
