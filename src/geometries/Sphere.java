package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a sphere in 3D space.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private Point center;

    /**
     * Constructs a sphere with a given radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Gets the normal vector to the sphere at a given point.
     *
     * @param point The point at which to get the normal vector.
     * @return The normal vector to the sphere at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
