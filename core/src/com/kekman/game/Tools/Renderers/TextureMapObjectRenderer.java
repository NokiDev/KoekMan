package com.kekman.game.Tools.Renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by elytum on 07/03/2017.
 */

public class TextureMapObjectRenderer extends OrthogonalTiledMapRenderer {

    public TextureMapObjectRenderer(TiledMap map) {
        super(map);
    }

    public TextureMapObjectRenderer(TiledMap map, Batch batch) {
        super(map, batch);
    }

    public TextureMapObjectRenderer(TiledMap map, float unitScale) {
        super(map, unitScale);
    }

    public TextureMapObjectRenderer(TiledMap map, float unitScale, Batch batch) {
        super(map, unitScale, batch);
    }

    @Override
    public void renderObject(MapObject object) {
        if (object instanceof TextureMapObject) {
            TextureMapObject textureObject = (TextureMapObject) object;
            batch.draw(
                    textureObject.getTextureRegion(),
                    textureObject.getX(),
                    textureObject.getY(),
                    textureObject.getOriginX(),
                    textureObject.getOriginY(),
                    textureObject.getTextureRegion().getRegionWidth(),
                    textureObject.getTextureRegion().getRegionHeight(),
                    textureObject.getScaleX(),
                    textureObject.getScaleY(),
                    textureObject.getRotation());
        }
    }
}