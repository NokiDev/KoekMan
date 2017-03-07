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

public class Animations extends Sprite implements IAnimation {
    private final String                        mEntityName;
    private String                              mAnimation;
    private final HashMap<String, Animation<TextureRegion>>    mAnimationsArray;

    public String toString() {
        return "(Entity: "+mEntityName+", animation: "+mAnimation+" with: "+mAnimationsArray+")";
    }

    public Animations(final TextureAtlas atlas, final String entityName) {
        mEntityName = entityName;
        mAnimation = null;

        final HashMap<String, Array<TextureRegion>>    mFoundRegions = new HashMap<String, Array<TextureRegion>>();
        for (TextureAtlas.AtlasRegion region: atlas.getRegions()) {
            if (region.name.startsWith(mEntityName)) {
                final String animation = region.name.substring(mEntityName.length() + 1);
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

        mAnimationsArray = new HashMap<String, Animation<TextureRegion>>();
        for (Map.Entry<String, Array<TextureRegion>> entry : mFoundRegions.entrySet()) {
            String                  name = entry.getKey();
            Array<TextureRegion>    region = entry.getValue();

            mAnimationsArray.put(name, new Animation<TextureRegion>(.1f, region, Animation.PlayMode.LOOP_PINGPONG));
        }
    }

    public void setAnimation(final String animation) {
        mAnimation = animation;
    }

    public void setAnimation(final String animation, boolean reset) {
        setAnimation(animation);
        if (reset)
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
        Animation<TextureRegion> animation = mAnimationsArray.get(mAnimation);
        if (animation == null) {
            System.out.println("Can't draw "+this);
            return;
        }
        batch.draw(animation.getKeyFrame(mStateTime), getX(), getY(), getWidth(), getHeight());
    }
}
