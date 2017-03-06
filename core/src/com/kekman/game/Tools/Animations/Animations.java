package com.kekman.game.Tools.Animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by elytum on 06/03/2017.
 */

public class Animations extends Sprite {
    private final String                        mEntityName;
    private String                              mAnimation;
    private final HashMap<String, Animation>    mAnimationsArray;

    public Animations(final TextureAtlas atlas, final String entityName) {
        mEntityName = entityName;
        mAnimation = null;

        final HashMap<String, Array<TextureRegion>>    mFoundRegions = new HashMap<String, Array<TextureRegion>>();
        for (TextureAtlas.AtlasRegion region: atlas.getRegions()) {
            if (region.name.startsWith(entityName)) {
                final String animation = region.name.substring(entityName.length() + 1);
                final String[] frameInformation = animation.split("/");
                if (frameInformation.length != 2)
                    continue;
                Array<TextureRegion> foundRegion = mFoundRegions.get(frameInformation[0]);
                if (foundRegion == null) {
                    foundRegion = new Array<TextureRegion>();
                    mFoundRegions.put(frameInformation[0], foundRegion);
                }
                foundRegion.add(region);
            }
        }

        mAnimationsArray = new HashMap<String, Animation>();
        for (Map.Entry<String, Array<TextureRegion>> entry : mFoundRegions.entrySet()) {
            String                  name = entry.getKey();
            Array<TextureRegion>    region = entry.getValue();

            mAnimationsArray.put(name, new Animation<TextureRegion>(.1f, region, Animation.PlayMode.LOOP));
        }
        System.out.println(mAnimationsArray);
    }

    public void setAnimation(final String animation) {
        mAnimation = animation;
        mStateTime = 0;
    }

    private float mStateTime = 0;

    public float getStateTime() {
        return mStateTime;
    }
    public void act(float delta) {
        mStateTime += delta;
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw((TextureRegion)mAnimationsArray.get(mAnimation).getKeyFrame(mStateTime), getX(), getY());
    }
}
