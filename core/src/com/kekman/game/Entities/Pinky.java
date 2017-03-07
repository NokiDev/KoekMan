package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Pinky extends com.kekman.game.Entities.Definitions.Ghost {
    public Pinky(final TextureAtlas atlas){
        super("pinky", atlas);
        setCell(1, 1);
    }
}
