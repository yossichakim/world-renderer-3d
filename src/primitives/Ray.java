package primitives;

import java.util.List;

import static primitives.Util.isZero;

import geometries.Intersectable.GeoPoint;

/**
 * Ray class represents a ray in a 3D Cartesian coordinate system.
 * A ray is defined by a starting point and a direction vector.
 */
public class Ray {

    /**
     *constant for ray
     */
    private static final double DELTA = 0.1;

    /**
     * The starting point of the ray.
     */
    private final Point p0;

    /**
     * The normalized direction vector of the ray.
     */
    private final Vector dir;

    /**
     * Gets the normalized direction vector of the ray.
     *
     * @return The normalized direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Gets the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Constructs a ray with a given starting point and direction vector.
     *
     * @param p0  the starting point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructor that moves the ray by DELTA
     *
     * @param p0 point
     * @param direction direction (must be normalized)
     * @param normal normal
     */
    public Ray(Point p0, Vector direction, Vector normal) {
        Vector delta = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : - DELTA);
        this.p0 = p0.add(delta);
        this.dir = direction;
    }


    /**
     * Calculates the point where starts at p0 and scaled by t.
     *
     * @param t The scalar to scale the direction with.
     * @return The calculated point.
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * Finds the closest point to the ray's starting point from a list of points.
     *
     * @param points The list of points to find the closest point from.
     * @return The closest point to the ray's starting point from the list of points.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                :findClosestGeoPoint (points.stream().map(p -> new GeoPoint (null, p)).toList()).point;
    }

    /**
     * Find closest GeoPoint
     *
     * @param points list of GeoPoints
     * @return closest GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null || points.isEmpty())
            return null;

        GeoPoint result = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (GeoPoint point : points) {
            double distance = point.point.distance(p0);
            if (distance < minDistance) {
                minDistance = distance;
                result = point;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray ray) && p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + "}";
    }
}
