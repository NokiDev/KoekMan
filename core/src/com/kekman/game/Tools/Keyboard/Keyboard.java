package com.kekman.game.Tools.Keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by elytum on 06/03/2017.
 */

public class Keyboard extends GestureDetector.GestureAdapter implements InputProcessor {
    private int         mLastKey = Input.Keys.UNKNOWN;
    private boolean     mActiveKey = false;

    private boolean dragging = false;
    private int dragStartX = 0;
    private int dragStartY = 0;


    public void setInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        mActiveKey = true;
        mLastKey = keycode;
        return false;
    }

    private int getKeycode() {return mLastKey;}
    public boolean isActive() {return mActiveKey;}

    @Override
    public boolean keyUp(int keycode) {
        if (mLastKey == keycode)
            mActiveKey = false;
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(pointer>0) return false;
        dragging = true;
        dragStartX = screenX;
        dragStartY = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer>0) return false;
        dragging = false;
        dragStartX = 0;
        dragStartY = 0;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(!dragging) return false;
        int velocityX =  screenX - dragStartX;
        int velocityY = screenY - dragStartY;
        if(Math.abs(velocityX) + Math.abs(velocityY) > 20){
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
        }
        //System.out.println("DRAGGED : " + screenX + "-" + screenY + " / " + pointer);
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
