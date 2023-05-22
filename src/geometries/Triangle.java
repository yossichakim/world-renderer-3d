package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a triangle in 3D space.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a triangle with the given vertices.
     *
     * @param p1 The first vertex of the triangle.
     * @param p2 The second vertex of the triangle.
     * @param p3 The third vertex of the triangle.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * @param ray the ray to find intersections with
     * @return list of intersection points
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        var intersection = plane.findIntersections(ray);
        if (intersection == null) return null;

        Point p0 = ray.getP0();
        Vector dir = ray.getDir();

        // take care of the 1st edge
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double s1 = alignZero(dir.dotProduct(n1));
        if (s1 == 0) return null;

        // take care of the 2nd edge
        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double s2 = alignZero(dir.dotProduct(n2));
        if (s1 * s2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double s3 = alignZero(dir.dotProduct(n3));
        if (s1 * s3 <= 0) return null;

        return intersection;
    }
}