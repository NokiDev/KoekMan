package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.kekman.game.KekMan;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;
import com.kekman.game.Tools.Keyboard.DirectionHandler;

/**
 * Created by elytum on 07/03/2017.
 */

public class MovingEntity extends Entity {
    private int         mDirection = DirectionHandler.UP;
    private int         mNextDirection = DirectionHandler.UNKNOWN;
    private int         mSpeed = 5;


    @Override
    public void act(float delta) {
        super.act(delta);
        final TiledMap tiledMap = GameMap.getTilesMap();
        if (tiledMap != null) {
            if (canGoDirection(tiledMap, mNextDirection, mSpeed)) {
                changeDirection(mNextDirection);
                mNextDirection = DirectionHandler.UNKNOWN;
            }
            else if (!canGoDirection(tiledMap, mDirection, mSpeed)) {
                changeDirection(DirectionHandler.UNKNOWN);
                mNextDirection = DirectionHandler.UNKNOWN;
            }
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
        if (getX()> 700){
            setX(0);
        }
        else if (getX()< 0){
            setX(700);
        }
        if (getY() - getHeight() / 2 > KekMan.WORLD_WIDTH)
            setY(getY() - KekMan.WORLD_HEIGHT);
        else if (getY() - getHeight() / 2 < 0)
            setY(getY() + KekMan.WORLD_HEIGHT);
    }

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

    protected int getDirection() {return mDirection;}

    private void changeDirection(final int direction) {
        mDirection = direction;
        directionChanged();
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

    public boolean setDirectionUp() {
        final TiledMap map = GameMap.getTilesMap();
        if (map != null && !canGoUp(map, mSpeed))
            mNextDirection = DirectionHandler.UP;
        else
            changeDirection(DirectionHandler.UP);
        return true;
    }

    public boolean setDirectionDown() {
        final TiledMap map = GameMap.getTilesMap();
        if (map != null && !canGoDown(map, mSpeed))
            mNextDirection = DirectionHandler.DOWN;
        else
            changeDirection(DirectionHandler.DOWN);
        return true;
    }

    public boolean setDirectionLeft() {
        final TiledMap map = GameMap.getTilesMap();
        if (map != null && !canGoLeft(map, mSpeed))
            mNextDirection = DirectionHandler.LEFT;
        else
            changeDirection(DirectionHandler.LEFT);
        return true;
    }

    public boolean setDirectionRight() {
        final TiledMap map = GameMap.getTilesMap();
        if (map != null && !canGoRight(map, mSpeed))
            mNextDirection = DirectionHandler.RIGHT;
        else
            changeDirection(DirectionHandler.RIGHT);
        return true;
    }
}
