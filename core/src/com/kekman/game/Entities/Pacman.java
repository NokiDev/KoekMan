package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Bonus;
import com.kekman.game.Entities.Definitions.Enemy;
import com.kekman.game.Entities.Definitions.Entity;
import com.kekman.game.Entities.Definitions.Player;
import com.kekman.game.Map.GameMap;
import com.kekman.game.Tools.CollisionDetector.CollisionDetector;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Player {
    private final int DEFAULT_WIDTH = 28;
    private final int DEFAULT_HEIGHT = 28;

    public Pacman(final TextureAtlas atlas) {
        setName("pacman");
        setAtlas(atlas);
        setAnimation("walk_right");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setCell(1, 1);
    }

    @Override
    public void onCollision(final Entity collider) {
        if (collider instanceof Enemy) {
            System.out.println("DIE");
        } else if (collider instanceof Bonus) {
            System.out.println("EAT BONUS");
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        CollisionDetector.applyCollision(GameMap.getEntities(), this);
    }
}
