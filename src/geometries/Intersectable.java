package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;


/**
 * The interface Intersectable.
 */
public interface Intersectable {

    /**
     * Find points of intersection between the geometry and a ray
     *
     * @param ray the ray
     * @return the list of intersection points
     */
    List<Point> findIntersections(Ray ray);
}