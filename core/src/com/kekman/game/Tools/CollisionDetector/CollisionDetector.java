package com.kekman.game.Tools.CollisionDetector;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.kekman.game.Entities.Definitions.Entity;

/**
 * Created by elytum on 06/03/2017.
 */

public class CollisionDetector {
    public static Rectangle getRectangle(final Actor actor) {
        return new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
    }

    public static RectangleMapObject getRectangle(final TextureMapObject text) {
        final MapProperties properties = text.getProperties();
        float x = text.getX();
        float y = text.getY();
        float width = properties.get("width", Float.class);
        float height = properties.get("height", Float.class);

        return new RectangleMapObject(x, y, width, height);
    }

    public static PolygonMapObject getPolygon(final TextureMapObject text) {
        final MapProperties properties = text.getProperties();
        float x = text.getX();
        float y = text.getY();
        float width = properties.get("width", Float.class);
        float height = properties.get("height", Float.class);

        float[] points = new float[] {
                x, y,
                x + width, y,
                x + width, y + height,
                x, y + height
        };
        final Polygon polygon = new Polygon(points);
        polygon.rotate(text.getRotation());
        return new PolygonMapObject(polygon);
    }

    public static Entity applyCollision(final Entity collider, final Entity checked) {
        if (collider.hashCode() == checked.hashCode())
            return null;
        if (isCollision(getRectangle(collider), getRectangle(checked)))
            return collider;
        else return null;
    }
    public static void applyCollision(final Array<Entity> entities, final Entity checked) {
        for (Entity entity: entities) {
            Entity collider = applyCollision(entity, checked);
            if (collider != null)
                checked.onCollision(collider);
        }
    }

    public static MapObject checkCollision(int layerId, final TiledMap map, final Rectangle entityRectangle) {
        final MapLayer mapLayer = map.getLayers().get(layerId);

        for (MapObject object : mapLayer.getObjects())
            if (object instanceof TextureMapObject) {
                TextureMapObject text = (TextureMapObject) object;
                if (text.getRotation() == 0) {
                    final RectangleMapObject rectangle = getRectangle(text);
                    if (isCollision(rectangle.getRectangle(), entityRectangle))
                        return rectangle;
                } else {
                    final PolygonMapObject polygon = getPolygon(text);
                    if (isCollision(polygon.getPolygon(), entityRectangle))
                        return polygon;
                }
            } else if (object instanceof RectangleMapObject) {
                if (isCollision(((RectangleMapObject) object).getRectangle(), entityRectangle))
                    return object;
            } else if (object instanceof PolygonMapObject) {
                if (isCollision(((PolygonMapObject) object).getPolygon(), entityRectangle))
                    return object;
            }
//            else if (object instanceof PolylineMapObject) {
//                if (((PolylineMapObject) object).getPolyline())
//                    return object;
//            }
//            else if (object instanceof EllipseMapObject) {
//                if (((EllipseMapObject) object).getEllipse())
//                    return object;
//            }
            else if (object instanceof CircleMapObject) {
                if (isCollision(((CircleMapObject) object).getCircle(), entityRectangle))
                    return object;
                ((CircleMapObject) object).getCircle();
            }
        return null;
    }

    public static MapObject checkCollision(int layerId, final TiledMap map, final Entity entity) {
        return checkCollision(layerId, map, getRectangle(entity));
    }

    public static MapObject checkCollision(final TiledMap map, final Entity entity) {
        return checkCollision(map, getRectangle(entity));
    }

    public static MapObject checkCollision(final TiledMap map, final Rectangle entityRectangle) {
        final int       range = map.getLayers().getCount();

        for (int i=0; i < range; i++) {
            MapObject collider = checkCollision(i, map, entityRectangle);
            if (collider != null)
                return collider;
        }
        return null;
    }

    private static boolean isCollision(Rectangle r1, Rectangle r2) {
        return Intersector.overlaps(r1, r2);
    }

    private static boolean isCollision(Rectangle r, Circle c) {
        return isCollision(c, r);
    }
        private static boolean isCollision(Circle c, Rectangle r) {
        return Intersector.overlaps(c, r);
    }

    private static boolean isCollision(Rectangle r, Polygon p) {
        return isCollision(p, r);
    }
        private static boolean isCollision(Polygon p, Rectangle r) {
        Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width,
                r.height, 0, r.height });
        rPoly.setPosition(r.x, r.y);
        if (Intersector.overlapConvexPolygons(rPoly, p))
            return true;
        return false;
    }

    private static boolean isCollision(Circle c, Polygon p) {
        return isCollision(p, c);
    }
    private static boolean isCollision(Polygon p, Circle c) {
        float[] vertices = p.getTransformedVertices();
        Vector2 center = new Vector2(c.x, c.y);
        float squareRadius = c.radius * c.radius;
        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[vertices.length - 2],
                        vertices[vertices.length - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            } else {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[i - 2], vertices[i - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            }
        }
        return false;
    }
}
