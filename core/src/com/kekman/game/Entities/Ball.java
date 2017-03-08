package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Bonus;
import com.kekman.game.Entities.Definitions.Required;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Ball extends Required {
    static public final String  name = "dot";
    static public final int     value = 10;

    public Ball(final TextureAtlas atlas, int tileX, int tileY){
        super(name, atlas, tileX, tileY, value);
    }
}
