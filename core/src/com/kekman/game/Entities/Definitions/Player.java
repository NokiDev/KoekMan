package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;

/**
 * Created by elytum on 07/03/2017.
 */

public class Player extends LivingEntity {
    boolean mInvincible = false;
    int     mInvincibilityDelay = 0;

    private void setInvincible(boolean invincible) {
        if (mInvincible == invincible)
            return;
        System.out.println("Invincible: "+invincible);
        mInvincible = invincible;
        if (!mInvincible)
            mInvincibilityDelay = 0;
    }

    public void setInvincible(int delay) {
        if (delay > mInvincibilityDelay) {
            mInvincibilityDelay = delay;
            setInvincible(true);
        }
    }

    protected Player(final String name, final TextureAtlas atlas, int tileX, int tileY) {
        super(name,atlas, tileX, tileY);
        setSpeed(getSpeed() * 1.2f);
    }
    public void actorAdded(final Stage stage) {setZIndex(PLAYER_ZINDEX);}

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
        CollisionDetector.applyCollision(GameMap.getEntities(), this);
    }

    @Override
    public void onCollision(final Entity collider) {
        super.onCollision(collider);
        if (collider instanceof Enemy) {
            if (mInvincible)
                ((Enemy) collider).die();
            else
                die();
        }
    }

    @Override
    public void onDie() {
        setSpeed(0);
        setAnimation("die", true);
        Logic.loose();
    }

    @Override
    protected void directionChanged() {
        if (alive)
            super.directionChanged();
    }
}
