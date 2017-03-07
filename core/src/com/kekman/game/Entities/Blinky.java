package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Tools.Keyboard.DirectionHandler;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Blinky extends Ghost{
    public Blinky(final TextureAtlas atlas){
        setName("blinky");
        setAtlas(atlas);
        setAnimation("walk_up");
        setPosition(200, 32);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    @Override
    protected void directionChanged() {
        int direction = getDirection();
        switch (direction) {
            case DirectionHandler.UP:
                setAnimation("walk_up");
                break;
            case DirectionHandler.DOWN:
                setAnimation("walk_down");
                break;
            case DirectionHandler.LEFT:
                setAnimation("walk_left");
                break;
            case DirectionHandler.RIGHT:
                setAnimation("walk_right");
                break;
            default:
                break;
        }
    }
    @Override
    public void setAtlas(final TextureAtlas atlas) {
        super.setAtlas(atlas);
    }
}
