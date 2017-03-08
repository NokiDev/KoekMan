package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;

/**
 * Created by elytum on 07/03/2017.
 */

public class Bonus extends StaticEntity {
    protected Bonus(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}
    public void actorAdded(final Stage stage) {setZIndex(BONUS_ZINDEX);}

    public void applyBonus(final Entity entity) {

    }
}
