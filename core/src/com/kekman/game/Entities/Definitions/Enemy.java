package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;
import com.kekman.game.Tools.Keyboard.DirectionHandler;
import com.kekman.game.Tools.Random.RandomUtils;

/**
 * Created by elytum on 07/03/2017.
 */

public class Enemy extends MovingEntity {
    protected Enemy(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}
    public void actorAdded(final Stage stage) {setZIndex(ENEMY_ZINDEX);}

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

    @Override
    public void onCollision(boolean upAvailable, boolean downAvailable,
                               boolean leftAvailable, boolean rightAvailable) {
        changeRandomDirection(upAvailable, downAvailable, leftAvailable, rightAvailable);
    }

    @Override
    public void onIntersection(boolean upAvailable, boolean downAvailable,
                               boolean leftAvailable, boolean rightAvailable) {
        changeRandomDirection(upAvailable, downAvailable, leftAvailable, rightAvailable);
    }
}
