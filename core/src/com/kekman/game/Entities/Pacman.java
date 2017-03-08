package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Definitions.Player;

/**
 * Created by elytum on 06/03/2017.
 */

public class Pacman extends Player {
    static public final String name = "pacman";

    public Pacman(final TextureAtlas atlas, int tileX, int tileY) {
        super(name, atlas, tileX, tileY);
    }
}
