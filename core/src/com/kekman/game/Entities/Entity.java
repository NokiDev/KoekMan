package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Animations.Animations;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;
import com.kekman.game.Tools.Keyboard.DirectionHandler;

/**
 * Created by elytum on 06/03/2017.
 */

public class Entity extends Actor {
    private Animations  mAnimations;
    private int         mDirection = DirectionHandler.UP;
    private int         mSpeed = 5;

    public String toString() {
        return super.toString()+" -> "+mAnimations;
    }

    protected void setAtlas(final TextureAtlas atlas) {
        mAnimations = new Animations(atlas, getName());
    }

    protected void setAnimation(final String animation) {
        mAnimations.setAnimation(animation);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (mAnimations != null)
            mAnimations.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (mAnimations != null)
            mAnimations.act(delta);
        final TiledMap tiledMap = GameMap.getTilesMap();
        if (tiledMap != null) {
            boolean flag = canGoDirection(tiledMap, mDirection, mSpeed);
            if (!flag)
                mDirection = DirectionHandler.UNKNOWN;
        }
        switch (mDirection) {
            case DirectionHandler.UP:
                moveBy(0, mSpeed);
                break;
            case DirectionHandler.DOWN:
                moveBy(0, -mSpeed);
                break;
            case DirectionHandler.LEFT:
                moveBy(-mSpeed, 0);
                break;
            case DirectionHandler.RIGHT:
                moveBy(mSpeed, 0);
                break;
            default:
                break;
        }
    }

    @Override
    protected void	positionChanged() {
        super.positionChanged();
        mAnimations.setPosition(getX(), getY());
    }

    @Override
    protected void	sizeChanged() {
        super.sizeChanged();
        mAnimations.setSize(getWidth(), getHeight());
    }

    private boolean canGo(final TiledMap map, float moveX, float moveY) {
        moveBy(moveX, moveY);
        Object hit = CollisionDetector.checkCollision(map, this);
        moveBy(-moveX, -moveY);
        return hit == null;
    }

    public boolean canGoUp(final TiledMap map, float speed) {
        return canGo(map, 0, speed);
    }

    public boolean canGoDown(final TiledMap map, float speed) {
        return canGo(map, 0, -speed);
    }

    public boolean canGoLeft(final TiledMap map, float speed) {
        return canGo(map, -speed, 0);
    }

    public boolean canGoRight(final TiledMap map, float speed) {
        return canGo(map, speed, 0);
    }

    public boolean canGoDirection(final TiledMap map, int direction, float speed) {
        switch (direction) {
            case DirectionHandler.UP:
                return canGoUp(map, speed);
            case DirectionHandler.DOWN:
                return canGoDown(map, speed);
            case DirectionHandler.LEFT:
                return canGoLeft(map, speed);
            case DirectionHandler.RIGHT:
                return canGoRight(map, speed);
            default:
                return false;
        }
    }
}
