package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.KekMan;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Keyboard.DirectionHandler;

/**
 * Created by elytum on 07/03/2017.
 */

public class MovingEntity extends Entity {
    protected MovingEntity(final String name, final TextureAtlas atlas, int tileX, int tileY) {
        super(name,atlas, tileX, tileY);
        setAnimation("walk_up");
    }

    private int         mDirection = DirectionHandler.UP;
    private int         mNextDirection = DirectionHandler.UNKNOWN;
    private int         mSpeed = 100;

    boolean upWasAvailable = false;
    boolean downWasAvailable = false;
    boolean leftWasAvailable = false;
    boolean rightWasAvailable = false;
    private void moveEntity(float x, float y) {
        float tileWidth = GameMap.getTileWidth();
        float tileHeight = GameMap.getTileHeight();

        float posX = (getX() % tileWidth);
        float nextPosX = posX + x;
        float posY = (getY() % tileHeight);
        float nextPosY = posY + y;

        boolean onCenter = false;
        if (posX < tileWidth != nextPosX < tileWidth) {
            float offset = tileWidth - posX;
            setX(getX() + offset);
            x -= offset;
            onCenter = true;
        }
        if (posY < tileHeight != nextPosY < tileHeight) {
            float offset = tileHeight - posY;
            setY(getY() + offset);
            y -= offset;
            onCenter = true;
        }
        if (onCenter) {
            float remainingDistance = (float)Math.sqrt(x * x + y * y);
            boolean up = canGoUp(remainingDistance);
            boolean down = canGoDown(remainingDistance);
            boolean left = canGoLeft(remainingDistance);
            boolean right = canGoRight(remainingDistance);

            if ((!upWasAvailable && up) || (!downWasAvailable && down) ||
                    (!leftWasAvailable && left) || (!rightWasAvailable && right)) {
                onIntersection(up, down, left, right, mDirection);
            }
            upWasAvailable = up;
            downWasAvailable = down;
            leftWasAvailable = left;
            rightWasAvailable = right;
        }
        moveBy(x, y);
    }

    private void applyDirection(float speed) {
        switch (mDirection) {
            case DirectionHandler.UP:
                moveEntity(0, speed);
                break;
            case DirectionHandler.DOWN:
                moveEntity(0, -speed);
                break;
            case DirectionHandler.LEFT:
                moveEntity(-speed, 0);
                break;
            case DirectionHandler.RIGHT:
                moveEntity(speed, 0);
                break;
            default:
                break;
        }
        if (getX() > 700){
            setX(0);
        }
        else if (getX() < 0){
            setX(700);
        }
        if (getY() - getHeight() / 2 > KekMan.WORLD_WIDTH)
            setY(getY() - KekMan.WORLD_HEIGHT);
        else if (getY() - getHeight() / 2 < 0)
            setY(getY() + KekMan.WORLD_HEIGHT);
    }

    private float mLastDelta;
    @Override
    public void act(float delta) {
        super.act(delta);
        if (mDirection != DirectionHandler.UNKNOWN || mNextDirection != DirectionHandler.UNKNOWN) {
            if (canGoDirection(mNextDirection, mSpeed * delta)) {
                changeDirection(mNextDirection);
                mNextDirection = DirectionHandler.UNKNOWN;
            }
            else if (!canGoDirection(mDirection, mSpeed * delta)) {
                setCell(getCellX(), getCellY());
                int oldDirection = mDirection;
                changeDirection(mNextDirection);
                mNextDirection = DirectionHandler.UNKNOWN;
                onCollision(canGoUp(mSpeed * delta), canGoDown(mSpeed * delta),
                        canGoLeft(mSpeed * delta), canGoRight(mSpeed * delta), oldDirection);
                onIntersection(canGoUp(mSpeed * delta), canGoDown(mSpeed * delta),
                        canGoLeft(mSpeed * delta), canGoRight(mSpeed * delta), oldDirection);
            }
        }
        applyDirection(mSpeed * delta);
        mLastDelta = delta;
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

    protected void changeDirection(final int direction) {
        if (mDirection == direction)
            return;
        if (direction != DirectionHandler.UNKNOWN &&
                !canGoDirection(direction, mSpeed * mLastDelta))
            return;
        mDirection = direction;
        mNextDirection = DirectionHandler.UNKNOWN;
        directionChanged();
    }

    private boolean canGo(float moveX, float moveY) {
        float x = getX();
        float y = getY();
        setPosition(x + moveX, y + moveY);
        boolean colliding = GameMap.isColliding(this);
        setPosition(x, y);
        return !colliding;
    }

    public boolean canGoUp(float speed) {
        return canGo(0, speed);
    }

    public boolean canGoDown(float speed) {
        return canGo(0, -speed);
    }

    public boolean canGoLeft(float speed) {
        return canGo(-speed, 0);
    }

    public boolean canGoRight(float speed) {
        return canGo(speed, 0);
    }

    public boolean canGoDirection(int direction, float speed) {
        switch (direction) {
            case DirectionHandler.UP:
                return canGoUp(speed);
            case DirectionHandler.DOWN:
                return canGoDown(speed);
            case DirectionHandler.LEFT:
                return canGoLeft(speed);
            case DirectionHandler.RIGHT:
                return canGoRight(speed);
            default:
                return false;
        }
    }

    public boolean setDirectionUp() {
        if (canGoUp(mSpeed * mLastDelta)) {
            changeDirection(DirectionHandler.UP);
            return true;
        } else {
            mNextDirection = DirectionHandler.UP;
            return false;
        }
    }

    public boolean setDirectionDown() {
        if (canGoDown(mSpeed * mLastDelta)) {
            changeDirection(DirectionHandler.DOWN);
            return true;
        } else {
            mNextDirection = DirectionHandler.DOWN;
            return false;
        }
    }

    public boolean setDirectionLeft() {
        if (canGoLeft(mSpeed * mLastDelta)) {
            changeDirection(DirectionHandler.LEFT);
            return true;
        } else {
            mNextDirection = DirectionHandler.LEFT;
            return false;
        }
    }

    public boolean setDirectionRight() {
        if (canGoRight(mSpeed * mLastDelta)) {
            changeDirection(DirectionHandler.RIGHT);
            return true;
        } else {
            mNextDirection = DirectionHandler.RIGHT;
            return false;
        }
    }

    public void onCollision(boolean upAvailable, boolean downAvailable,
                            boolean leftAvailable, boolean rightAvailable, int direction) {}

    public void onIntersection(boolean upAvailable, boolean downAvailable,
                            boolean leftAvailable, boolean rightAvailable, int direction) {
        switch (mNextDirection) {
            case DirectionHandler.UP:
                if (upAvailable)
                    changeDirection(DirectionHandler.UP);
                break;
            case DirectionHandler.DOWN:
                if (downAvailable)
                    changeDirection(DirectionHandler.DOWN);
                break;
            case DirectionHandler.LEFT:
                if (leftAvailable)
                    changeDirection(DirectionHandler.LEFT);
                break;
            case DirectionHandler.RIGHT:
                if (rightAvailable)
                    changeDirection(DirectionHandler.RIGHT);
                break;
        }
    }

    public int getCellX() {
        return (int)((getX() + getWidth() / 2) / GameMap.getTileWidth());
    }

    public int getCellY() {
        return (int)((getY() + getHeight() / 2) / GameMap.getTileHeight());
    }
}
