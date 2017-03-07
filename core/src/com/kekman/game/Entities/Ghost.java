package com.kekman.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kekman.game.Entities.Behaviours.IBehaviour;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Ghost extends Entity {

    private Color color;
    private IBehaviour currentBehaviour;
    protected final int DEFAULT_WIDTH = 30;
    protected final int DEFAULT_HEIGHT = 30;

    public void setBehaviour(IBehaviour behaviour){
        this.currentBehaviour = behaviour;
    }


}
