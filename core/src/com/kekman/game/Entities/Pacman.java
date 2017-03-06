package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Tools.Animations.Animations;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Entity {
    private final Animations    animations;

    public Pacman(final TextureAtlas atlas) {
        animations = new Animations(atlas, "pacman");
        animations.setAnimation("walk");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        animations.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animations.act(delta);
    }
}
