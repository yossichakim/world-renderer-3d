package lighting;
import primitives.*;

/*
 * LightSource interface represents a light source in a scene.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at the given point.
     *
     * @param p The point to get the intensity at.
     * @return The intensity of the light at the given point.
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction of the light at the given point.
     *
     * @param p The point to get the direction at.
     * @return The direction of the light at the given point.
     */
    public Vector getL(Point p);

    /**
     * returns the distance of the light source
     *
     * @param point point to check the distance
     * @return distance of the light source
     */
    double getDistance(Point point);
}
