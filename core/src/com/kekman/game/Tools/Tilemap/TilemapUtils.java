package com.kekman.game.Tools.Tilemap;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.kekman.game.Tools.Random.RandomUtils;

/**
 * Created by elytum on 07/03/2017.
 */

public class TilemapUtils {
    public static int           getWidth(final MapLayer mapLayer) {
        if (mapLayer instanceof TiledMapTileLayer) {
            TiledMapTileLayer layer = (TiledMapTileLayer) mapLayer;
            return layer.getWidth();
        }
        else return 0;
    }

    public static int           getMaxWidth(final TiledMap tiledMap) {
        int maxWidth = 0;
        for (MapLayer layer: tiledMap.getLayers())
            maxWidth = Math.max(maxWidth, getWidth(layer));
        return maxWidth;
    }

    public static int           getHeight(final MapLayer mapLayer) {
        if (mapLayer instanceof TiledMapTileLayer) {
            TiledMapTileLayer layer = (TiledMapTileLayer) mapLayer;
            return layer.getHeight();
        }
        else return 0;
    }

    public static int           getMaxHeight(final TiledMap tiledMap) {
        int maxHeight = 0;
        for (MapLayer layer: tiledMap.getLayers())
            maxHeight = Math.max(maxHeight, getHeight(layer));
        return maxHeight;
    }

    public static float           getTileWidth(final TiledMap tiledMap) {
        MapLayer tileLayer = tiledMap.getLayers().get(0);
        if (tileLayer != null && tileLayer instanceof TiledMapTileLayer) {
            TiledMapTileLayer layer = (TiledMapTileLayer) tileLayer;
            return layer.getTileWidth();
        }
        else
            return 0;
    }

    public static float           getTileHeight(final TiledMap tiledMap) {
        MapLayer tileLayer = tiledMap.getLayers().get(0);
        if (tileLayer != null && tileLayer instanceof TiledMapTileLayer) {
            TiledMapTileLayer layer = (TiledMapTileLayer) tileLayer;
            return layer.getTileHeight();
        }
        else
            return 0;
    }

    public static void printBoolArrayArray(final boolean[][] array) {
        for (boolean yArray[]: array) {
            System.out.print("[");
            for (boolean xValue: yArray)
                System.out.print(xValue+", ");
            System.out.println("]");
        }
    }

    public static boolean[][]   getEmptyCells(final TiledMap tiledMap) {
        boolean array[][] = new boolean[getMaxHeight(tiledMap)][getMaxWidth(tiledMap)];
//        for (boolean yArray[]: array) {
//            for (int i = 0; i < yArray.length; i++) {
//                yArray[i] = true;
//            }
//        }
        float tileWidth = getTileWidth(tiledMap);
        float tileHeight = getTileHeight(tiledMap);

        for (MapLayer layer: tiledMap.getLayers()) {
            for (MapObject object: layer.getObjects()) {
                MapProperties properties = object.getProperties();
                float x = properties.get("x", float.class);
                int   tileX = (int)(x / tileWidth);
                float y = properties.get("y", float.class);
                int   tileY = (int)(y / tileHeight);
                float width = properties.get("width", float.class) + x % tileWidth;
                float height = properties.get("height", float.class) + y % tileHeight;

                int posX = tileX - 1;
                for (float w = width; w > 0; w -= tileWidth) {
                    ++posX;
                    int posY = tileY - 1;
                    for (float h = height; h > 0; h -= tileHeight) {
                        ++posY;
                        array[posY][posX] = true;
                    }
                }
            }
        }
        printBoolArrayArray(array);
        return array;
    }

    public static int[] randomEmptyCell(final boolean[][] array) {
        int numberOfPossibilities = 0;
        for (boolean yArray[]: array) {
            for (boolean xValue: yArray) {
                if (!xValue)
                    ++numberOfPossibilities;
            }
        }
        int cell = RandomUtils.randInt(numberOfPossibilities - 1);
        for (int y = 0; y < array.length; y++) {
            boolean yArray[] = array[y];
            int x = 0;
            for (boolean xValue: yArray) {
                if (!xValue) {
                    if (cell == 0)
                        return new int[]{x, y};
                    --cell;
                }
                ++x;
            }
        }
        return null;
    }
}
