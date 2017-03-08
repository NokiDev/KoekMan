package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by elytum on 08/03/2017.
 */

public class LivingEntity extends MovingEntity {
    protected LivingEntity(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}

    boolean alive = true;

    protected void onDie() {}
    protected void onResurect() {}

    public void die() {
        if (!alive)
            return;
        alive = false;
        onDie();
    }

    public void resurect() {
        if (alive)
            return;
        alive = true;
        onResurect();
    }
}
