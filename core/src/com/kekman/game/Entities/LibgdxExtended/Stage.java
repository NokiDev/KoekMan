package com.kekman.game.Entities.LibgdxExtended;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Stage extends com.badlogic.gdx.scenes.scene2d.Stage {
    public Stage() {super();}
    public Stage(Viewport viewport) {super(viewport);}
    public Stage(Viewport viewport, Batch batch) {super(viewport, batch);}

    @Override
    public void addActor(final com.badlogic.gdx.scenes.scene2d.Actor actor) {
        super.addActor(actor);
        if (actor instanceof Actor)
            ((Actor) actor).actorAdded(this);
    }
}