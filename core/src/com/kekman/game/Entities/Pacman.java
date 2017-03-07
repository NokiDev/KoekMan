package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;
import com.kekman.game.Tools.Keyboard.DirectionHandler;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Entity {
    private final int DEFAULT_WIDTH = 28;
    private final int DEFAULT_HEIGHT = 28;

    public Pacman(final TextureAtlas atlas) {
        setName("pacman");
        setAtlas(atlas);
        setAnimation("walk_right");
        setPosition(32, 32);
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

    @Override
    public void onCollision(final Entity collider) {
//        System.out.println("Colliding with: "+collider);
        if (collider instanceof Enemy) {
//            System.out.println("DIE");
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        CollisionDetector.applyCollision(GameMap.getEntities(), this);
    }
}
