package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.LibgdxExtended.Stage;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;

/**
 * Created by elytum on 07/03/2017.
 */

public class Player extends MovingEntity {
    protected Player(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}
    public void actorAdded(final Stage stage) {setZIndex(PLAYER_ZINDEX);}

    @Override
    public void act(float delta) {
        super.act(delta);
        CollisionDetector.applyCollision(GameMap.getEntities(), this);
    }

    @Override
    public void onCollision(final Entity collider) {
        super.onCollision(collider);
        if (collider instanceof Enemy) {
            ;//            System.out.println("DIE");
        } else if (collider instanceof Bonus) {
            System.out.println("EAT BONUS");
        }
    }

    public void onCollision(boolean upAvailable, boolean downAvailable,
                            boolean leftAvailable, boolean rightAvailable) {
        System.out.println("Collision: "+upAvailable+" "+downAvailable+" "+leftAvailable+" "+rightAvailable);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
//        System.out.println("Changed position to "+getX()+" "+getY());
    }
}
