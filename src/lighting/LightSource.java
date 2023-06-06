package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource interface represents a light source in a scene.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at the given point.
     *
     * @param p The point to get the intensity at.
     * @return The intensity of the light at the given point.
     */
    Color getIntensity(Point p);

    /**
     * Returns the direction of the light at the given point.
     *
     * @param p The point to get the direction at.
     * @return The direction of the light at the given point.
     */
    Vector getL(Point p);

    /**
     * returns the distance of the light source
     *
     * @param point point to check the distance
     * @return distance of the light source
     */
    double getDistance(Point point);
}
