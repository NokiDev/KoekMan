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
import com.kekman.game.Entities.Entity;

/**
 * Created by elytum on 06/03/2017.
 */

public class CollisionDetector {
    public static Rectangle getRectangle(final Actor actor) {
        return new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
    }

    public static Rectangle getRectangle(final TextureMapObject text) {
        final MapProperties properties = text.getProperties();
        float x = text.getX();
        float y = text.getY();
        float width = properties.get("width", Float.class);
        float height = properties.get("height", Float.class);

        return new Rectangle(x, y, width, height);
    }

    public static Polygon getPolygon(final TextureMapObject text) {
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
        return polygon;
    }

    public static Object checkCollision(int layerId, final TiledMap map, final Rectangle entityRectangle) {
        System.out.println("checkCollision");
        final MapLayer mapLayer = map.getLayers().get(layerId);

        for (MapObject object : mapLayer.getObjects())
            if (object instanceof TextureMapObject) {
                TextureMapObject text = (TextureMapObject) object;
                if (text.getRotation() == 0) {
                    final Rectangle rectangle = getRectangle(text);
                    if (isCollision(rectangle, entityRectangle))
                        return rectangle;
                } else {
                    final Polygon polygon = getPolygon(text);
                    if (isCollision(polygon, entityRectangle))
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

    public static Object checkCollision(int layerId, final TiledMap map, final Entity entity) {
        return checkCollision(layerId, map, getRectangle(entity));
    }

    public static Object checkCollision(final TiledMap map, final Entity entity) {
        return checkCollision(map, getRectangle(entity));
    }

    public static Object checkCollision(final TiledMap map, final Rectangle entityRectangle) {
        final int       range = map.getLayers().getCount();

        for (int i=0; i < range; i++) {
            Object collider = checkCollision(i, map, entityRectangle);
            if (collider != null)
                return collider;
        }
        return null;
    }

    private static boolean isCollision(Rectangle r1, Rectangle r2) {
//        System.out.println(r1+" x "+r2);
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
