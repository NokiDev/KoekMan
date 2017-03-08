package com.kekman.game.Tools.Keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by elytum on 06/03/2017.
 */

public class Keyboard extends GestureDetector.GestureAdapter implements InputProcessor {
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
        //if (mLastKey == keycode)
            //mLastKey = Input.Keys.UNKNOWN;
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
        //System.out.println("DRAGGED : " + screenX + "-" + screenY + " / " + pointer);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        System.out.println("FLING : " + velocityX + "-" + velocityY);
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX>0){
                mLastKey = Input.Keys.RIGHT;
            }else{
                mLastKey = Input.Keys.LEFT;
            }
        }else{
            if(velocityY>0){
                mLastKey = Input.Keys.DOWN;
            }else{
                mLastKey = Input.Keys.UP;
            }
        }
        return super.fling(velocityX, velocityY, button);
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
