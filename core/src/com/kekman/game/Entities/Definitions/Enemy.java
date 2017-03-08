package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;

/**
 * Created by elytum on 07/03/2017.
 */

public class Enemy extends MovingEntity {
    protected Enemy(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}
    public void actorAdded(final Stage stage) {setZIndex(ENEMY_ZINDEX);}
}
