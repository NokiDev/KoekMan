package com.kekman.game.Tools.Keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by elytum on 06/03/2017.
 */

public class Keyboard implements InputProcessor {
    private int mLastKey = Input.Keys.UNKNOWN;

    public void setInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        mLastKey = keycode;
        return false;
    }

    private int getKeycode() {
        return mLastKey;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (mLastKey == keycode)
            mLastKey = Input.Keys.UNKNOWN;
        return false;
    }

    public int getLastKey() {
        return mLastKey;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
