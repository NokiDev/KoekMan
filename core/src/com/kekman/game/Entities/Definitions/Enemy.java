package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Keyboard.DirectionHandler;
import com.kekman.game.Tools.Random.RandomUtils;

/**
 * Created by elytum on 07/03/2017.
 */

public class Enemy extends LivingEntity {
    protected Enemy(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}
    public void actorAdded(final Stage stage) {setZIndex(ENEMY_ZINDEX);}

    private boolean mWeak = false;

    public boolean  isWeak() {return mWeak;}
    public void     setWeak(boolean weak) {
        if (mWeak == weak)
            return;
        mWeak = weak;
        if (weak)
            setAnimation("weak", true);
    }

    @Override
    public void setAnimation(final String animation) {
        if (mWeak && !animation.equals("weak"))
            return;
        super.setAnimation(animation);
    }

    protected void changeRandomDirection(boolean upAvailable, boolean downAvailable,
                                         boolean leftAvailable, boolean rightAvailable) {
        int possibilities = (upAvailable ? 1 : 0) + (downAvailable ? 1 : 0) +
                (leftAvailable ? 1 : 0) + (rightAvailable ? 1 : 0);
        int nextDirection = RandomUtils.randInt(possibilities - 1);
        if (upAvailable && nextDirection-- == 0)
            changeDirection(DirectionHandler.UP);
        else if (downAvailable && nextDirection-- == 0)
            changeDirection(DirectionHandler.DOWN);
        else if (leftAvailable && nextDirection == 0)
            changeDirection(DirectionHandler.LEFT);
        else if (rightAvailable)
            changeDirection(DirectionHandler.RIGHT);
    }

    protected void changeNewRandomDirection(boolean upAvailable, boolean downAvailable,
                                            boolean leftAvailable, boolean rightAvailable, int direction) {
        switch (direction) {
            case DirectionHandler.UP:
                downAvailable = false;
                break;
            case DirectionHandler.DOWN:
                upAvailable = false;
                break;
            case DirectionHandler.LEFT:
                rightAvailable = false;
                break;
            case DirectionHandler.RIGHT:
                leftAvailable = false;
                break;
            default:
                break;
        }
        changeRandomDirection(upAvailable, downAvailable, leftAvailable, rightAvailable);
    }

    @Override
    public void onCollision(boolean upAvailable, boolean downAvailable,
                               boolean leftAvailable, boolean rightAvailable, int direction) {
    }

    @Override
    public void onIntersection(boolean upAvailable, boolean downAvailable,
                               boolean leftAvailable, boolean rightAvailable, int direction) {
        changeNewRandomDirection(upAvailable, downAvailable, leftAvailable, rightAvailable, direction);
    }

    @Override
    protected void onDie() {
        remove();
        GameMap.removeEntity(this);
    }
}
