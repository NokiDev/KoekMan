package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kekman.game.Tools.Animations.Animations;

/**
 * Created by elytum on 06/03/2017.
 */

public class Entity extends Actor {
    private Animations animations;

    public String toString() {
        return super.toString()+" -> "+animations;
    }

    protected void setAtlas(final TextureAtlas atlas) {
        animations = new Animations(atlas, getName());
    }

    protected void setAnimation(final String animation) {
        animations.setAnimation(animation);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (animations != null)
            animations.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (animations != null)
            animations.act(delta);
    }
}
