package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Map.GameMap;

/**
 * Created by elytum on 08/03/2017.
 */

public class Required extends Bonus {
    public Required(final String name, final TextureAtlas atlas, int tileX, int tileY, int value){
        super(name, atlas, tileX, tileY, value);
    }

    @Override
    protected void bonusRemoved() {
        GameMap.checkWin();
    }
}
