package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.Score.Score;

/**
 * Created by elytum on 07/03/2017.
 */

public class Bonus extends StaticEntity {
    protected Bonus(final String name, final TextureAtlas atlas, int tileX, int tileY, int value) {
        super(name,atlas, tileX, tileY);
        setValue(value);
    }
    public void actorAdded(final Stage stage) {setZIndex(BONUS_ZINDEX);}

    int mValue = 0;
    public void setValue(int value) {mValue = value;}
    public int getValue() {return mValue;}

    protected void bonusRemoved() {}

    public void applyBonus(final Entity entity) {
        if (entity instanceof Player) {
            Score.AddScore(mValue);
            remove();
            GameMap.removeEntity(this);
            bonusRemoved();
        }
    }
}
