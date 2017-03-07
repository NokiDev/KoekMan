package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Player;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Player {
    private final int DEFAULT_WIDTH = 28;
    private final int DEFAULT_HEIGHT = 28;

    public Pacman(final TextureAtlas atlas, int tileX, int tileY) {
        super("pacman", atlas, tileX, tileY);
//        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
