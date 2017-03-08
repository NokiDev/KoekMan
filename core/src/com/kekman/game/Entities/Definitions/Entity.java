package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Actor;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Animations.Animations;
import com.kekman.game.Tools.Tilemap.TilemapUtils;

/**
 * Created by elytum on 06/03/2017.
 */

public class Entity extends Actor {
    protected final int DEFAULT_WIDTH = 32;
    protected final int DEFAULT_HEIGHT = 32;
    protected final int BONUS_ZINDEX = 2;
    protected final int PLAYER_ZINDEX = 1;
    protected final int ENEMY_ZINDEX = 0;

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
    protected void  setAnimation(final String animation, final boolean reset) {mAnimations.setAnimation(animation, reset);}
    protected void  setPlaymode(final String animationName, final Animation.PlayMode playmode) {mAnimations.setPlaymode(animationName, playmode);}

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

    public void     onCollision(final Entity collider) {
        if (collider instanceof Bonus)
            ((Bonus) collider).applyBonus(this);
    }
}
