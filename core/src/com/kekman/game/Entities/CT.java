package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Bonus;
import com.kekman.game.Entities.Definitions.Entity;
import com.kekman.game.Entities.Definitions.Player;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class CT extends Bonus {
    static public final String  name = "ct";
    static public final int     value = 100;
    static public final float   invicibilityDelay = 5;

    public CT(final TextureAtlas atlas, int tileX, int tileY) {
        super(name, atlas, tileX, tileY, value);
    }

    @Override
    public void applyBonus(final Entity entity) {
        if (entity instanceof Player) {
            ((Player) entity).setInvincible(invicibilityDelay);
        }
        super.applyBonus(entity);
    }
}
