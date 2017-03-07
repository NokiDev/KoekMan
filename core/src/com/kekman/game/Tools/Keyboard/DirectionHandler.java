package com.kekman.game.Tools.Keyboard;

import com.badlogic.gdx.Input;
import com.kekman.game.Tools.Ternary;

/**
 * Created by elytum on 06/03/2017.
 */

public class DirectionHandler {
    public interface IDirectionListener {
        public Ternary onUp();
        public Ternary onDown();
        public Ternary onLeft();
        public Ternary onRight();
        public Ternary onUnknown();
    }

    public static abstract class ADirectionListener implements IDirectionListener {
        public Ternary onUnknown() {return new Ternary(Ternary.UNKNOWN);};
    }

    final static public int UP =        0;
    final static public int DOWN =      1;
    final static public int LEFT =      2;
    final static public int RIGHT =     3;
    final static public int UNKNOWN =   4;

    private IDirectionListener    mDirectionListener;

    public DirectionHandler(final IDirectionListener directionListener) {
        setDirectionLIstener(directionListener);
    }

    public void setDirectionLIstener(final IDirectionListener directionListener) {
        mDirectionListener = directionListener;
    }

    public static int getDirection(final Keyboard keyboard) {
        switch (keyboard.getLastKey()) {
            case (Input.Keys.UP):
                return UP;
            case (Input.Keys.DOWN):
                return DOWN;
            case (Input.Keys.LEFT):
                return LEFT;
            case (Input.Keys.RIGHT):
                return RIGHT;
            case (Input.Keys.UNKNOWN):
                return UNKNOWN;
            default:
                return -1;
        }
    }

    public Ternary applyDirection(final Keyboard keyboard) {
        if (mDirectionListener == null) {
            System.out.println("Can't apply direction without listener");
        }
        switch (keyboard.getLastKey()) {
            case (Input.Keys.UP):
                return mDirectionListener.onUp();
            case (Input.Keys.DOWN):
                return mDirectionListener.onDown();
            case (Input.Keys.LEFT):
                return mDirectionListener.onLeft();
            case (Input.Keys.RIGHT):
                return mDirectionListener.onRight();
            case (Input.Keys.UNKNOWN):
                return mDirectionListener.onUnknown();
            default:
                return new Ternary(Ternary.UNKNOWN);
        }
    }
}
