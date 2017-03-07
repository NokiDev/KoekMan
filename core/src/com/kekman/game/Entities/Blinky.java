package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Ghost;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Blinky extends Ghost {
    public Blinky(final TextureAtlas atlas){
        super("blinky", atlas);
        setCell(1, 1);
    }
}
