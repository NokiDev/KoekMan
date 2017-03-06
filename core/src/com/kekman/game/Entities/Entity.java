package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kekman.game.Tools.Animations.Animations;

/**
 * Created by elytum on 06/03/2017.
 */

public class Entity extends Actor {
    private Animations mAnimations;

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
}
