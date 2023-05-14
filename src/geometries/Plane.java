package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a plane in 3D space.
 */
public class Plane implements Geometry {

    /**
     * The starting point q0 of the plane.
     */
    private final Point q0;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane from three points.
     *
     * @param p1 The first point on the plane.
     * @param p2 The second point on the plane.
     * @param p3 The third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
        this.q0 = p1;
    }

    /**
     * Constructs a plane from a point and a normal vector.
     *
     * @param p      The point on the plane.
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
        return this.normal;
    }

    /**
     * Gets the normal vector to the plane.
     *
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return this.normal;
    }

    /**
     * @param ray the ray
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        double nv = normal.dotProduct(ray.getDir());
        // if the ray is parallel to the plane or if the ray starts on the plane
        if (isZero(nv) || q0.equals(ray.getP0())) return null;

        double t = normal.dotProduct(q0.subtract(ray.getP0())) / nv;
        // if the intersection is behind the ray
        return alignZero(t) > 0 ? List.of(ray.getPoint(t)) : null;
    }
}