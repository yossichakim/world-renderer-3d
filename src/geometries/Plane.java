package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a plane in 3D space.
 */
public class Plane implements Geometry {

    /**
     * The starting point q0 of the plane.
     */
    private Point q0;

    /**
     * The normal vector to the plane.
     */
    private Vector normal;

    /**
     * Constructs a plane from three points.
     *
     * @param p1 The first point on the plane.
     * @param p2 The second point on the plane.
     * @param p3 The third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.normal = null;
        this.q0 = p1;
    }

    /**
     * Constructs a plane from a point and a normal vector.
     *
     * @param p The point on the plane.
     * @param normal The normal vector to the plane.
     */
    public Plane(Point p, Vector normal) {
        this.normal = normal.normalize();
        this.q0 = p;
    }

    /**
     * Gets the normal vector to the plane at a given point.
     *
     * @param point The point at which to get the normal vector.
     * @return The normal vector to the plane at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    /**
     * Gets the normal vector to the plane.
     *
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return this.normal;
    }
}
