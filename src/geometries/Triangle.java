package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
        // Ray starts at one of the vertices or Ray starts on one of the edges
        if (vertices.get(0).equals(ray.getP0()) ||
                vertices.get(1).equals(ray.getP0()) ||
                vertices.get(2).equals(ray.getP0()))
            return null;

        // Ray starts on the plane of the triangle
        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double s1 = ray.getDir().dotProduct(n1);
        double s2 = ray.getDir().dotProduct(n2);
        double s3 = ray.getDir().dotProduct(n3);

        // Ray is parallel to the plane of the triangle
        if (isZero(s1) || isZero(s2) || isZero(s3))
            return null;

        // Ray is outside the plane of the triangle
        if (alignZero(s1) > 0 && alignZero(s2) > 0 && alignZero(s3) > 0 ||
                alignZero(s1) < 0 && alignZero(s2) < 0 && alignZero(s3) < 0)
            return plane.findIntersections(ray);

        return null;
    }
}