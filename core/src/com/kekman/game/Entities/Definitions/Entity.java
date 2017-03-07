package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Animations.Animations;
import com.kekman.game.Tools.Tilemap.TilemapUtils;

/**
 * Created by elytum on 06/03/2017.
 */

public class Entity extends Actor {
    protected final int DEFAULT_WIDTH = 30;
    protected final int DEFAULT_HEIGHT = 30;

    protected Entity(final String name, final TextureAtlas atlas, int tileX, int tileY) {
        setName(name);
        setAtlas(atlas);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setCell(tileX, tileY);
    }

    private Animations  mAnimations;

    public String   toString() {return super.toString()+" -> "+mAnimations;}
    protected void  setAtlas(final TextureAtlas atlas) {mAnimations = new Animations(atlas, getName());}
    protected void  setAnimation(final String animation) {mAnimations.setAnimation(animation);}
    public void     onCollision(final Entity collider) {}

    @Override protected void	positionChanged() {super.positionChanged(); mAnimations.setPosition(getX(), getY());}
    @Override protected void	sizeChanged() {super.sizeChanged(); mAnimations.setSize(getWidth(), getHeight());}

    @Override
    public void     draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (mAnimations != null)
            mAnimations.draw(batch, parentAlpha);
    }

    @Override
    public void     act(float delta) {
        super.act(delta);
        if (mAnimations != null)
            mAnimations.act(delta);
    }

    public void setCell(int x, int y) {
        float width = TilemapUtils.getTileWidth(GameMap.getTilesMap());
        float height = TilemapUtils.getTileHeight(GameMap.getTilesMap());
        float offsetX = (width - getWidth()) / 2;
        float offsetY = (height - getHeight()) / 2;
        setPosition(x * width + offsetX, y * height + offsetY);
    }
}
