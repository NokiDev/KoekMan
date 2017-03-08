package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Map.GameMap;

/**
 * Created by elytum on 08/03/2017.
 */

public class LivingEntity extends MovingEntity {
    private boolean mInvincible = false;
    private float   mInvincibilityDelay = 0;

    public boolean isInvincible() {return mInvincible;}

    private void setInvincible(boolean invincible) {
        if (mInvincible == invincible)
            return;
        mInvincible = invincible;
        if (!mInvincible)
            mInvincibilityDelay = 0;
        if (this instanceof Player) {
            GameMap.invinciblePlayer(mInvincible);
        }
    }

    public void setInvincible(float delay) {
        if (delay > mInvincibilityDelay) {
            mInvincibilityDelay = delay;
            setInvincible(true);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (mInvincible) {
            mInvincibilityDelay -= delta;
            if (mInvincibilityDelay < 0) {
                setInvincible(false);
                mInvincibilityDelay = 0;
            }
        }
    }

    @Override
    protected void directionChanged() {
        if (alive)
            super.directionChanged();
    }

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
