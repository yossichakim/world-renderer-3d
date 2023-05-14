package geometries;

import primitives.Double3;
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
    
    @Override
    public List<Point> findIntersections(Ray ray) {
        
        List<Point> intersections = new ;
        
        if (intersections == null) return null;
        
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double sign = alignZero(v.dotProduct(n1));
        if (isZero(sign)) return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double sign2 = alignZero(v.dotProduct(n2));
        if (alignZero(sign * sign2) <= 0 ) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double sign3 = v.dotProduct(n3);
        if (alignZero(sign * sign3) <= 0 ) return null;

        return intersections;
    }
}
